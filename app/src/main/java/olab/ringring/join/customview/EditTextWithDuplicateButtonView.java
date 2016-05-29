package olab.ringring.join.customview;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Setter;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-29.
 */
public class EditTextWithDuplicateButtonView extends LinearLayout {

    @Bind(R.id.edit_content_text) EditText contentTextEdit;
    @Bind(R.id.btn_duplicate_check) Button duplicateCheckBtn;

    public EditTextWithDuplicateButtonView(Context context) {
        super(context);
        initView();

    }

    public EditTextWithDuplicateButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_edit_text_with_duplicate_button, this);
        ButterKnife.bind(this, view);
    }

    public String getInputString(){
        return contentTextEdit.getText().toString();
    }

    public void setOnDuplicateButtonClickListener(OnClickListener clickListener){
        duplicateCheckBtn.setOnClickListener(clickListener);
    }

    public void setHint(String hintText){
        contentTextEdit.setHint(hintText);
    }

    public void addTextChangedListener(TextWatcher textWatcher){
        contentTextEdit.addTextChangedListener(textWatcher);
    }

}
