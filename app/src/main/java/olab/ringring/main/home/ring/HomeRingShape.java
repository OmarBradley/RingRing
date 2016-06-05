package olab.ringring.main.home.ring;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-06-05.
 */
public enum HomeRingShape {
    CIRCLE(R.drawable.chatting_circle_image),
    TRIANGLE(R.drawable.chatting_triangle_image),
    RECTANGLE(R.drawable.chatting_rectangle_image),
    PENTAGON(R.drawable.chatting_pentagon_image),
    HEXAGON(R.drawable.chatting_hexagon_image),
    OCTAGON(R.drawable.chatting_octagon_image);

    HomeRingShape(@DrawableRes int imageRes) {
        this.image = ContextCompat.getDrawable(RingRingApplication.getContext(), imageRes);
    }

    @Getter private Drawable image;
}
