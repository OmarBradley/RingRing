package olab.ringring.util.string;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by 재화 on 2016-06-10.
 */
public class StringHandler {

    public static final boolean isCorrectImageUrl(String imageUrl){
        Log.e("imageUrl", imageUrl);
        if(!TextUtils.isEmpty(imageUrl) && !imageUrl.equals(" -")){
            Log.e("imageUrlf", imageUrl);
            return true;
        } else {
            Log.e("imageUrlT", imageUrl);
            return false;
        }
    }

}
