package olab.ringring.main.ringdesign.ringattribute.material;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-23.
 */
public enum RingMaterial {

    PLASTIC(R.color.colorPlastic, R.drawable.plastic_image, "플라스틱"), IRON(R.color.colorIron, R.drawable.iron_image, "철"),
    COPPER(R.color.colorCopper, R.drawable.copper_image, "동"), SILVER(R.color.colorSliver, R.drawable.sliver_image, "은"),
    WHITEGOLD(R.color.colorWhiteGold, R.drawable.white_gold_image, "화이트골드"), GOLD(R.color.colorGold, R.drawable.gold_image,"금");

    RingMaterial(@ColorRes int colorRes, @DrawableRes int choiceImageRes, String name) {
        this.color = ContextCompat.getColor(RESOURCE_CONTEXT, colorRes);
        this.image = ContextCompat.getDrawable(RESOURCE_CONTEXT, choiceImageRes);
        this.name = name;
    }

    @Getter @Setter private @ColorRes int color;
    @Getter @Setter private @DrawableRes Drawable image;
    @Getter @Setter private String name;
    private final Context RESOURCE_CONTEXT = RingRingApplication.getContext();

}
