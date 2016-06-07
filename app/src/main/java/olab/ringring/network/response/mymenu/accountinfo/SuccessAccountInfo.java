package olab.ringring.network.response.mymenu.accountinfo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-27.
 */
@Data
public class SuccessAccountInfo {

    @SerializedName("USER_PROFILE")
    private String userProfile;

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("USER_NAME")
    private String userName;

    @SerializedName("USER_NICKNAME")
    private String userNickName;

    @SerializedName("USER_SEX")
    private String userSex;

    @SerializedName("USER_PHONE_NUMBER")
    private String userPhoneNumber;

    @SerializedName("USER_EMAIL")
    private String userEmail;

    @SerializedName("PASSWORD")
    private String password;

    @SerializedName("coupleIndex")
    private int coupleIndex;

    @SerializedName("COUPLE_USER_WOMAN")
    private int coupleUserWoman;

    @SerializedName("COUPLE_USER_MAN")
    private int coupleUserMan;

    @SerializedName("LOVER_INDEX")
    private int loverIndex;

    @SerializedName("LOVER_NICKNAME")
    private String loverNickname;

}
