package olab.ringring.network.response.ring.select.shape;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-29.
 */
@Data
public class SuccessSelectShape {

    @SerializedName("RING_INDEX")
    private int ringIndex;

    @SerializedName("COUPLE_ID")
    private int coupleId;

    @SerializedName("RING_SHAPE")
    private String ringShape;

}
