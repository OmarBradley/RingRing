package olab.ringring.main.mymenu.ringattribute;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-30.
 */
public enum MyMenuRingShape {

    CIRCLE(R.drawable.my_menu_ring_circie_image),
    TRIANGLE(R.drawable.my_menu_ring_triangle_image),
    RECTANGLE(R.drawable.my_menu_ring_rectangle_image),
    PENTAGON(R.drawable.my_menu_ring_pentagon_image),
    HEXAGON(R.drawable.my_menu_ring_hexagon_image),
    OCTAGON(R.drawable.my_menu_ring_octagon_image);

    MyMenuRingShape(@DrawableRes int ringShapeRes){
        this.ringShape = ContextCompat.getDrawable(RingRingApplication.getContext(), ringShapeRes);
    }

    @Getter private Drawable ringShape;
}
