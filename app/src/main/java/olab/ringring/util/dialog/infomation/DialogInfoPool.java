package olab.ringring.util.dialog.infomation;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-29.
 */
public enum DialogInfoPool {

    LOGIN_INFO_ERROR("아이디와 비밀번호가 \n일치하지 않습니다", "정보 오류", R.mipmap.ic_launcher, R.color.colorAccent);

    DialogInfoPool(String dialogMessage, String dialogTitle, int dialogTitleIconRes, int dialogTitleTextColorRes) {
        this.dialogMessage = dialogMessage;
        this.dialogTitle = dialogTitle;
        this.dialogTitleIcon = ContextCompat.getDrawable(RingRingApplication.getContext(), dialogTitleIconRes);
        this.dialogTitleTextColor = ContextCompat.getColor(RingRingApplication.getContext(), dialogTitleTextColorRes);
    }

    @Getter private String dialogTitle;
    @Getter private Drawable dialogTitleIcon;
    @Getter private String dialogMessage;
    @Getter private @ColorRes int dialogTitleTextColor;
}
