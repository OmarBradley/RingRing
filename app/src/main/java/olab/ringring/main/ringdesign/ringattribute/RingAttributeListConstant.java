package olab.ringring.main.ringdesign.ringattribute;

import android.support.annotation.DrawableRes;

import lombok.Getter;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-28.
 */
public enum RingAttributeListConstant {

    JEWELRY("JEWELRY", "보석 설정", R.drawable.choice_dialog_jewelry_title_image),
    MATERIAL("MATERIAL", "링 재질 설정", R.drawable.choice_dialog_material_title_image),
    SHAPE("SHAPE", "링 모양 설정", R.drawable.choice_dialog_shape_title_image);

    RingAttributeListConstant(String attributeName, String choiceDialogTitle, @DrawableRes int choiceDialogTitleImageRes){
        this.attributeName = attributeName;
        this.choiceDialogTitle = choiceDialogTitle;
        this.choiceDialogTitleImageRes = choiceDialogTitleImageRes;
    }

    @Getter private String attributeName;
    @Getter private String choiceDialogTitle;
    @Getter private int choiceDialogTitleImageRes;
}
