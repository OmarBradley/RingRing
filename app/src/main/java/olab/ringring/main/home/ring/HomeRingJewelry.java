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
public enum HomeRingJewelry {
    PERL(R.drawable.chatting_perl_image),
    CRYSTAL(R.drawable.chatting_crystal_image),
    EMERALD(R.drawable.chatting_emerald_image),
    SAPPHIRE(R.drawable.chatting_sapphire_image),
    RUBY(R.drawable.chatting_ruby_image),
    DIAMOND(R.drawable.chatting_diamond_image);

    HomeRingJewelry(@DrawableRes int imageRes) {
        this.image = ContextCompat.getDrawable(RingRingApplication.getContext(), imageRes);
    }

    @Getter private Drawable image;
}
