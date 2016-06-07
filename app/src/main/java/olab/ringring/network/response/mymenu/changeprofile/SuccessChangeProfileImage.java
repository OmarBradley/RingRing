package olab.ringring.network.response.mymenu.changeprofile;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-31.
 */
@Data
public class SuccessChangeProfileImage {

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("ORIGINAL_PICTURE_PATH")
    private String originalPicturePath;

    @SerializedName("THUMNAIL_PICTURE_PATH")
    private String thumnailPicturePath;
}
