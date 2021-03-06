package olab.ringring.join.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.customview.EditTextWithSubmitButtonView;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.NetworkResponseCode;
import olab.ringring.network.request.users.UsersProtocol;
import olab.ringring.network.response.users.SuccessLogin;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;
import olab.ringring.util.dialog.confirm.ConfirmDialogInfoPool;
import olab.ringring.util.preperance.PropertyManager;
import olab.ringring.util.string.TextWatcherTemplate;

public class LoginFragment extends Fragment {

    @Bind(R.id.edit_login_password) EditTextWithSubmitButtonView passwordEdit;
    @Bind(R.id.edit_content_text) EditText emailEdit;
    @Bind(R.id.text_login_email_validate) TextView emailValidateText;
    @Bind(R.id.text_login_password_validate) TextView passwordValidateText;

    public static final int MIN_PASSWORD_TEXT_LENGTH = 4;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        hidePasswordValidateText();
        setHintInEditLoginPassword();
        return view;
    }

    private void hidePasswordValidateText(){
        passwordValidateText.setVisibility(View.INVISIBLE);
    }

    private void setHintInEditLoginPassword() {
        passwordEdit.setHint(getString(R.string.login_password_hint));
    }

    @Override
    public void onResume() {
        super.onResume();
        emailEdit.addTextChangedListener(new TextWatcherTemplate()
                .setBeforeTextChanged(() -> emailValidateText.setVisibility(View.VISIBLE))
                .setOnTextChanged((s) -> {
                    String emailText = s.toString();
                    if ((emailText.length() != 0) && Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                        emailValidateText.setVisibility(View.INVISIBLE);
                    }
                })
                .build());
        validatePassword();
    }

    private void validatePassword() {
        passwordEdit.setOnSubmitButtonClickListener(view -> {
            NetworkManager.getInstance().sendRequest(UsersProtocol.makeLoginRequest(getActivity(), emailEdit.getText().toString(), passwordEdit.getInputString()), SuccessLogin.class, (request, result) -> {
                PropertyManager.getInstance().setUserIndexId(result.getUserIndex());
                setLoginProperty(result.getLoginCase(), emailEdit.getText().toString(), passwordEdit.getInputString());
                moveToAnotherActivity(LoginCase.valueOf(result.getLoginCase()).getNextActivityClass());
                passwordValidateText.setVisibility(View.INVISIBLE);
            }, (request, errorCode, throwable) -> {
                if (errorCode.equals(NetworkResponseCode.WRONG_DATA)) {
                    buildInfoErrorDialog();
                } else {
                    Toast.makeText(getActivity(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        passwordEdit.addTextChangedListener(new TextWatcherTemplate()
                .setBeforeTextChanged(passwordEdit::hideSubmitButton)
                .setOnTextChanged(this::validateEmail)
                .build());
    }

    private void validateEmail(CharSequence s){
        String text = s.toString();
        if (text.length() >= MIN_PASSWORD_TEXT_LENGTH) {
            passwordEdit.showSubmitButton();
        }
    }




    private void moveToAnotherActivity(Class activityClass){
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
        getActivity().finish();
    }

    private void setLoginProperty(int loginCaseCode, String email, String password) {
        if(loginCaseCode == LoginCase.YES_LOVER_AUTHORIZATION.getLoginCode()){
            PropertyManager.getInstance().setUserEmail(email);
            PropertyManager.getInstance().setUserPassword(password);
        }
    }

    private void buildInfoErrorDialog() {
        ConfirmDialogFragment loginErrorDialog = ConfirmDialogInfoPool.LOGIN_INFO_ERROR.makeConfirmDialog();
        loginErrorDialog.show(getActivity().getSupportFragmentManager(), "login error dialog");
    }
}
