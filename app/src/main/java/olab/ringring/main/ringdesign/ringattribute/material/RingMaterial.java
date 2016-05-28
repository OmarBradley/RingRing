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
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;
import olab.ringring.util.colorchanger.ImageColorChanger;

/**
 * Created by 재화 on 2016-05-23.
 */
public enum RingMaterial {

    PLASTIC(R.color.colorPlastic, R.drawable.plastic_image, "플라스틱"), IRON(R.color.colorIron, R.drawable.iron_image, "철"),
    COPPER(R.color.colorCopper, R.drawable.copper_image, "동"), SILVER(R.color.colorSliver, R.drawable.sliver_image, "은"),
    WHITEGOLD(R.color.colorWhiteGold, R.drawable.white_gold_image, "화이트골드"), GOLD(R.color.colorGold, R.drawable.gold_image,"금");

    RingMaterial(@ColorRes int colorRes, @DrawableRes int choiceImageRes, String name) {
        this.color = ContextCompat.getColor(RingRingApplication.getContext(), colorRes);
        this.image = ContextCompat.getDrawable(RingRingApplication.getContext(), choiceImageRes);
        this.name = name;
    }

    @Getter @Setter private @ColorRes int color;
    @Getter @Setter private @DrawableRes Drawable image;
    @Getter @Setter private String name;

    private static final @ColorRes int NOT_SELECTED_MATERIAL_COLOR = R.color.colorDefault;
    private static final @DrawableRes int NOT_SELECTED_MATERIAL_IMAGE = R.drawable.choice_dialog_material_not_selected_image;

    public static final Drawable getMaterialImageUsingChoiceDialog(RingMaterial material, RingCollectCount count) {
        if (count == RingCollectCount.MAX_COUNTING_NUMBER) {
            return material.getImage();
        } else {
            return ImageColorChanger.changeDrawableImageColor(ContextCompat.getDrawable(RingRingApplication.getContext(), NOT_SELECTED_MATERIAL_IMAGE),
                    ContextCompat.getColor(RingRingApplication.getContext(), NOT_SELECTED_MATERIAL_COLOR));
        }
    }
}
