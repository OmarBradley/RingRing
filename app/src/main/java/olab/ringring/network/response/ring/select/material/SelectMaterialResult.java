package olab.ringring.network.response.ring.select.material;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SelectMaterialResult {

    @SerializedName("COUPLE_ID")
    private int coupleId;

    @SerializedName("RING_INDEX")
    private int ringIndex;

    @SerializedName("RING_MATERIAL")
    private String ringMaterial;
}
