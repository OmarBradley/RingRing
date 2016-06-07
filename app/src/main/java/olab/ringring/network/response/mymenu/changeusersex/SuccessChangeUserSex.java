package olab.ringring.network.response.mymenu.changeusersex;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-27.
 */
@Data
public class SuccessChangeUserSex {

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("USER_SEX")
    private String userSex;

}
