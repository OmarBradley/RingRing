package olab.ringring.network.response.ring.setwindow.jewelry;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-29.
 */
@Data
public class SuccessRingJewelry {

    @SerializedName("RING_INDEX")
    private int ringIndex;

    @SerializedName("COUPLE_ID")
    private int coupleId;

    @SerializedName("RING_JEWELRY")
    private String ringJewelry;

    @SerializedName("RING_JEWELRY_LEVEL")
    private String ringJewelryLevel;

    @SerializedName("JEWELRY_PERL_LEVEL")
    private String jewelryPerlLevel;

    @SerializedName("JEWELRY_CRYSTAL_LEVEL")
    private String jewelryCrystalLevel;

    @SerializedName("JEWELRY_EMERALD_LEVEL")
    private String jewelryEmeraldLevel;

    @SerializedName("JEWELRY_SAPPHIRE_LEVEL")
    private String jewelrySapphireLevel;

    @SerializedName("JEWELRY_RUBY_LEVEL")
    private String jewelryRubyLevel;

    @SerializedName("JEWELRY_DIAMOND_LEVEL")
    private String jewelryDiamondLevel;
}
