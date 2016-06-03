package olab.ringring.join.signup;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.customview.EditTextWithDuplicateButtonView;
import olab.ringring.join.customview.EditTextWithSubmitButtonView;
import olab.ringring.join.signup.loverauthorization.LoverConnectingActivity;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.users.UsersProtocol;
import olab.ringring.network.response.users.SuccessSignUp;
import olab.ringring.util.dialog.confirm.ConfirmDialogBuilder;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;
import olab.ringring.util.dialog.confirm.ConfirmDialogInfoPool;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private static final String EMAIL_EDIT_TINT = "이메일";
    private static final String PHONE_NUMBER_EDIT_TINT = "내 전화번호";
    private static final String EMAIL_DUPLICATE_CHECK_MESSAGE = "email 중복확인을 하시기 바랍니다";

    @Bind(R.id.edit_sign_up_email) EditTextWithDuplicateButtonView emailEdit;
    @Bind(R.id.edit_sign_up_phone_number) EditTextWithDuplicateButtonView phoneNumberEdit;
    @Bind(R.id.edit_sign_up_password) EditText passwordEdit;
    @Bind(R.id.edit_sign_up_password_confirm) EditTextWithSubmitButtonView passwordConfirmEdit;
    @Bind(R.id.text_sign_up_email_validate) TextView emailValidateText;
    @Bind(R.id.text_sign_up_phone_number_validate) TextView phoneNumberValidateText;
    @Bind(R.id.text_sign_up_password_validate) TextView passwordValidateText;
    @Bind(R.id.text_sign_up_password_confirm_validate) TextView passwordConfirmValidateText;

    @Bind(R.id.btn_sign_up_facebook) Button facebookSingUpBtn;

    private String userPassword;
    private SuccessSignUp signUpData;

    public SignUpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        initSignUpData();
        initEmailEdit();
        initPhoneNumberEdit();
        initPasswordEdit();
        initConfirmPasswordEdit();
        return view;
    }

    private void initSignUpData(){
        signUpData = new SuccessSignUp();
    }

    private void initEmailEdit(){
        emailEdit.setHint(EMAIL_EDIT_TINT);
        emailEdit.hideDuplicateCheckButton();
        emailEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailEdit.setOnDuplicateButtonClickListener(view -> {
            Toast.makeText(getActivity(),"test",Toast.LENGTH_SHORT).show();
        });
        emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if(Patterns.EMAIL_ADDRESS.matcher(text).matches()){
                    emailValidateText.setText(EMAIL_DUPLICATE_CHECK_MESSAGE);
                    emailEdit.showDuplicateCheckButton();
                    emailEdit.setOnDuplicateButtonClickListener(view ->{
                        NetworkManager.getInstance().sendSignUpValidateRequest(UsersProtocol.makeCheckEmailRequest(getActivity(), text), (networkResponseCode, result) -> {
                            ConfirmDialogFragment emailCheckDialog = ConfirmDialogInfoPool.EMAIL_DUPLICATE_CHECK.makeConfirmDialog();
                            emailCheckDialog.show(getActivity().getSupportFragmentManager(), "email check success dialog");
                            signUpData.setUserEmail(result.getResult());
                            emailValidateText.setVisibility(View.INVISIBLE);
                        }, (request, networkResponseCode, throwable) -> {
                            ConfirmDialogFragment emailCheckErrorDialog = ConfirmDialogInfoPool.EMAIL_DUPLICATE_ERROR.makeConfirmDialog();
                            emailCheckErrorDialog.show(getActivity().getSupportFragmentManager(), "email check fail dialog");
                            emailValidateText.setText(EMAIL_DUPLICATE_CHECK_MESSAGE);
                        });
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initPhoneNumberEdit() {
        phoneNumberEdit.setHint(PHONE_NUMBER_EDIT_TINT);
        phoneNumberEdit.hideDuplicateCheckButton();
        phoneNumberEdit.setInputType(InputType.TYPE_CLASS_PHONE);
        phoneNumberEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (Patterns.PHONE.matcher(text).matches()) {
                    phoneNumberValidateText.setVisibility(View.INVISIBLE);
                    phoneNumberEdit.showDuplicateCheckButton();
                    phoneNumberEdit.setOnDuplicateButtonClickListener(view -> {
                        NetworkManager.getInstance().sendSignUpValidateRequest(UsersProtocol.makeCheckPhoneNumberRequest(getActivity(), text), (networkResponseCode, result) -> {
                            ConfirmDialogFragment phoneNumberCheckDialog = ConfirmDialogInfoPool.PHONE_NUMBER_DUPLICATE_CHECK.makeConfirmDialog();
                            phoneNumberCheckDialog.show(getActivity().getSupportFragmentManager(), "phone number check success dialog");
                            signUpData.setUserPhoneNumber(result.getResult());
                        }, (request, networkResponseCode, throwable) -> {
                            ConfirmDialogFragment phoneNumberCheckErrorDialog = ConfirmDialogInfoPool.PHONE_NUMBER_DUPLICATE_ERROR.makeConfirmDialog();
                            phoneNumberCheckErrorDialog.show(getActivity().getSupportFragmentManager(), "phone number check fail dialog");
                        });
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initPasswordEdit() {
        passwordValidateText.setVisibility(View.VISIBLE);
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() >= 4) {
                    passwordValidateText.setVisibility(View.INVISIBLE);
                    userPassword = s.toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
     }

    private void initConfirmPasswordEdit() {
        passwordConfirmEdit.setHint(getString(R.string.sign_up_password_confirm_hint));
        passwordConfirmEdit.hideSubmitButton();
        passwordConfirmEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if(userPassword.equals(text)){
                    passwordConfirmValidateText.setVisibility(View.INVISIBLE);
                    passwordConfirmEdit.showSubmitButton();
                    signUpData.setUserPassword(text);
                    passwordConfirmEdit.setOnSubmitButtonClickListener(view -> {
                        NetworkManager.getInstance().sendRequest(UsersProtocol.makeSignUpRequest(getActivity(),signUpData), SuccessSignUp.class, (request, result) -> {
                            Intent intent = new Intent(getActivity(), LoverConnectingActivity.class);
                            startActivity(intent);
                        }, (request, integer, throwable) -> {
                            ConfirmDialogFragment signUpErrorDialog = ConfirmDialogInfoPool.SIGN_UP_INFO_ERROR.makeConfirmDialog();
                            signUpErrorDialog.show(getActivity().getSupportFragmentManager(), "sign up error dialog");
                        });
                    });

                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

}
