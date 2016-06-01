package olab.ringring.network.response.chat;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDBConstant;
import olab.ringring.main.home.chat.moudle.localdb.RowMapper;

/**
 * Created by 재화 on 2016-05-18.
 */
@Data
public class ChatContent implements RowMapper, Serializable{
    @SerializedName("_id")
    private long messageIndex;

    @SerializedName("user_token")
    private String userToken;

    @SerializedName("sender_id")
    private String senderId;

    @SerializedName("receiver_id")
    private String receiverId;

    @SerializedName("message_content")
    private String message;

    @SerializedName("send_date")
    private String sendDate;

    @SerializedName("read_status")
    private int readStatus;

    @SerializedName("couple_id")
    private String coupleId;

    @Override
    public ContentValues mapRow() {
        ContentValues values = new ContentValues();
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._ID, messageIndex);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE, sendDate);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_MESSAGE, message);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._COUPLE_ID, coupleId);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._RECEIVER_ID, receiverId);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._SENDER_ID, senderId);
        values.put(CoupleChatDBConstant.CoupleChatTableColumn._IS_READ, readStatus);
        return values;
    }
}
