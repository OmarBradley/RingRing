package olab.ringring.network.response.mymenu.changecreatedate;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SuccessChangeCreateDate implements java.io.Serializable {
    private static final long serialVersionUID = 5159339154943815739L;

    @SerializedName("COUPLE_INDEX")
    private String coupleIndex;

    @SerializedName("COUPLE_CREATED_DATE")
    private long coupleCreatedDate;

}
