package olab.ringring.main.home.chat.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.network.response.chat.ChatContent;

/**
 * Created by 재화 on 2016-05-19.
 */
public class LoverChatViewHolder extends RecyclerView.ViewHolder implements ChatDataSetter {

    @Bind(R.id.text_lover_chat_message) TextView loverChatMessageText;
    @Bind(R.id.text_lover_chat_time) TextView loverChatTimeText;

    public LoverChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setChatDataInChatViewHolder(ChatContent data) {
        loverChatMessageText.setText(data.getMessage());
        loverChatTimeText.setText(data.getSendDate());
    }
}
