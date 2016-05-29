package olab.ringring.join.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.customview.EditTextWithSubmitButtonView;
import olab.ringring.join.util.Validator;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.util.dialog.confirm.ConfirmDialogBuilder;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;
import olab.ringring.util.dialog.infomation.DialogInfoPool;

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

    private void validatePassword(){
        passwordEdit.setOnSubmitButtonClickListener(view -> {
            if(Validator.isCorrectPassword(passwordEdit.getInputString(), "1234")){
                moveToMainActivity();
                passwordValidateText.setVisibility(View.INVISIBLE);
            } else {
                buildInfoErrorDialog();
                passwordValidateText.setVisibility(View.VISIBLE);
            }
        });
    }

    private void moveToMainActivity(){
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    private void buildInfoErrorDialog() {
        ConfirmDialogFragment errorDialog = new ConfirmDialogBuilder()
                .setDialogInfo(DialogInfoPool.LOGIN_INFO_ERROR)
                .setConfirmButtonTextColor(getResources().getColor(R.color.colorPrimary))
                .setOnConfirmButtonClickListener((dialog, dialogItemIndex) -> {
                    dialog.dismiss();
                }).build();
        errorDialog.show(getActivity().getSupportFragmentManager(), "error dialog");

        /*YesOrNoDialogFragment errorDialog = new YesOrNoDialogBuilder()
                .setDialogTitleIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                .setDialogTitleText("로그인 오류")
                .setDialogMessage("해당 비밀번호와 id가\n 일치하지 않습니다.")
                .setOnNegativeButtonClickListener((dialog, dialogItemIndex) -> {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "부정", Toast.LENGTH_SHORT).show();
                }).setOnPositiveButtonClickListener((dialog, dialogItemIndex) -> {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "긍정", Toast.LENGTH_SHORT).show();
                }).setPositiveButtonTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimary))
                .setNegativeButtonTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimary))
                .build();
        errorDialog.show(getActivity().getSupportFragmentManager(), "error dialog");*/
    }









}
