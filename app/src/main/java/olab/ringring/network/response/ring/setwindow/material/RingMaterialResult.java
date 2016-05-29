package olab.ringring.network.response.ring.setwindow.material;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-29.
 */
@Data
public class RingMaterialResult {

    @SerializedName("RING_INDEX")
    private int ringIndex;

    @SerializedName("COUPLE_ID")
    private int coupleId;

    @SerializedName("RING_MATERIAL")
    private String ringMaterial;

    @SerializedName("RING_MATERIAL_LEVEL")
    private String ringMaterialLevel;

    @SerializedName("MATERIAL_PLASTIC_LEVEL")
    private String materialPlasticLevel;

    @SerializedName("MATERIAL_IRON_LEVEL")
    private String materialIronLevel;

    @SerializedName("MATERIAL_COPPER_LEVEL")
    private String materialCopperLevel;

    @SerializedName("MATERIAL_SILVER_LEVEL")
    private String materialSilverLevel;

    @SerializedName("MATERIAL_WHITEGOLD_LEVEL")
    private String materialWhiteGoldLevel;

    @SerializedName("MATERIAL_GOLD_LEVEL")
    private String materialGoldLevel;
}
