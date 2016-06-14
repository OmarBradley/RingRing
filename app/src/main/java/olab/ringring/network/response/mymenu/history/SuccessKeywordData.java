package olab.ringring.network.response.mymenu.history;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SuccessKeywordData implements java.io.Serializable {
    private static final long serialVersionUID = 2157496465976902076L;

    @SerializedName("COUPLE_INDEX")
    private String coupleIndex;

    @SerializedName("LOVE_KEYWORD")
    private int loveKeyword;

    @SerializedName("CUTE_KEYWORD")
    private int cuteKeyword;

    @SerializedName("CALL_KEYWORD")
    private int callKeyword;

    @SerializedName("PRETTY_KEYWORD")
    private int prettyKeyword;

    @SerializedName("GOOD_KEYWORD")
    private int goodKeyword;

    @SerializedName("GOODNIGHT_KEYWORD")
    private int goodNightKeyword;

    @SerializedName("BEATING_KEYWORD")
    private int beatingKeyword;

    @SerializedName("HANDSOME_KEYWORD")
    private int handsomeKeyword;
}
