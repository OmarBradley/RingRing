package olab.ringring.main.mymenu.ringattribute;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;

/**
 * Created by 재화 on 2016-05-30.
 */
public enum MyMenuRingJewelry {

    PERL(R.drawable.my_menu_ring_perl_image),
    CRYSTAL(R.drawable.my_menu_ring_crystal_image),
    EMERALD(R.drawable.my_menu_ring_emerald_image),
    SAPPHIRE(R.drawable.my_menu_ring_sapphire_image),
    RUBY(R.drawable.my_menu_ring_ruby_image),
    DIAMOND(R.drawable.my_menu_ring_diamond_image);

    MyMenuRingJewelry(@DrawableRes int ringJewelryRes) {
        this.ringJewelry = ContextCompat.getDrawable(RingRingApplication.getContext(), ringJewelryRes);
    }

    @Getter private Drawable ringJewelry;

}
