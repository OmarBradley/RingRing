package olab.ringring.join.signup.lovercertification;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.JoinActivity;
import olab.ringring.join.signup.lovercertification.certificationcase.CertificationCase;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.users.UsersProtocol;
import olab.ringring.network.response.users.SuccessLoverCertification;
import olab.ringring.util.normalvisitor.element.NomalActivityElement;
import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;
import olab.ringring.util.normalvisitor.visitor.concretevisitor.SetActionBarIconVisitor;
import olab.ringring.util.normalvisitor.visitor.concretevisitor.SetActionBarTitleVisitor;
import olab.ringring.util.dialog.confirm.ConfirmDialogBuilder;
import olab.ringring.util.dialog.confirm.ConfirmDialogData;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;
import olab.ringring.util.dialog.confirm.ConfirmDialogInfoPool;

public class LoverConnectingActivity extends AppCompatActivity implements NomalActivityElement {

    @Bind(R.id.edit_lover_phone_number) EditText loverPhoneNumberEdit;
    @Bind(R.id.btn_lover_connect) Button loverConnectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lover_connecting);
        ButterKnife.bind(this);
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.drawable.actionbar_home_as_up_image)));
        this.accept(new SetActionBarTitleVisitor("회원가입"));
        setOnClickListenerInLoverConnectBtn();
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, JoinActivity.class);
        startActivity(pageMover);
        finish();
    }

    @Override
    public void accept(NormalActivityVisitor visitor) {
        visitor.visit(this);
    }

    private void setOnClickListenerInLoverConnectBtn() {
        loverConnectBtn.setOnClickListener(view -> {
            NetworkManager.getInstance().sendLoverCertificationRequest(UsersProtocol.makeLoverCertificationRequest(this, loverPhoneNumberEdit.getText().toString()),
                    (request, result) -> {
                        Log.e("result", result.toString());
                        buildSuccessDialog(result);
                    }, (request, failResult) -> {
                        ConfirmDialogFragment failDialog = ConfirmDialogInfoPool.LOVER_AUTHORIZATION_ERROR.makeConfirmDialog();
                        failDialog.show(getSupportFragmentManager(), "fail lover certification dialog");
                    }, (request, networkResponseCode, throwable) -> {
                        Toast.makeText(this, networkResponseCode.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            );
        });
    }

    private void buildSuccessDialog(SuccessLoverCertification result) {
        if (result.getCertificationCase() == CertificationCase.SUCCESS_MATCHED.getCode()) {
            ConfirmDialogInfoPool authSuccessDialogInfo = ConfirmDialogInfoPool.LOVER_AUTHORIZATION_SUCCESS;
            ConfirmDialogData dialogData = new ConfirmDialogData()
                    .setDialogMessage(authSuccessDialogInfo.getDialogMessage())
                    .setDialogConfirmButtonMessage(authSuccessDialogInfo.getDialogConfirmButtonMessage())
                    .setDialogTextColor(authSuccessDialogInfo.getDialogTextColor())
                    .setDialogTitle(authSuccessDialogInfo.getDialogTitle())
                    .setDialogTitleIcon(authSuccessDialogInfo.getDialogTitleIcon());
            ConfirmDialogFragment successDialog = new ConfirmDialogBuilder()
                    .setDialogInfo(dialogData)
                    .setOnConfirmButtonClickListener((dialog, which) -> {
                        dialog.dismiss();
                        moveHomeActivity();
                    }).build();
            successDialog.show(getSupportFragmentManager(), "success lover certification dialog");
        } else {
            Toast.makeText(this, CertificationCase.valueOf(result.getCertificationCase()).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void moveHomeActivity(){
        Intent nextPageIntent = new Intent(this, SendDownloadLinkActivity.class);
        startActivity(nextPageIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
