package olab.ringring.main.ringdesign.ringattribute.shape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.util.colorchanger.ColorChanger;

/**
 * Created by 재화 on 2016-05-23.
 */

public enum RingShape {

    // TODO: 2016-05-27 default image와 선택은 색깔로 구분하기
    CIRCLE(R.drawable.circle_big_image, R.drawable.circle_image, R.drawable.circle_set_image, "원"),
    TRIANGLE(R.drawable.triangle_big_image, R.drawable.triangle_image, R.drawable.triangle_set_image, "삼각형"),
    RECTANGLE(R.drawable.rectangle_big_image, R.drawable.rectangle_image, R.drawable.rectangle_set_image, "사각형"),
    PENTAGON(R.drawable.pentagon_big_image, R.drawable.pantagon_image, R.drawable.pentagon_set_image, "오각형"),
    HEXAGON(R.drawable.hexagon_big_image, R.drawable.hexagon_image, R.drawable.hexagon_set_image, "육각형"),
    OCTAGON(R.drawable.octagon_big_image, R.drawable.octagon_image, R.drawable.octagon_set_image, "팔각형");

    RingShape(@DrawableRes int bigImageRes, @DrawableRes int choiceImageRes, @DrawableRes int setImageRes, String name) {
        this.bigImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, bigImageRes);
        this.choiceImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, choiceImageRes);
        this.setImage = ContextCompat.getDrawable(RESOURCE_CONTEXT, setImageRes);
        this.name = name;
    }

    @Getter @Setter private @DrawableRes Drawable bigImage;
    @Getter @Setter private @DrawableRes Drawable choiceImage;
    @Getter @Setter private @DrawableRes Drawable setImage;
    @Getter @Setter private String name;
    private final Context RESOURCE_CONTEXT = RingRingApplication.getContext();

    private final int CHOICE_COLOR = ContextCompat.getColor(RESOURCE_CONTEXT, R.color.colorPrimary);
    private final int NOT_CHOICE_COLOR = ContextCompat.getColor(RESOURCE_CONTEXT, R.color.colorDefault);

    public Bitmap getChoiceShapeImage(String shapeText){
        Drawable imageNotColorChanged = RingShape.valueOf(shapeText).getChoiceImage();
        return ColorChanger.changeImageColor(imageNotColorChanged, CHOICE_COLOR);
    }

    public Bitmap getNotChoiceShapeImage(String shapeText){
        Drawable imageNotColorChanged = RingShape.valueOf(shapeText).getChoiceImage();
        return ColorChanger.changeImageColor(imageNotColorChanged, NOT_CHOICE_COLOR);
    }
}
