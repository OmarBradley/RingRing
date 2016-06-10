package olab.ringring.notification.ring;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.util.image.ImageHandler;

/**
 * Created by 재화 on 2016-06-06.
 */
public enum NotiRingShape {
    CIRCLE(R.drawable.noti_circle_image),
    TRIANGLE(R.drawable.noti_triangle_image),
    RECTANGLE(R.drawable.noti_rectangle_image),
    PENTAGON(R.drawable.noti_pentagon_image),
    HEXAGON(R.drawable.noti_hexagon_image),
    OCTAGON(R.drawable.noti_octagon_image);

    NotiRingShape(@DrawableRes int imageRes) {
        this.image = resizeDrawable(imageRes);
    }

    @Getter private Drawable image;

    private Drawable resizeDrawable(@DrawableRes int imageRes){
        Bitmap originImage = ImageHandler.convertDrawableToBitmap(ContextCompat.getDrawable(RingRingApplication.getContext(), imageRes));
        Bitmap resizedImage = ImageHandler.resizeBitmapImage(originImage,  ImageHandler.MAX_RESOLUTION);
        return ImageHandler.covertBitmapToDrawable(RingRingApplication.getContext(), resizedImage);
    }

}
