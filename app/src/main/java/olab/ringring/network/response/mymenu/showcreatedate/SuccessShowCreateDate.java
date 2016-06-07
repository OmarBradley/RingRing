package olab.ringring.network.response.mymenu.showcreatedate;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SuccessShowCreateDate implements java.io.Serializable {
    private static final long serialVersionUID = -8461237395409422492L;

    @SerializedName("COUPLE_INDEX")
    private int coupleIndex;

    @SerializedName("COUPLE_CREATED_DATE")
    private long coupleCreateDate;
}
