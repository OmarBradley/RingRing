package olab.ringring.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-26.
 */
@Data
public class Success<T> {

    @SerializedName("SUCCESS")
    private int success;

    @SerializedName("MESSAGE")
    private String message;

    @SerializedName("RESULT")
    private List<T> userResults;
}
