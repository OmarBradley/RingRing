package olab.ringring.join.util;

import android.app.Activity;
import android.os.PatternMatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.annimon.stream.function.Consumer;

import java.util.regex.Pattern;

import olab.ringring.join.customview.EditTextWithDuplicateButtonView;
import olab.ringring.join.customview.EditTextWithSubmitButtonView;

/**
 * Created by 재화 on 2016-05-28.
 */
public class Validator {

    public static final int MIN_PASSWORD_TEXT_LENGTH = 4;

    public static final void validateEmail(EditText emailEdit, TextView captionText) {
        emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                captionText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailText = s.toString();
                if ((emailText.length() != 0) && Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    captionText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static final void validateData(EditTextWithDuplicateButtonView emailEdit, TextView captionText, Pattern inputTypePattern) {
        emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                captionText.setVisibility(View.VISIBLE);
                emailEdit.setDuplicateCheckButtonClickable(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailText = s.toString();
                if ((emailText.length() != 0) && inputTypePattern.matcher(emailText).matches()) {
                    captionText.setVisibility(View.INVISIBLE);
                    emailEdit.setDuplicateCheckButtonClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private static String PASSWORD_TEXT;

    public static final void validatePassword(EditText passwordEdit, TextView captionText, int passwordLimitLength) {

        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                captionText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((s.toString().length() >= passwordLimitLength)) {
                    captionText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                PASSWORD_TEXT = s.toString();
            }
        });
    }

    public static final void confirmPassword(EditTextWithSubmitButtonView confirmPasswordEdit, TextView captionText) {
        confirmPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                confirmPasswordEdit.hideSubmitButton();
                captionText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String confirmPasswordText = s.toString();
                if (confirmPasswordText.equals(PASSWORD_TEXT)) {
                    confirmPasswordEdit.showSubmitButton();
                    captionText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static final boolean isCorrectPassword(String inputPassword, String realPassword) {
        return inputPassword.equals(realPassword);
    }

}
