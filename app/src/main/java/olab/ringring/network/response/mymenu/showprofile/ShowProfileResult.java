package olab.ringring.network.response.mymenu.showprofile;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ShowProfileResult implements java.io.Serializable {
    private static final long serialVersionUID = 2571775707988167391L;

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("USER_PROFILE")
    private String userProfile;

    @SerializedName("USER_PROFILE_THUMNAIL")
    private String userProfileThumail;

}
