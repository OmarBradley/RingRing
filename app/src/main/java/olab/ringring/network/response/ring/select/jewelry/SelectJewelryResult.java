package olab.ringring.network.response.ring.select.jewelry;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SelectJewelryResult {

    @SerializedName("COUPLE_ID")
    private int coupleId;

    @SerializedName("RING_JEWELRY")
    private String ringJewelry;

    @SerializedName("RING_INDEX")
    private int ringIndex;

}
