package olab.ringring.util.dialog.confirm;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import com.annimon.stream.function.BiFunction;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.UnaryOperator;

import java.text.SimpleDateFormat;
import java.util.Locale;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.util.dialog.confirm.ConfirmDialogBuilder;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;

/**
 * Created by 재화 on 2016-05-29.
 */
public enum ConfirmDialogInfoPool {

    LOGIN_INFO_ERROR("아이디와 비밀번호가 \n일치하지 않습니다", "정보 오류", R.drawable.dialog_caption_image, R.color.colorDialogCaption, "알겠어"),
    SIGN_UP_INFO_ERROR("해당 회원가입 정보가\n유효하지 않습니다", "정보 오류", R.drawable.dialog_caption_image, R.color.colorDialogCaption, "알겠어"),
    EMAIL_DUPLICATE_ERROR("이미 존재하는 이메일이야\n다시 확인해줘", "이메일 중복", R.drawable.dialog_caption_image, R.color.colorDialogCaption, "알겠어"),
    EMAIL_DUPLICATE_CHECK("해당 이메일은 사용해도 좋아\n다음 단계로 고고", "이메일 중복 확인", R.drawable.dialog_check_image, R.color.colorDialogCheck, "좋아"),
    PHONE_NUMBER_DUPLICATE_ERROR("이미 존재하는 전화번호야\n다시 확인해줘", "전화번호 중복", R.drawable.dialog_caption_image, R.color.colorDialogCaption, "알겠어"),
    PHONE_NUMBER_DUPLICATE_CHECK("해당 전화번호는 사용해도 좋아\n다음 단계로 고고!", "전화번호 중복 확인", R.drawable.dialog_check_image, R.color.colorDialogCheck, "좋아"),
    LOVER_AUTHORIZATION_ERROR("상대방 인증을 실패했어..\n다시 한번 더 시도해줘", "상대방 인증실패", R.drawable.dialog_caption_image, R.color.colorDialogCaption, "알겠어"),
    LOVER_AUTHORIZATION_SUCCESS("애인과 연결성공!\n가입 축하해 채팅하러 고고!", "가입 완료", R.drawable.dialog_check_image, R.color.colorDialogCheck, "좋아"),
    GET_COUPLEING_MESSAGE("드디어! 모든아이템을 다 모았어!\n커플링 받으러 가자가자!", "우와! 축하축하", R.drawable.dialog_mission_success, R.color.colorDialogCheck, "좋아");


    ConfirmDialogInfoPool(String dialogMessage, String dialogTitle, int dialogTitleIconRes, int dialogTitleTextColorRes, String dialogConfirmButtonMessage) {
        this.dialogMessage = dialogMessage;
        this.dialogTitle = dialogTitle;
        this.dialogTitleIcon = ContextCompat.getDrawable(RingRingApplication.getContext(), dialogTitleIconRes);
        this.dialogTextColor = ContextCompat.getColor(RingRingApplication.getContext(), dialogTitleTextColorRes);
        this.dialogConfirmButtonMessage = dialogConfirmButtonMessage;
    }

    @Getter private String dialogTitle;
    @Getter private Drawable dialogTitleIcon;
    @Getter private String dialogMessage;
    @Getter private @ColorRes int dialogTextColor;
    @Getter private String dialogConfirmButtonMessage;

    public ConfirmDialogFragment makeConfirmDialog() {
        return new ConfirmDialogBuilder()
                .setDialogInfo(new ConfirmDialogData()
                        .setDialogTitle(dialogTitle)
                        .setDialogTitleIcon(dialogTitleIcon)
                        .setDialogMessage(dialogMessage)
                        .setDialogTextColor(dialogTextColor)
                        .setDialogConfirmButtonMessage(dialogConfirmButtonMessage))
                .setOnConfirmButtonClickListener((dialog, dialogItemIndex) -> {
                    dialog.dismiss();
                }).build();
    }

}
