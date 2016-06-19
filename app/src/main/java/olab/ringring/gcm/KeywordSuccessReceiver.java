package olab.ringring.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.util.dialog.confirm.ConfirmDialogBuilder;
import olab.ringring.util.dialog.confirm.ConfirmDialogData;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;

public class KeywordSuccessReceiver extends BroadcastReceiver {

    AppCompatActivity activity;

    public KeywordSuccessReceiver(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConfirmDialogFragment keywordSuccessDialog = new ConfirmDialogBuilder()
                .setDialogInfo(new ConfirmDialogData()
                        .setDialogMessage(intent.getExtras().getString(RingRingGcmListenerService.KEYWORD_DIALOG_KEY))
                        .setDialogTitle("블라인드 미션 성공!")
                        .setDialogTitleIcon(ContextCompat.getDrawable(RingRingApplication.getContext(), R.drawable.dialog_check_image))
                        .setDialogConfirmButtonMessage("확인")
                        .setDialogTextColor(ContextCompat.getColor(RingRingApplication.getContext(), R.color.colorPrimary)))
                .setOnConfirmButtonClickListener((dialog, which) -> {
                    dialog.dismiss();
                }).build();
        keywordSuccessDialog.show(activity.getSupportFragmentManager(), "keywordSuccessDialog");
    }
}
