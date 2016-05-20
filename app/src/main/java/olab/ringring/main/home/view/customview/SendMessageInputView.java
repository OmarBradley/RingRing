package olab.ringring.main.home.view.customview;

import android.content.Context;
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

/**
 * Created by 재화 on 2016-05-18.
 */
public class SendMessageInputView extends LinearLayout {

    @Bind(R.id.btn_user) Button btnSubmit;
    @Bind(R.id.edit_chat_message) EditText editMessage;

    @Getter @Setter
    private Consumer<View> btnSubmitOnClickListener;

    public SendMessageInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        setOnClickListenerOnBtnSubmit();
    }

    public SendMessageInputView(Context context) {
        super(context);
        initView();
        setOnClickListenerOnBtnSubmit();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_send_message_input, this);
        ButterKnife.bind(this, view);
    }

    private void setOnClickListenerOnBtnSubmit() {
        btnSubmit.setOnClickListener(view -> {
            btnSubmitOnClickListener.accept(view);
        });
    }
}
