package olab.ringring.network.response.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by 재화 on 2016-06-08.
 */
@Data
public class SuccessReceiveChat {

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("LOVERS_INDEX")
    private int loverIndex;

    @SerializedName("MESSAGE_CONTENTS")
    private List<SuccessSendChat> messageContents;

    public SuccessSendChat getLastMessage(){
        return messageContents.get(messageContents.size()-1);
    }

}
