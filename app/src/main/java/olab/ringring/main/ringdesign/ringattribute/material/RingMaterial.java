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

    PLASTIC(R.color.colorAccent, R.mipmap.ic_launcher), IRON(R.color.colorPrimary, R.mipmap.ic_launcher),
    COPPER(R.color.colorPrimary, R.mipmap.ic_launcher), SILVER(R.color.colorRipple, R.mipmap.ic_launcher),
    WHITEGOLD(R.color.colorPrimaryDark, R.mipmap.ic_launcher), GOLD(R.color.colorBackground, R.mipmap.ic_launcher);

    RingMaterial(@ColorRes int ringMaterialColorRes, @DrawableRes int ringMaterialSetViewImageRes) {
        this.ringMaterialColor = ContextCompat.getColor(RESOURCE_CONTEXT, ringMaterialColorRes);
        this.ringMaterialSetViewImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, ringMaterialSetViewImageRes);
    }

    @Getter @Setter private @ColorRes int ringMaterialColor;
    @Getter @Setter private @DrawableRes Drawable ringMaterialSetViewImage;
    private final Context RESOURCE_CONTEXT = RingRingApplication.getContext();

}
