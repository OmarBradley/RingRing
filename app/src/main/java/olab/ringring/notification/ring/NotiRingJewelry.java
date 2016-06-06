package olab.ringring.notification.ring;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-06-06.
 */
public enum NotiRingJewelry {

    PERL(R.drawable.noti_perl_image),
    CRYSTAL(R.drawable.noti_crystal_image),
    EMERALD(R.drawable.noti_emerald_image),
    SAPPHIRE(R.drawable.noti_sapphire_image),
    RUBY(R.drawable.noti_ruby_image),
    DIAMOND(R.drawable.noti_diamond_image);

    NotiRingJewelry(@DrawableRes int imageRes) {
        this.image = ContextCompat.getDrawable(RingRingApplication.getContext(), imageRes);
    }

    @Getter private Drawable image;

}
