package olab.ringring.join.customview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-20.
 */
public class EditTextWithSubmitButtonView extends LinearLayout{

    @Bind(R.id.edit_content_text) EditText editContentText;
    @Bind(R.id.btn_submit) Button btnSubmit;

    public EditTextWithSubmitButtonView(Context context) {
        super(context);
        initView();
    }

    public EditTextWithSubmitButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_edit_text_with_submit_button, this);
        ButterKnife.bind(this, view);
    }

    private void displaySubmitButton(){
        editContentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if(text.length() > 4){
                    btnSubmit.setVisibility(VISIBLE);
                } else {
                    btnSubmit.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



}
