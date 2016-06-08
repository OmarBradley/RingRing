package olab.ringring.network.response.home;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ReceiveContent implements java.io.Serializable {
    private static final long serialVersionUID = -6692964485160805982L;

    @SerializedName("SENDER_ID")
    private int senderId;

    @SerializedName("READ_STATUS")
    private int readStatus;

    @SerializedName("RECEIVER_ID")
    private int receiverId;

    @SerializedName("SEND_DATE")
    private long sendDate;

    @SerializedName("MESSAGE_CONTENT")
    private String messageContent;
}
