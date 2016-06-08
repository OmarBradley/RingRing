package olab.ringring.network.response.users;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class FailLoverCertification implements java.io.Serializable {
    private static final long serialVersionUID = 667790577576236730L;

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("FAILURE_CASE")
    private int failureCase;
}
