package olab.ringring.network.response.users;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SuccessLogin implements java.io.Serializable {
    private static final long serialVersionUID = -1653884318841705587L;

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("USER_EMAIL")
    private String userEmail;

    @SerializedName("LOGIN_CASE")
    private int loginCase;
}
