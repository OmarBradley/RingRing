package olab.ringring.network.response.mymenu.intro;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-26.
 */
@Data
public class MyMenuIntroResult {

    @SerializedName("USER_PROFILE")
    private String userProfile;

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("USER_NICKNAME")
    private String userNickname;

    @SerializedName("COUPLE_INDEX")
    private int coupleIndex;

    @SerializedName("COUPLE_USER_WOMAN")
    private int coupleUserWoman;

    @SerializedName("COUPLE_USER_MAN")
    private int coupleUserMan;

    @SerializedName("COUPLE_EXP")
    private String coupleExp;

    @SerializedName("LOVER_INDEX")
    private int loverIndex;

    @SerializedName("LOVER_NICKNAME")
    private String loverNickname;

    @SerializedName("COUPLERING")
    private List<MyMenuIntroCoupleRing> coupleRings;


}
