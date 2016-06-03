package olab.ringring.network.response.users;


import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SuccessSignUp implements java.io.Serializable {
    private static final long serialVersionUID = 6335234706404379579L;

    @SerializedName("USER_PASSWORD")
    private String userPassword;

    @SerializedName("USER_EMAIL")
    private String userEmail;

    @SerializedName("USER_PHONE_NUMBER")
    private String userPhoneNumber;

}
