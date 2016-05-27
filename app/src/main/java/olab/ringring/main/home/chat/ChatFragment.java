package olab.ringring.main.home.chat;


import android.content.Context;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import olab.ringring.R;
import olab.ringring.main.home.chat.data.ChatContent;
import olab.ringring.main.home.chat.view.adapter.ChatViewAdapter;
import olab.ringring.util.date.NowDateGetter;
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ChatFragment extends Fragment {

    public static final int USER_ID = 1;
    public static final int LOVER_ID = 2;
    public static final int CHAT_DAY_ID = 3;

    @Bind(R.id.list_view_chat_message) RecyclerView chatMessageListView;
    @Bind(R.id.btn_user) Button userBtn;
    @Bind(R.id.btn_lover) Button loverBtn;
    @Bind(R.id.btn_chat_date) Button chatDateBtn;
    @Bind(R.id.edit_chat_message) EditText chatMessageEdit;
    ChatViewAdapter adapter;

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

    // TODO: 2016-05-20 실제 서버 데이터 삽입 시 변하게 될 부분임
    private void setOnClickListenerOnButtons() {
        userBtn.setOnClickListener(view -> {
            hideSoftInput(view);
            ChatContent chatContent = new ChatContent();
            chatContent.setMessage(chatMessageEdit.getText().toString());
            chatContent.setSenderId("" + USER_ID);
            chatContent.setRegDate(new NowDateGetter().getChatTimeString());
            adapter.addMessage(chatContent);
            scrollToLastItem();
            deleteStringInChatMessageEditView();
        });
        loverBtn.setOnClickListener(view -> {
            hideSoftInput(view);
            ChatContent chatContent = new ChatContent();
            chatContent.setMessage(chatMessageEdit.getText().toString());
            chatContent.setSenderId("" + LOVER_ID);
            chatContent.setRegDate(new NowDateGetter().getChatTimeString());
            adapter.addMessage(chatContent);
            scrollToLastItem();
            deleteStringInChatMessageEditView();
        });
        chatDateBtn.setOnClickListener(view -> {
            hideSoftInput(view);
            ChatContent chatContent = new ChatContent();
            chatContent.setSenderId("" + CHAT_DAY_ID);
            chatContent.setRegDate(new NowDateGetter().getNowDayString());
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
        userBtn.setEnabled(isClickable);
        loverBtn.setEnabled(isClickable);
        chatDateBtn.setEnabled(isClickable);
    }

}
