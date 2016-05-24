package olab.ringring.main.ringdesign.ringattribute.shape;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.util.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-23.
 */

public enum RingShape {

    CIRCLE(R.drawable.test_big, R.drawable.test_small, R.mipmap.ic_launcher), TRIANGLE(R.drawable.test_big, R.drawable.test_small, R.mipmap.ic_launcher),
    RECTANGLE(R.drawable.test_big, R.drawable.test_small, R.mipmap.ic_launcher), PENTAGON(R.drawable.test_big, R.drawable.test_small, R.mipmap.ic_launcher),
    HEXAGON(R.drawable.test_big, R.drawable.test_small, R.mipmap.ic_launcher), OCTAGON(R.drawable.test_big, R.drawable.test_small, R.mipmap.ic_launcher);

    RingShape(@DrawableRes int ringShapeBigImageRes, @DrawableRes int ringShapeSmallImageRes, @DrawableRes int ringShapeSetViewImageRes) {
        this.ringShapeBigImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringShapeBigImageRes);
        this.ringShapeSmallImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringShapeSmallImageRes);
        this.ringShapeSetViewImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringShapeSetViewImageRes);
    }

    @Getter @Setter private @DrawableRes Drawable ringShapeBigImage;
    @Getter @Setter private @DrawableRes Drawable ringShapeSmallImage;
    @Getter @Setter private @DrawableRes Drawable ringShapeSetViewImage;
    private final Context RESOURCE_CONTEXT = RingRingApplication.getContext();
}
