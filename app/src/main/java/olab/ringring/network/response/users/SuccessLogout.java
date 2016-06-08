package olab.ringring.network.response.users;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SuccessLogout implements java.io.Serializable {
    private static final long serialVersionUID = -210962535030052926L;

    @SerializedName("USER_INDEX")
    private int userIndex;
}
