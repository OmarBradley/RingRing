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

import com.annimon.stream.function.Consumer;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.util.inputtype.InputTypeConstant;

/**
 * Created by 재화 on 2016-05-20.
 */
public class EditTextWithSubmitButtonView extends LinearLayout{

    @Bind(R.id.edit_content_text) EditText contentTextEdit;
    @Bind(R.id.btn_duplicate_check) Button submitBtn;

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
        initSubmitButtonDisplayState();
        displaySubmitButton();
    }

    private void displaySubmitButton(){
        contentTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if(text.length() >= 4){
                    submitBtn.setVisibility(VISIBLE);
                } else {
                    submitBtn.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initSubmitButtonDisplayState(){
        submitBtn.setVisibility(INVISIBLE);
    }

    public void setHint(String text){
        contentTextEdit.setHint(text);
    }

    public void setInputType(InputTypeConstant inputtypeConstant) {
        contentTextEdit.setInputType(inputtypeConstant.getTypeConstantValue());
    }

    public String getInputString(){
        return contentTextEdit.getText().toString();
    }

    public void setOnSubmitButtonClickListener(OnClickListener clickListener){
        submitBtn.setOnClickListener(clickListener);
    }
}
