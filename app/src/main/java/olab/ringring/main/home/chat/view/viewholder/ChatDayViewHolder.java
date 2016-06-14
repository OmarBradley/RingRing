package olab.ringring.main.home.chat.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.network.response.home.SuccessSendChat;
import olab.ringring.util.date.NowDateGetter;

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
    public void setChatDataInChatViewHolder(SuccessSendChat data) {
        Log.e("time", new DateTime(data.getSendDate()).getYear()+"");
        chatDayText.setText(new NowDateGetter().getNowDayString(new DateTime(data.getSendDate())));
    }
}
