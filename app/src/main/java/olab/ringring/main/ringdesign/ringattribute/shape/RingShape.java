package olab.ringring.main.ringdesign.ringattribute.shape;

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

public enum RingShape {

    CIRCLE(R.drawable.circle_big_image, R.drawable.circle_image, R.drawable.circle_set_image, "원", "CIRCLE"),
    TRIANGLE(R.drawable.triangle_big_image, R.drawable.triangle_image, R.drawable.triangle_set_image, "삼각형", "TRIANGLE"),
    RECTANGLE(R.drawable.rectangle_big_image, R.drawable.rectangle_image, R.drawable.rectangle_set_image, "사각형", "RECTANGLE"),
    PENTAGON(R.drawable.pentagon_big_image, R.drawable.pantagon_image, R.drawable.pentagon_set_image, "오각형", "PENTAGON"),
    HEXAGON(R.drawable.hexagon_big_image, R.drawable.hexagon_image, R.drawable.hexagon_set_image, "육각형", "HEXAGON"),
    OCTAGON(R.drawable.octagon_big_image, R.drawable.octagon_image, R.drawable.octagon_set_image, "팔각형", "OCTAGON");

    RingShape(@DrawableRes int bigImageRes, @DrawableRes int choiceImageRes, @DrawableRes int setImageRes, String name, String tag) {
        this.bigImage = ContextCompat.getDrawable(RingRingApplication.getContext(), bigImageRes);
        this.choiceImage = ContextCompat.getDrawable(RingRingApplication.getContext(), choiceImageRes);
        this.setImage = ContextCompat.getDrawable(RingRingApplication.getContext(), setImageRes);
        this.name = name;
        this.tag = tag;
    }

    @Getter @Setter private @DrawableRes Drawable bigImage;
    @Getter @Setter private @DrawableRes Drawable choiceImage;
    @Getter @Setter private @DrawableRes Drawable setImage;
    @Getter @Setter private String name;
    @Getter @Setter private String tag;


    public RingDetailAttributeViewData getAttributeData(RingCollectCount count){
        return new RingDetailAttributeViewData.Builder()
                .setAttributeImage(getShapeImageUsingChoiceDialog(this, count))
                .setSetImage(setImage)
                .setCollectCount(count)
                .setBigImage(bigImage)
                .setAttributeName(name)
                .setTag(tag).build();
    }

    private static final Context RESOURCE_CONTEXT = RingRingApplication.getContext();

    private static final int SELECTED_SHAPE_COLOR = ContextCompat.getColor(RESOURCE_CONTEXT, R.color.colorPrimary);
    private static final int NOT_SELECTED_SHAPE_COLOR = ContextCompat.getColor(RESOURCE_CONTEXT, R.color.colorDefault);

    public static final Drawable getShapeImageUsingChoiceDialog(RingShape shape, RingCollectCount count) {
        if (count == RingCollectCount.MAX_COUNTING_NUMBER) {
            return getSelectedShapeImage(shape);
        } else {
            return getNotSelectedShapeImage(shape);
        }
    }

    public static final Drawable getSelectedShapeImage(RingShape shape){
        Drawable imageNotColorChanged = shape.getChoiceImage();
        return ImageColorChanger.changeDrawableImageColor(imageNotColorChanged, SELECTED_SHAPE_COLOR);
    }

    public static final Drawable getNotSelectedShapeImage(RingShape shape){
        Drawable imageNotColorChanged = shape.getChoiceImage();
        return ImageColorChanger.changeDrawableImageColor(imageNotColorChanged, NOT_SELECTED_SHAPE_COLOR);
    }

}
