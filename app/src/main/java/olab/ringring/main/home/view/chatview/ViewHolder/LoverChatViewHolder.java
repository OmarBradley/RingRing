package olab.ringring.main.home.view.chatview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.data.ChatContent;

/**
 * Created by 재화 on 2016-05-19.
 */
public class LoverChatViewHolder extends RecyclerView.ViewHolder implements ChatDataSetter {

    @Bind(R.id.text_lover_chat_message) TextView textLoverChatMessage;
    @Bind(R.id.text_lover_chat_time) TextView textLoverChatTime;

    public LoverChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setChatDataInChatViewHolder(ChatContent data) {
        textLoverChatMessage.setText(data.getMessage());
        textLoverChatTime.setText(data.getRegDate());
    }
}
