package olab.ringring.network.response.users;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by 재화 on 2016-06-03.
 */
@Data
public class ValidateSuccess implements Serializable{

    @SerializedName("SUCCESS")
    private int success;

    @SerializedName("MESSAGE")
    private String message;

    @SerializedName("RESULT")
    private String result;

}
