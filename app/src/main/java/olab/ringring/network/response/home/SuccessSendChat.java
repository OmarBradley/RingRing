package olab.ringring.network.response.home;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;

import lombok.Data;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDBConstant;
import olab.ringring.network.response.BundleMapper;
import olab.ringring.util.db.CursorMapper;
import olab.ringring.util.db.RowMapper;

/**
 * Created by 재화 on 2016-05-18.
 */
@Data
public class SuccessSendChat implements RowMapper, Serializable, BundleMapper<SuccessSendChat>, CursorMapper<SuccessSendChat>{
    @SerializedName("SENDER_ID")
    private int senderId;

    @SerializedName("RECEIVER_ID")
    private int receiverId;

    @SerializedName("MESSAGE_CONTENT")
    private String message;

    @SerializedName("SEND_DATE")
    private long sendDate;

    @SerializedName("READ_STATUS")
    private int readStatus;

    @SerializedName("LOVER_NICKNAME")
    private String loverNickname;

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

    @Override
    public SuccessSendChat mapBundle(Bundle data) {
        SuccessSendChat successSendChat = new SuccessSendChat();
        successSendChat.setSenderId(data.getInt("SENDER_ID"));
        successSendChat.setReceiverId(data.getInt("RECEIVER_ID"));
        successSendChat.setMessage(data.getString("MESSAGE_CONTENT"));
        successSendChat.setSendDate(DateTime.now().getMillis());
        successSendChat.setReadStatus(data.getInt("READ_STATUS"));
        successSendChat.setLoverNickname(data.getString("LOVER_NICKNAME"));
        return successSendChat;
    }

    @Override
    public SuccessSendChat mapCursor(Cursor cursor) {
        SuccessSendChat data = new SuccessSendChat();
        data.setSendDate(cursor.getLong(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE)));
        data.setMessage(cursor.getString(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_MESSAGE)));
        data.setReceiverId(cursor.getInt(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._RECEIVER_ID)));
        data.setSenderId(cursor.getInt(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._SENDER_ID)));
        data.setReadStatus(cursor.getInt(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._IS_READ)));
        return data;
    }
}
