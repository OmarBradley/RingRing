package olab.ringring.main.ringdesign.ringattribute.material;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.util.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-23.
 */
public enum RingMaterial {

    PLASTIC(R.color.colorAccent, R.mipmap.ic_launcher, "플라스틱"), IRON(R.color.colorPrimary, R.mipmap.ic_launcher, "철"),
    COPPER(R.color.colorPrimary, R.mipmap.ic_launcher, "동"), SILVER(R.color.colorRipple, R.mipmap.ic_launcher, "은"),
    WHITEGOLD(R.color.colorPrimaryDark, R.mipmap.ic_launcher, "화이트골드"), GOLD(R.color.colorBackground, R.mipmap.ic_launcher,"금");

    RingMaterial(@ColorRes int ringMaterialColorRes, @DrawableRes int ringMaterialSetViewImageRes, String ringMaterialName) {
        this.ringMaterialColor = ContextCompat.getColor(RESOURCE_CONTEXT, ringMaterialColorRes);
        this.ringMaterialSetViewImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringMaterialSetViewImageRes);
        this.ringMaterialName = ringMaterialName;
    }

    @Getter @Setter private @ColorRes int ringMaterialColor;
    @Getter @Setter private @DrawableRes Drawable ringMaterialSetViewImage;
    @Getter @Setter private String ringMaterialName;
    private final Context RESOURCE_CONTEXT = RingRingApplication.getContext();

}
