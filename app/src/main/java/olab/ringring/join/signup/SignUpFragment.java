package olab.ringring.join.signup;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.customview.EditTextWithDuplicateButtonView;
import olab.ringring.join.customview.EditTextWithSubmitButtonView;
import olab.ringring.join.signup.partnerauthorization.LoverConnectingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    @Bind(R.id.edit_sign_up_email) EditTextWithDuplicateButtonView emailEdit;
    @Bind(R.id.edit_sign_up_phone_number) EditTextWithDuplicateButtonView phoneNumberEdit;
    @Bind(R.id.edit_sign_up_password) EditText passwordEdit;
    @Bind(R.id.edit_sign_up_password_confirm) EditTextWithSubmitButtonView passwordConfirmEdit;
    @Bind(R.id.btn_sign_up_facebook) Button facebookSingUpBtn;

    private static final String EMAIL_EDIT_TINT = "이메일";
    private static final String PHONE_NUMBER_EDIT_TINT = "내 전화번호";

    public SignUpFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        initView();
        setBasicAttributesOnEditLoginPassword();
        setOnClickListenerOnEditLoginPassword();
        return view;
    }

    private void initView(){
        emailEdit.setHint(EMAIL_EDIT_TINT);
        emailEdit.setOnDuplicateButtonClickListener(view -> {
            Toast.makeText(getActivity(),"test",Toast.LENGTH_SHORT).show();
        });
        phoneNumberEdit.setHint(PHONE_NUMBER_EDIT_TINT);
        phoneNumberEdit.setOnDuplicateButtonClickListener(view -> {
            Toast.makeText(getActivity(),"testssss",Toast.LENGTH_SHORT).show();
        });
    }


    private void setOnClickListenerOnEditLoginPassword() {
        passwordConfirmEdit.setOnSubmitButtonClickListener(view -> {
            Intent intent = new Intent(getActivity(), LoverConnectingActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }

    private void setBasicAttributesOnEditLoginPassword(){
        passwordConfirmEdit.setHint(getString(R.string.sign_up_password_confirm_hint));
    }

}
