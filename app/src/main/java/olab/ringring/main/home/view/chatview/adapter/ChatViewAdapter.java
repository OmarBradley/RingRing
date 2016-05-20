package olab.ringring.main.home.view.chatview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.home.data.ChatContent;
import olab.ringring.main.home.view.chatview.viewholder.ChatDayViewHolder;
import olab.ringring.main.home.view.chatview.viewholder.LoverChatViewHolder;
import olab.ringring.main.home.view.chatview.viewholder.UserChatViewHolder;

/**
 * Created by 재화 on 2016-05-19.
 */
public class ChatViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_USER_CHAT = 100;
    private static final int VIEW_LOVER_CHAT = 200;
    private static final int VIEW_CHAT_DAY = 300;

    List<ChatContent> chatContents = new ArrayList<>();

    public void addMessage(ChatContent chatContent) {
        chatContents.add(chatContent);
        notifyDataSetChanged();
    }

    public void addAllMessage(List<ChatContent> chatContents) {
        chatContents.addAll(chatContents);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ChatContent chatContent = chatContents.get(position);
        if (chatContent.getSenderId() == Integer.toString(HomeActivity.USER_ID)) {
            return VIEW_USER_CHAT;
        } else if (chatContent.getSenderId() == Integer.toString(HomeActivity.LOVER_ID)) {
            return VIEW_LOVER_CHAT;
        } else if (chatContent.getSenderId() == Integer.toString(HomeActivity.CHAT_DAY_ID)) {
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
        }
    }
}
