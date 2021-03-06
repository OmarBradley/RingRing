package olab.ringring.main.home.chat.view.viewholder;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.home.chat.moudle.ReadStatus;
import olab.ringring.network.response.home.SuccessSendChat;
import olab.ringring.util.date.NowDateGetter;

/**
 * Created by 재화 on 2016-05-19.
 */
public class UserChatViewHolder extends RecyclerView.ViewHolder implements ChatDataSetter{

    @Bind(R.id.text_user_chat_message) TextView userChatMessageText;
    @Bind(R.id.text_user_chat_time) TextView userChatTimeText;

    public UserChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setChatDataInChatViewHolder(SuccessSendChat data) {
        userChatMessageText.setText(data.getMessage());
        userChatTimeText.setText(new NowDateGetter().getChatTimeString(data.getSendDate()));
        if(data.getReadStatus() == ReadStatus.READ.getReadStatus()){
            userChatMessageText.setBackgroundResource(R.drawable.user_chat_read_bubble_image);
            userChatMessageText.setTextColor(ContextCompat.getColor(RingRingApplication.getContext(), ReadStatus.READ_STATE_COLOR));
        } else {
            userChatMessageText.setBackgroundResource(R.drawable.user_chat_unread_bubble_image);
            userChatMessageText.setTextColor(ContextCompat.getColor(RingRingApplication.getContext(), ReadStatus.UNREAD_STATE_COLOR));
        }
    }
}
