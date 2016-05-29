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
import olab.ringring.main.ringdesign.choicedialog.attributeview.RingDetailAttributeViewData;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.network.response.ring.intro.RingIntroResult;
import olab.ringring.util.colorchanger.ImageColorChanger;

/**
 * Created by 재화 on 2016-05-23.
 */
public enum RingMaterial {

    PLASTIC(R.color.colorPlastic, R.drawable.plastic_image, "플라스틱", "PLASTIC"),
    IRON(R.color.colorIron, R.drawable.iron_image, "철", "IRON"),
    COPPER(R.color.colorCopper, R.drawable.copper_image, "동", "COPPER"),
    SILVER(R.color.colorSliver, R.drawable.sliver_image, "은", "SILVER"),
    WHITEGOLD(R.color.colorWhiteGold, R.drawable.white_gold_image, "화이트골드", "WHITEGOLD"),
    GOLD(R.color.colorGold, R.drawable.gold_image,"금", "GOLD");

    RingMaterial(@ColorRes int colorRes, @DrawableRes int choiceImageRes, String name, String tag) {
        this.color = ContextCompat.getColor(RingRingApplication.getContext(), colorRes);
        this.image = ContextCompat.getDrawable(RingRingApplication.getContext(), choiceImageRes);
        this.name = name;
        this.tag = tag;
    }

    @Getter @Setter private @ColorRes int color;
    @Getter @Setter private @DrawableRes Drawable image;
    @Getter @Setter private String name;
    @Getter @Setter private String tag;

    public RingDetailAttributeViewData getAttributeData(RingCollectCount count, RingIntroResult viewData){
        return new RingDetailAttributeViewData.Builder()
                .setAttributeImage(getMaterialImageUsingChoiceDialog(this, count))
                .setSetImage(image)
                .setCollectCount(count)
                .setBigImage(ImageColorChanger.changeDrawableImageColor(RingShape.valueOf(viewData.getRingShape()).getBigImage(), this.getColor()))
                .setAttributeName(name)
                .setTag(tag).build();
    }

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
