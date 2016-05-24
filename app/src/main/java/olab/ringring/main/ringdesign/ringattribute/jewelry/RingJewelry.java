package olab.ringring.main.ringdesign.ringattribute.jewelry;

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
public enum RingJewelry {

    PERL(android.R.drawable.ic_menu_upload, R.mipmap.ic_launcher), CRYSTAL(android.R.drawable.ic_menu_mapmode, R.mipmap.ic_launcher),
    EMERALD(android.R.drawable.btn_star_big_off, R.mipmap.ic_launcher), SAPPHIRE(android.R.drawable.btn_star_big_on, R.mipmap.ic_launcher),
    RUBY(android.R.drawable.ic_menu_camera, R.mipmap.ic_launcher), DIAMOND(android.R.drawable.ic_menu_help, R.mipmap.ic_launcher);

    RingJewelry(@DrawableRes int ringJewelryImageRes, @DrawableRes int ringJewelrySetViewImageRes) {
        this.ringJewelryImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringJewelryImageRes);
        this.ringJewelrySetViewImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringJewelrySetViewImageRes);
    }

    @Setter @Getter private Drawable ringJewelryImage;
    @Setter @Getter private Drawable ringJewelrySetViewImage;
    private final Context RESOURCE_CONTEXT = RingRingApplication.getContext();
}
