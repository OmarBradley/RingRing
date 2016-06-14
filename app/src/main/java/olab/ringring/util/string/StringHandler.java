package olab.ringring.util.string;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by 재화 on 2016-06-10.
 */
public class StringHandler {

    public static final boolean isCorrectImageUrl(String imageUrl){
        if(!TextUtils.isEmpty(imageUrl) && imageUrl.contains("http")){
            return true;
        } else {
            return false;
        }
    }

}
