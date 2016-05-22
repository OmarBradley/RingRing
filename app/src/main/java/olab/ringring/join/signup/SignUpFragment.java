package olab.ringring.join.signup;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.customview.EditTextWithSubmitButtonView;
import olab.ringring.join.signup.partnerauthorization.PartnerConnectingActivity;
import olab.ringring.util.inputtype.InputTypeConstant;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    @Bind(R.id.edit_sign_up_email) EditText editSignUpEmail;
    @Bind(R.id.edit_sign_up_phone_number) EditText editSignUpPhoneNumber;
    @Bind(R.id.edit_sign_up_password) EditText editSignUpPassword;
    @Bind(R.id.edit_sign_up_password_confirm) EditTextWithSubmitButtonView editSignUpPasswordConfirm;
    @Bind(R.id.btn_sign_up_facebook) Button btnSignUpFacebook;

    public SignUpFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        setBasicAttributesOnEditLoginPassword();
        setOnClickListenerOnEditLoginPassword();
        return view;
    }

    private void setOnClickListenerOnEditLoginPassword() {
        editSignUpPasswordConfirm.setOnButtonClickListener(view -> {
            Intent intent = new Intent(getActivity(), PartnerConnectingActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }

    private void setBasicAttributesOnEditLoginPassword(){
        editSignUpPasswordConfirm.setHint(getString(R.string.sign_up_password_confirm_hint));
    }
}
