package olab.ringring.main.ringdesign.ringattribute.jewelry;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.ringdesign.choicedialog.attributeview.RingDetailAttributeViewData;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;
import olab.ringring.util.colorchanger.ImageColorChanger;

/**
 * Created by 재화 on 2016-05-23.
 */
public enum RingJewelry {

    PERL(R.drawable.perl_choice_image, "진주", R.drawable.perl_default_image, R.drawable.perl_big_ring_image, R.drawable.perl_set_image, "PERL"),
    CRYSTAL(R.drawable.crystal_choice_image, "수정", R.drawable.crystal_default_image, R.drawable.crystal_big_ring_image, R.drawable.crystal_set_image, "CRYSTAL"),
    EMERALD(R.drawable.emerald_choice_image, "에메랄드", R.drawable.emerald_default_image, R.drawable.emerald_big_ring_image, R.drawable.emerald_set_image, "EMERALD"),
    SAPPHIRE(R.drawable.sapphire_choice_image, "사파이어", R.drawable.sapphire_default_image, R.drawable.sapphire_big_ring_image, R.drawable.sapphire_set_image, "SAPPHIRE"),
    RUBY(R.drawable.ruby_choice_image, "루비", R.drawable.ruby_default_image, R.drawable.ruby_big_ring_image, R.drawable.ruby_set_image, "RUBY"),
    DIAMOND(R.drawable.diamond_choice_image, "다이아몬드", R.drawable.diamond_default_image, R.drawable.diamond_big_ring_image, R.drawable.diamond_set_image, "DIAMOND");

    RingJewelry(@DrawableRes int setChoiceImageRes, String name, @DrawableRes int defaultImageRes, @DrawableRes int bigImageRes, @DrawableRes int setImageRes, String tag) {
        this.choiceImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, setChoiceImageRes);
        this.name = name;
        this.defaultImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, defaultImageRes);
        this.bigImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, bigImageRes);
        this.setImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, setImageRes);
        this.tag = tag;
    }

    @Setter @Getter private Drawable choiceImage;
    @Setter @Getter private String name;
    @Setter @Getter private Drawable defaultImage;
    @Setter @Getter private Drawable bigImage;
    @Setter @Getter private Drawable setImage;
    @Setter @Getter private String tag;

    public RingDetailAttributeViewData getAttributeData(RingCollectCount count){
        return new RingDetailAttributeViewData.Builder()
                .setAttributeImage(getJewelryImageUsingChoiceDialog(this, count))
                .setSetImage(setImage)
                .setCollectCount(count)
                .setBigImage(bigImage)
                .setAttributeName(name)
                .setTag(tag).build();
    }

    private final Context RESOURCE_CONTEXT = RingRingApplication.getContext();

    public static final Drawable getJewelryImageUsingChoiceDialog(RingJewelry jewelry, RingCollectCount count) {
        if (count == RingCollectCount.MAX_COUNTING_NUMBER) {
            return getSelectedShapeImage(jewelry);
        } else {
            return getNotSelectedShapeImage(jewelry);
        }
    }

    public static final Drawable getSelectedShapeImage(RingJewelry jewelry){
        return jewelry.getChoiceImage();
    }

    public static final Drawable getNotSelectedShapeImage(RingJewelry jewelry){
        return jewelry.getDefaultImage();
    }



}
