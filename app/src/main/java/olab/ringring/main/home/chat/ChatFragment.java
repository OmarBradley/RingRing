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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.home.chat.moudle.gcm.RingRingGcmListenerService;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDAO;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.home.HomeProtocol;
import olab.ringring.network.response.chat.ChatContent;
import olab.ringring.main.home.chat.view.adapter.ChatViewAdapter;
import olab.ringring.notification.ChatNotificationActivity;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ChatFragment extends Fragment {

    public static final int USER_ID = 1;
    public static final int LOVER_ID = 2;
    public static final int CHAT_DAY_ID = 3;
    public static final int READ = 1;
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
            hideSoftInput(view);
            sendChatDataToServer();
            deleteStringInChatMessageEditView();
        });
    }

    private void hideSoftInput(View view) {
        InputMethodManager iMM = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        iMM.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private ChatContent makeChatMessage() {
        ChatContent chatContent = new ChatContent();
        chatContent.setReadStatus(UNREAD);
        chatContent.setSendDate(DateTime.now().getMillis());
        chatContent.setMessage(chatMessageEdit.getText().toString());
        chatContent.setSenderId("" + HomeActivity.USER_ID);
        chatContent.setReceiverId("" + HomeActivity.LOVER_ID);

        return chatContent;
    }

    private void sendChatDataToServer() {
        NetworkManager.getInstance().sendRequest(HomeProtocol.maeChatContentRequest(getActivity(), makeChatMessage()), ChatContent.class, (request, result) -> {
            CoupleChatDAO.getInstance().insertData(result);
        }, (request, integer, throwable) -> {
            Log.e("error", request.toString());
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
        chatMessageEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() == 0) {
                    setButtonClickable(false);
                } else {
                    setButtonClickable(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setButtonClickable(boolean isClickable){
        messageSendBtn.setEnabled(isClickable);
    }

    BroadcastReceiver chatReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getActivity().runOnUiThread(()->{
                bindChatDataToView();
            });
            Log.e("sss", "on");
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
        bindChatDataToView();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(chatReceiver, chatActionFilter);
    }

    private void bindChatDataToView(){
        List<ChatContent> chatResults = CoupleChatDAO.getInstance().getDataRows();
        if(chatResults.size() == 0){
            return;
        } else {
            adapter.addAllMessage(chatResults);
            scrollToLastItem();
        }
    }
}
