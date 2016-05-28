package olab.ringring.join.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import olab.ringring.util.dialog.yesorno.YesOrNoDialogBuilder;
import olab.ringring.util.dialog.yesorno.YesOrNoDialogFragment;

public class LoginFragment extends Fragment {

    // TODO: 2016-05-18 view들의 id 선언해주기..
    @Bind(R.id.edit_login_password) EditTextWithSubmitButtonView passwordEdit;
    @Bind(R.id.edit_content_text) EditText emailEdit;
    @Bind(R.id.text_login_email_validate) TextView emailValidateText;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        setHintInEditLoginPassword();
        setOnClickListenerOnEditLoginPassword();

        return view;
    }

    private void setOnClickListenerOnEditLoginPassword() {
        passwordEdit.setOnButtonClickListener(view -> {
            buildErrorDialog();
        });
    }

    private void setHintInEditLoginPassword() {
        passwordEdit.setHint(getString(R.string.login_password_hint));
    }

    private void buildErrorDialog() {
        /*ConfirmDialogFragment errorDialog = new ConfirmDialogBuilder()
                .setDialogTitleIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                .setDialogTitleTextColor(getResources().getColor(R.color.colorPrimary))
                .setDialogTitleText("로그인 오류")
                .setDialogMessage("해당 비밀번호와 id가\n 일치하지 않습니다.")
                .setConfirmButtonTextColor(getResources().getColor(R.color.colorPrimary))
                .setOnConfirmButtonClickListener((dialog, dialogItemIndex) -> {
                    dialog.dismiss();
                }).build();
        errorDialog.show(getActivity().getSupportFragmentManager(), "error dialog");*/

        YesOrNoDialogFragment errorDialog = new YesOrNoDialogBuilder()
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
        errorDialog.show(getActivity().getSupportFragmentManager(), "error dialog");
    }

    @Override
    public void onResume() {
        super.onResume();
        Validator.validateEmail(emailEdit,emailValidateText);
    }
}
