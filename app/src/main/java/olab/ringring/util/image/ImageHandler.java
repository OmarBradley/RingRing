package olab.ringring.util.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-23.
 */
public class ImageHandler {

    private static final @ColorRes int WHITE_COLOR = ContextCompat.getColor(RingRingApplication.getContext(), android.R.color.white);
    public static final int MAX_RESOLUTION = 320;

    public static Bitmap changeImageColor(Bitmap sourceBitmap, @ColorRes int color) {
        Bitmap resultBitmap = resizeBitmapImage(sourceBitmap, MAX_RESOLUTION);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
        return resultBitmap;
    }

    public static Drawable covertBitmapToDrawable(Context context, Bitmap bitmap) {
        Drawable d = new BitmapDrawable(context.getResources(), bitmap);
        return d;
    }

    public static Bitmap convertDrawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = resizeBitmapImage(Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888), MAX_RESOLUTION);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap changeImageColor(Drawable drawable, @ColorRes int color) {
        Bitmap drawableToBitmap = convertDrawableToBitmap(drawable);
        return changeImageColor(drawableToBitmap, color);
    }

    ;

    public static Drawable changeDrawableImageColor(Drawable drawable, @ColorRes int color) {
        Bitmap drawableToBitmap = convertDrawableToBitmap(drawable);
        return covertBitmapToDrawable(RingRingApplication.getContext(), changeImageColor(drawableToBitmap, color));
    }

    ;

    public static Drawable changeWhiteImage(Drawable drawable) {
        Bitmap drawableToBitmap = convertDrawableToBitmap(drawable);
        return covertBitmapToDrawable(RingRingApplication.getContext(), changeImageColor(drawableToBitmap, WHITE_COLOR));
    }


    public static Bitmap resizeBitmapImage(Bitmap bmpSource, int maxResolution) {
        int iWidth = bmpSource.getWidth();
        int iHeight = bmpSource.getHeight();
        int newWidth = iWidth;
        int newHeight = iHeight;
        float rate = 0.0f;

        if (iWidth > iHeight) {
            if (maxResolution < iWidth) {
                rate = maxResolution / (float) iWidth;
                newHeight = (int) (iHeight * rate);
                newWidth = maxResolution;
            }
        } else {
            if (maxResolution < iHeight) {
                rate = maxResolution / (float) iHeight;
                newWidth = (int) (iWidth * rate);
                newHeight = maxResolution;
            }
        }

        return Bitmap.createScaledBitmap(
                bmpSource, newWidth, newHeight, true);
    }
}
