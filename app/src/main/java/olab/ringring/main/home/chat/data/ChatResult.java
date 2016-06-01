package olab.ringring.main.home.chat.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import olab.ringring.network.response.chat.ChatContent;

/**
 * Created by 재화 on 2016-05-18.
 */
@Data
public class ChatResult {
    @SerializedName("user_nickname")
    private String userNickname;

    @SerializedName("lover_nickname")
    private String loverNickname;

    @SerializedName("user_profile")
    private String userProfile;

    @SerializedName("lover_profile")
    private String loverProfile;

    @SerializedName("couple_during_date")
    private String coupleDuringDate;

    @SerializedName("ring_jewelry")
    private String ringJewelry;

    @SerializedName("ring_shape")
    private String ringShape;

    @SerializedName("ring_material")
    private String ringMaterial;

    @SerializedName("chat_content")
    private List<ChatContent> chatContents;
}
