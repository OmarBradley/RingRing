package olab.ringring.main.home.chat.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.network.response.home.SuccessSendChat;
import olab.ringring.main.home.chat.view.viewholder.ChatDayViewHolder;
import olab.ringring.main.home.chat.view.viewholder.LoverChatViewHolder;
import olab.ringring.main.home.chat.view.viewholder.UserChatViewHolder;

/**
 * Created by 재화 on 2016-05-19.
 */
public class ChatViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_USER_CHAT = 100;
    private static final int VIEW_LOVER_CHAT = 200;
    private static final int VIEW_CHAT_DAY = 300;

    List<SuccessSendChat> chatContents = new ArrayList<>();

    public void addMessage(SuccessSendChat successSendChat) {
        chatContents.add(successSendChat);
        notifyDataSetChanged();
    }

    public void addAllMessage(List<SuccessSendChat> successSendChats) {
        this.chatContents.addAll(successSendChats);
        notifyDataSetChanged();
    }

    // TODO: 2016-06-04 id와 비교로직 구현하기
    @Override
    public int getItemViewType(int position) {
        SuccessSendChat successSendChat = chatContents.get(position);
        if (successSendChat.getSenderId() == null || successSendChat.getSenderId().equals(""+HomeActivity.USER_ID)) {
            return VIEW_USER_CHAT;
        } else if (successSendChat.getSenderId().equals(""+HomeActivity.LOVER_ID)) {
            return VIEW_LOVER_CHAT;
        } else if (successSendChat.getSenderId().equals(Integer.toString(HomeActivity.CHAT_DAY_ID))) {
            return VIEW_CHAT_DAY;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return chatContents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_USER_CHAT:
                return new UserChatViewHolder(inflater.inflate(R.layout.view_user_chat, parent, false));
            case VIEW_LOVER_CHAT:
                return new LoverChatViewHolder(inflater.inflate(R.layout.view_lover_chat, parent, false));
            case VIEW_CHAT_DAY:
                return new ChatDayViewHolder(inflater.inflate(R.layout.view_chat_day, parent, false));
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_USER_CHAT:
                ((UserChatViewHolder) holder).setChatDataInChatViewHolder(chatContents.get(position));
                break;
            case VIEW_LOVER_CHAT:
                ((LoverChatViewHolder) holder).setChatDataInChatViewHolder(chatContents.get(position));
                break;
            case VIEW_CHAT_DAY:
                ((ChatDayViewHolder) holder).setChatDataInChatViewHolder(chatContents.get(position));
                break;
        }
    }





}
