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
public class UserChatViewHolder extends RecyclerView.ViewHolder implements ChatDataSetter{

    @Bind(R.id.text_user_chat_message) TextView textUserChatMessage;
    @Bind(R.id.text_user_chat_time) TextView textUserChatTime;

    public UserChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setChatDataInChatViewHolder(ChatContent data) {
        textUserChatMessage.setText(data.getMessage());
        textUserChatTime.setText(data.getRegDate());
    }
}
