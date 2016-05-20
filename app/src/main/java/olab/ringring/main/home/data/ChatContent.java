package olab.ringring.main.home.data;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-18.
 */
@Data
public class ChatContent {
    @SerializedName("message_index")
    private int messageIndex;

    private String message;

    @SerializedName("sender_id")
    private String senderId;

    @SerializedName("receiver_id")
    private String receiverId;

    @SerializedName("reg_date")
    private String regDate;

    private int status;
}
