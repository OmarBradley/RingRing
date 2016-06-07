package olab.ringring.join.customview;

import android.content.Context;
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
    }

    private void initSubmitButtonDisplayState(){
        submitBtn.setVisibility(INVISIBLE);
    }

    public void setHint(String text){
        contentTextEdit.setHint(text);
    }

    public String getInputString(){
        return contentTextEdit.getText().toString();
    }

    public void setOnSubmitButtonClickListener(OnClickListener clickListener){
        submitBtn.setOnClickListener(clickListener);
    }

    public void hideSubmitButton(){
        submitBtn.setVisibility(INVISIBLE);
    }

    public void showSubmitButton(){
        submitBtn.setVisibility(VISIBLE);
    }

    public void addTextChangedListener(TextWatcher textWatcher){
        contentTextEdit.addTextChangedListener(textWatcher);
    }


}
