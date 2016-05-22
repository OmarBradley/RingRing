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

    @Bind(R.id.edit_content_text) EditText editContentText;
    @Bind(R.id.btn_submit) Button btnSubmit;
    @Getter @Setter private Consumer<View> onButtonClickListener;


    public EditTextWithSubmitButtonView(Context context) {
        super(context);
        initView();
        initSubmitButtonDisplayState();
        displaySubmitButton();
        setOnClickListenerInSubmitButton();
    }

    public EditTextWithSubmitButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initSubmitButtonDisplayState();
        displaySubmitButton();
        setOnClickListenerInSubmitButton();
    }

    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_edit_text_with_submit_button, this);
        ButterKnife.bind(this, view);
    }

    private void displaySubmitButton(){
        editContentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

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
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initSubmitButtonDisplayState(){
        btnSubmit.setVisibility(INVISIBLE);
    }

    public void setHint(String text){
        editContentText.setHint(text);
    }

    public void setInputType(InputTypeConstant inputtypeConstant) {
        editContentText.setInputType(inputtypeConstant.getTypeConstantValue());
    }

    // TODO: 2016-05-21 주의 !! 메소드 레퍼런스 기능 적용 안됨
    private void setOnClickListenerInSubmitButton(){
        btnSubmit.setOnClickListener(view->{
            onButtonClickListener.accept(view);
        });
    }
}
