package olab.ringring.network.response.ring.intro;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-27.
 */
@Data
public class RingIntroResult {

    @SerializedName("RING_INDEX")
    private int ringIndex;

    @SerializedName("COUPLE_ID")
    private int coupleId;

    @SerializedName("RING_SHAPE")
    private String ringShape;

    @SerializedName("RING_SHAPE_LEVEL")
    private String ringShapeLevel;

    @SerializedName("RING_MATERIAL")
    private String ringMaterial;

    @SerializedName("RING_MATERIAL_NAME")
    private String ringMaterialName;

    @SerializedName("RING_JEWELRY")
    private String ringJewelry;

    @SerializedName("RING_JEWELRY_LEVEL")
    private String ringJewelryLevel;

    @SerializedName("COUPLE_EXP")
    private String coupleExp;

}
