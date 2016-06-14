package olab.ringring.main.home.chat;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.annimon.stream.Stream;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import olab.ringring.R;
import olab.ringring.gcm.RingRingGcmListenerService;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDAO;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.home.HomeProtocol;
import olab.ringring.network.response.home.SuccessReceiveChat;
import olab.ringring.network.response.home.SuccessSendChat;
import olab.ringring.main.home.chat.view.adapter.ChatViewAdapter;
import olab.ringring.util.preperance.PropertyManager;
import olab.ringring.util.string.TextWatcherTemplate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ChatFragment extends Fragment {

    private static final long INIT_TIME = 0;
    public static final int UNREAD = 0;


    @Bind(R.id.list_view_chat_message) RecyclerView chatMessageListView;
    @Bind(R.id.btn_sender) Button messageSendBtn;
    @Bind(R.id.edit_chat_message) EditText chatMessageEdit;

    private ChatViewAdapter adapter;
    IntentFilter chatActionFilter = new IntentFilter(RingRingGcmListenerService.ACTION_CHAT);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View chatFragmentView =inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, chatFragmentView);
        setChatMessageListView();
        setOnClickListenerOnButtons();
        executeActionWhenTextTyping();
        hideSoftInput(chatFragmentView);
        return chatFragmentView;
    }



    private void setChatMessageListView(){
        adapter = new ChatViewAdapter();
        chatMessageListView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        chatMessageListView.setLayoutManager(layoutManager);
    }

    private void setOnClickListenerOnButtons() {
        messageSendBtn.setOnClickListener(view -> {
            sendChatDataToServer();
            deleteStringInChatMessageEditView();
        });
    }

    private void hideSoftInput(View view) {
        view.setOnClickListener(targetView ->{
            InputMethodManager iMM = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            iMM.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        });
    }

    private SuccessSendChat makeChatMessage() {
        SuccessSendChat successSendChat = new SuccessSendChat();
        successSendChat.setReadStatus(UNREAD);
        successSendChat.setSendDate(DateTime.now().getMillis());
        successSendChat.setMessage(chatMessageEdit.getText().toString());
        successSendChat.setSenderId(PropertyManager.getInstance().getUserIndexId());
        successSendChat.setReceiverId(PropertyManager.getInstance().getLoverIndexId());
        return successSendChat;
    }

    private void sendChatDataToServer() {
        NetworkManager.getInstance().sendRequest(HomeProtocol.maeSendChatMessageRequest(getActivity(), makeChatMessage()), SuccessSendChat.class, (request, result) -> {
            CoupleChatDAO.getInstance().insertData(result);
            adapter.addMessage(result);
            scrollToLastItem();
        }, (request, errorCode, throwable) -> {
            Log.e("request", request.toString());
            Log.e("error", errorCode.getMessage());
        });
    }

    private void scrollToLastItem() {
        chatMessageListView.scrollToPosition(adapter.getItemCount() - 1);
    }

    private void deleteStringInChatMessageEditView(){
        chatMessageEdit.setText("");
    }

    private void executeActionWhenTextTyping() {
        setButtonClickable(false);
        chatMessageEdit.addTextChangedListener(new TextWatcherTemplate()
                .setOnTextChanged((s) -> {
                    String text = s.toString();
                    if (text.length() == 0) {
                        setButtonClickable(false);
                    } else {
                        setButtonClickable(true);
                    }
                }).build());
    }

    private void setButtonClickable(boolean isClickable){
        messageSendBtn.setEnabled(isClickable);
    }

    BroadcastReceiver chatReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getActivity().runOnUiThread(ChatFragment.this::bindStoredChatDataToView);
            intent.putExtra(RingRingGcmListenerService.EXTRA_RESULT, true);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(chatReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        bindStoredChatDataToView();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(chatReceiver, chatActionFilter);
    }

    private void bindStoredChatDataToView() {
        CoupleChatDAO.getInstance().changeUnReadToRead();
        List<SuccessSendChat> chatResults = CoupleChatDAO.getInstance().getDataRows();
        if (chatResults.size() == 0) {
            NetworkManager.getInstance().sendRequest(HomeProtocol.makeReceiveChatMessageRequest(getActivity(), INIT_TIME), SuccessReceiveChat.class, (request, result) -> {
                Stream.of(result.getMessageContents()).forEach(CoupleChatDAO.getInstance()::insertData);
                adapter.addAllMessage(result.getMessageContents());
                scrollToLastItem();
            }, (request, networkResponseCode, throwable) -> {
                Toast.makeText(getActivity(), networkResponseCode.getMessage(), Toast.LENGTH_SHORT).show();
            });
            return;
        } else {
            adapter.addAllMessage(chatResults);
            scrollToLastItem();
        }
    }

}
