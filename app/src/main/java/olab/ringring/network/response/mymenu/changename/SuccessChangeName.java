package olab.ringring.network.response.mymenu.changename;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-27.
 */
@Data
public class SuccessChangeName {
    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("USER_NICKNAME")
    private String userNickName;

}
