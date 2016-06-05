package olab.ringring.join.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import olab.ringring.join.util.Validator;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.users.UsersProtocol;
import olab.ringring.network.response.users.SuccessLogin;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;
import olab.ringring.util.dialog.confirm.ConfirmDialogInfoPool;
import olab.ringring.util.dialog.yesorno.YesOrNoDialogFragment;
import olab.ringring.util.dialog.yesorno.YesOrNoDialogInfoPool;

public class LoginFragment extends Fragment {

    // TODO: 2016-05-18 view들의 id 선언해주기..
    @Bind(R.id.edit_login_password) EditTextWithSubmitButtonView passwordEdit;
    @Bind(R.id.edit_content_text) EditText emailEdit;
    @Bind(R.id.text_login_email_validate) TextView emailValidateText;
    @Bind(R.id.text_login_password_validate) TextView passwordValidateText;

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
        Validator.validateEmail(emailEdit,emailValidateText);
        validatePassword();
    }

    // TODO: 2016-06-04 상욱이가 내려준 프로토콜로 로그인 프로토콜 고치기
    private void validatePassword(){
        passwordEdit.setOnSubmitButtonClickListener(view -> {
            NetworkManager.getInstance().sendRequest(UsersProtocol.makeLoginRequest(getActivity(),emailEdit.getText().toString(), passwordEdit.getInputString()), SuccessLogin.class, (request, result) -> {
                if(result == null){
                    buildInfoErrorDialog();
                } else {
                    moveToHomeActivity();
                    passwordValidateText.setVisibility(View.VISIBLE);
                }
            }, (request, integer, throwable) -> {
                Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
            });
        });
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                passwordEdit.hideSubmitButton();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if(text.length() >= Validator.MIN_PASSWORD_TEXT_LENGTH){
                    passwordEdit.showSubmitButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void moveToHomeActivity(){
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    private void buildInfoErrorDialog() {
        ConfirmDialogFragment loginErrorDialog = ConfirmDialogInfoPool.LOGIN_INFO_ERROR.makeConfirmDialog();
        loginErrorDialog.show(getActivity().getSupportFragmentManager(), "login error dialog");
    }









}
