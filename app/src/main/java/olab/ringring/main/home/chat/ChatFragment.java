package olab.ringring.main.home.chat;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import olab.ringring.R;
import olab.ringring.main.home.chat.data.ChatResult;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDAO;
import olab.ringring.network.response.chat.ChatContent;
import olab.ringring.main.home.chat.view.adapter.ChatViewAdapter;
import olab.ringring.util.date.NowDateGetter;
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ChatFragment extends Fragment {

    public static final int USER_ID = 1;
    public static final int LOVER_ID = 2;
    public static final int CHAT_DAY_ID = 3;

    @Bind(R.id.list_view_chat_message) RecyclerView chatMessageListView;
    @Bind(R.id.btn_sender) Button messageSendBtn;
    @Bind(R.id.edit_chat_message) EditText chatMessageEdit;
    private ChatViewAdapter adapter;
    private ChatContent user;
    private long userId = CoupleChatDAO.INVALID_ID;

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
        chatMessageListView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setOnClickListenerOnButtons() {
        messageSendBtn.setOnClickListener(view -> {
            hideSoftInput(view);
            ChatContent chatContent = new ChatContent();
            chatContent.setMessage(chatMessageEdit.getText().toString());
            chatContent.setSenderId("" + USER_ID);
            chatContent.setSendDate(new NowDateGetter().getChatTimeString());
            adapter.addMessage(chatContent);
            scrollToLastItem();
            deleteStringInChatMessageEditView();
        });
    }

    private void hideSoftInput(View view) {
        InputMethodManager iMM = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        iMM.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void scrollToLastItem() {
        chatMessageListView.smoothScrollToPosition(adapter.getItemCount());
    }

    private void deleteStringInChatMessageEditView(){
        chatMessageEdit.setText("");
    }

    private void executeActionWhenTextTyping() {
        setButtonClickable(false);
        chatMessageEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                scrollToLastItem();
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




        }
    };

    private void bindChatDataToView(){
        if(userId == CoupleChatDAO.INVALID_ID){
            userId = CoupleChatDAO.getInstance().getChatContentId(user.getMessageIndex());
            if(userId == CoupleChatDAO.INVALID_ID){
                return;
            }
        }
        List<ChatContent> chatResults = CoupleChatDAO.getInstance().searchDataColumns();
        adapter.addAllMessage(chatResults);
    }




}
