package olab.ringring.util.dialog.yesorno;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-31.
 */
public enum YesOrNoDialogInfoPool {

    SESSION("회원 탈퇴하면 개인정보와\n대화내용이 모두 사라진다구..", "정말 탈퇴할꺼야?", R.drawable.dialog_caption_image, R.color.colorDialogCaption, "다시생각해볼께", "도저히안되겠어"),
    SEND_RING("사랑하는 연인에게\n반지 알림을 보내시겠습니까?", "반지 보내기", R.drawable.dialog_check_image, R.color.colorDialogCheck, "YES", "NO");

    YesOrNoDialogInfoPool(String dialogMessage, String dialogTitle, int dialogTitleIconRes, int dialogTitleTextColorRes, String dialogNegativeButtonMessage, String dialogPositiveButtonMessage) {
        this.dialogMessage = dialogMessage;
        this.dialogTitle = dialogTitle;
        this.dialogTitleIcon = ContextCompat.getDrawable(RingRingApplication.getContext(), dialogTitleIconRes);
        this.dialogTextColor = ContextCompat.getColor(RingRingApplication.getContext(), dialogTitleTextColorRes);
        this.dialogNegativeButtonMessage = dialogNegativeButtonMessage;
        this.dialogPositiveButtonMessage = dialogPositiveButtonMessage;
    }

    @Getter private String dialogTitle;
    @Getter private Drawable dialogTitleIcon;
    @Getter private String dialogMessage;
    @Getter private @ColorRes int dialogTextColor;
    @Getter private String dialogNegativeButtonMessage;
    @Getter private String dialogPositiveButtonMessage;

    public YesOrNoDialogFragment makeYesOrNoDialog(DialogInterface.OnClickListener onPositiveButtonClickListener, DialogInterface.OnClickListener onNegativeButtonClickListener){
        return new YesOrNoDialogBuilder()
                .setDialogInfo(this)
                .setOnNegativeButtonClickListener(onNegativeButtonClickListener)
                .setOnPositiveButtonClickListener(onPositiveButtonClickListener)
                .build();
    }


}
