package olab.ringring.main.ringdesign.ringattribute.shape;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-23.
 */

public enum RingShape {

    CIRCLE(R.drawable.test_big, R.drawable.test_small), TRIANGLE(R.drawable.test_big2, R.drawable.test_small1),
    RECTANGLE(R.drawable.test_big, R.drawable.test_small), PENTAGON(R.drawable.test_big2, R.drawable.test_small1),
    HEXAGON(R.drawable.test_big, R.drawable.test_small), OCTAGON(R.drawable.test_big2, R.drawable.test_small1);

    RingShape(@DrawableRes int ringShapeBigImageRes, @DrawableRes int ringShapeSmallImageRes){
        this.ringShapeBigImageRes = ringShapeBigImageRes;
        this.ringShapeSmallImageRes = ringShapeSmallImageRes;
    }

    @Getter @Setter private @DrawableRes int ringShapeBigImageRes;
    @Getter @Setter private @DrawableRes int ringShapeSmallImageRes;

}
