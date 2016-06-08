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
public class LoverChatViewHolder extends RecyclerView.ViewHolder implements ChatDataSetter {

    @Bind(R.id.text_lover_chat_message) TextView loverChatMessageText;
    @Bind(R.id.text_lover_chat_time) TextView loverChatTimeText;

    public LoverChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setChatDataInChatViewHolder(SuccessSendChat data) {
        loverChatMessageText.setText(data.getMessage());
        loverChatTimeText.setText(new NowDateGetter().getChatTimeString(data.getSendDate()));
        if(data.getReadStatus() == ReadStatus.READ.getReadStatus()){
            loverChatMessageText.setBackgroundResource(R.drawable.lover_chat_read_bubble_image);
            loverChatMessageText.setTextColor(ContextCompat.getColor(RingRingApplication.getContext(), ReadStatus.READ_STATE_COLOR));
        } else {
            loverChatMessageText.setBackgroundResource(R.drawable.lover_chat_unread_bubble_image);
            loverChatMessageText.setTextColor(ContextCompat.getColor(RingRingApplication.getContext(), ReadStatus.UNREAD_STATE_COLOR));
        }
    }
}
