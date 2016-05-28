package olab.ringring.join.util;

import android.app.Activity;
import android.os.PatternMatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 재화 on 2016-05-28.
 */
public class Validator {

    public static final String TEST_PASSWORD ="1234";
    public static final int MIN_PASSWORD_TEXT_LENGTH = 4;


    public static final void validateEmail(EditText emailEdit, TextView captionText){
        emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                captionText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailText = s.toString();
                if((emailText.length() != 0) && Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
                    captionText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

}
