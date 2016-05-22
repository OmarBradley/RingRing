package olab.ringring.util.inputtype;

import android.text.InputType;

import lombok.Getter;

/**
 * Created by 재화 on 2016-05-21.
 */
// TODO: 2016-05-21 여기에 ENUM 변수 추가시 https://developer.android.com/reference/android/text/InputType.html를 참고하라
public enum InputTypeConstant {

    TYPE_TEXT_VARIATION_PASSWORD(InputType.TYPE_TEXT_VARIATION_PASSWORD), TYPE_NUMBER_VARIATION_PASSWORD(InputType.TYPE_NUMBER_VARIATION_PASSWORD);

    InputTypeConstant(int typeConstantValue){
        this.typeConstantValue = typeConstantValue;
    }

    @Getter
    private int typeConstantValue;

}
