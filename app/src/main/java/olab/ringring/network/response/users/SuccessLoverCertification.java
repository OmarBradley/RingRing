package olab.ringring.network.response.users;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SuccessLoverCertification implements java.io.Serializable {
    private static final long serialVersionUID = -5010483442399508296L;

    @SerializedName("CERTIFICATION_CASE")
    private int certificationCase;

    @SerializedName("USER_INDEX")
    private int userIndex;

    @SerializedName("LOVERS_INDEX")
    private int loverIndex;
}
