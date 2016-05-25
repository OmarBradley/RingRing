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

    PERL(android.R.drawable.ic_menu_mapmode, R.mipmap.ic_launcher, "진주"), CRYSTAL(android.R.drawable.ic_menu_help, R.mipmap.ic_launcher, "수정"),
    EMERALD(android.R.drawable.ic_menu_camera, R.mipmap.ic_launcher, "에메랄드"), SAPPHIRE(android.R.drawable.ic_input_get, R.mipmap.ic_launcher, "사파이어"),
    RUBY(android.R.drawable.ic_delete, R.mipmap.ic_launcher, "루비"), DIAMOND(android.R.drawable.ic_input_add, R.mipmap.ic_launcher, "다이아몬드");

    RingJewelry(@DrawableRes int ringJewelryImageRes, @DrawableRes int ringJewelrySetViewImageRes, String ringJewelryName) {
        this.ringJewelryImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringJewelryImageRes);
        this.ringJewelrySetViewImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringJewelrySetViewImageRes);
        this.ringJewelryName = ringJewelryName;
    }

    @Setter @Getter private Drawable ringJewelryImage;
    @Setter @Getter private Drawable ringJewelrySetViewImage;
    @Setter @Getter private String ringJewelryName;
    private final Context RESOURCE_CONTEXT = RingRingApplication.getContext();
}
