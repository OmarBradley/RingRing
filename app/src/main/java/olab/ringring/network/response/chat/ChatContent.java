package olab.ringring.network.response.chat;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDBConstant;
import olab.ringring.util.db.RowMapper;

/**
 * Created by 재화 on 2016-05-18.
 */
@Data
public class ChatContent implements RowMapper, Serializable{
    @SerializedName("SENDER_ID")
    private String senderId;

    @SerializedName("RECEIVER_ID")
    private String receiverId;

    @SerializedName("MESSAGE_CONTENT")
    private String message;

    @SerializedName("SEND_DATE")
    private long sendDate;

    @SerializedName("READ_STATUS")
    private int readStatus;

    @Override
    public ContentValues mapRow() {
        ContentValues values = new ContentValues();
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE, sendDate);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_MESSAGE, message);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._RECEIVER_ID, receiverId);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._SENDER_ID, senderId);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._IS_READ, readStatus);
        return values;
    }
}
