package olab.ringring.network.response.mymenu.home;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-26.
 */
@Data
public class MyMenuIntroCoupleRing {

    @SerializedName("RING_INDEX")
    private int ringIndex;

    @SerializedName("RING_SIZE")
    private int ringSize;

    @SerializedName("RING_JEWELRY")
    private String ringJewelry;

    @SerializedName("RING_SHAPE")
    private String ringShape;

    @SerializedName("RING_MATERIAL")
    private String ringMaterial;
}
