package olab.ringring.main.home.chat.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.chat.data.ChatContent;

/**
 * Created by 재화 on 2016-05-20.
 */
public class ChatDayViewHolder extends RecyclerView.ViewHolder implements ChatDataSetter  {

    @Bind(R.id.text_chat_day) TextView chatDayText;

    public ChatDayViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setChatDataInChatViewHolder(ChatContent data) {
        chatDayText.setText(data.getRegDate());
    }
}
