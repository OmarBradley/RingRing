package olab.ringring.join.login;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.customview.EditTextWithSubmitButtonView;
import olab.ringring.util.dialog.confirm.ConfirmDialogBuilder;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;
import olab.ringring.util.dialog.yesorno.YesOrNoDialogBuilder;
import olab.ringring.util.dialog.yesorno.YesOrNoDialogFragment;

public class LoginFragment extends Fragment {

    // TODO: 2016-05-18 view들의 id 선언해주기..
    @Bind(R.id.edit_login_password) EditTextWithSubmitButtonView editLoginPassword;
    @Bind(R.id.edit_content_text) EditText editLoginEmail;

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
        editLoginPassword.setOnButtonClickListener(view -> {
            buildErrorDialog();
            /*Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
            getActivity().finish();*/
        });
    }

    private void buildErrorDialog() {
        ConfirmDialogFragment errorDialog = new ConfirmDialogFragment();
        ConfirmDialogBuilder dialogBuilder = new ConfirmDialogBuilder()
                .setDialogTitleIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                .setDialogTitleTextColor(getResources().getColor(R.color.colorPrimary))
                .setDialogTitleText("로그인 오류")
                .setDialogMessage("해당 비밀번호와 id가\n 일치하지 않습니다.")
                .setConfirmButtonTextColor(getResources().getColor(R.color.colorPrimary))
                .setOnConfirmButtonClickListener(view -> {
                    errorDialog.dismiss();
                    Toast.makeText(getContext(), "긍정", Toast.LENGTH_SHORT).show();
                }).build();
        errorDialog.setDialogBuilder(dialogBuilder);
        errorDialog.show(getActivity().getSupportFragmentManager(), "error dialog");

/*        YesOrNoDialogFragment errorDialog = new YesOrNoDialogFragment();
        YesOrNoDialogBuilder dialogBuilder = new YesOrNoDialogBuilder()
                .setDialogTitleIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                .setDialogTitleText("로그인 오류")
                .setDialogMessage("해당 비밀번호와 id가\n 일치하지 않습니다.")
                .setOnNegativeButtonClickListener(view -> {
                    errorDialog.dismiss();
                    Toast.makeText(getContext(), "부정", Toast.LENGTH_SHORT).show();
                }).setOnPositiveButtonClickListener(view -> {
                    errorDialog.dismiss();
                    Toast.makeText(getContext(), "긍정", Toast.LENGTH_SHORT).show();
                }).build();
        errorDialog.setDialogBuilder(dialogBuilder);
        errorDialog.show(getActivity().getSupportFragmentManager(), "error dialog");*/
    }

    private void setHintInEditLoginPassword(){
        editLoginPassword.setHint(getString(R.string.login_password_hint));
    }
}
