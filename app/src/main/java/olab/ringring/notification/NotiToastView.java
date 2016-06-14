package olab.ringring.notification;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.network.response.home.SuccessSendChat;
import olab.ringring.util.preperance.PropertyManager;

/**
 * Created by 재화 on 2016-06-07.
 */
public class NotiToastView extends LinearLayout {

    @Bind(R.id.image_noti_toast_lover_profile) CircleImageView loverProfile;
    @Bind(R.id.text_noti_toast_lover_name) TextView loverName;
    @Bind(R.id.text_noti_toast_message) TextView message;

    public NotiToastView(Context context) {
        super(context);
        initView();
    }

    public NotiToastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_noti_toast, this);
        ButterKnife.bind(this, view);
    }


    public void setMessage(String messageText){
        message.setText(messageText);
    }

    public void setLoverName(String loverNameText){
        loverName.setText(loverNameText);
    }

    public void setLoverProfile(String profileImageUrl){
        if (profileImageUrl != null || !TextUtils.isEmpty(profileImageUrl)) {
            Glide.with(getContext()).load(profileImageUrl).into(loverProfile);
        } else {
            loverProfile.setImageResource(R.drawable.default_profile_image);
        }
    }

    public static final void makeToast(SuccessSendChat successSendChat) {
        Handler popUpToastHandler = new Handler(Looper.getMainLooper());
        popUpToastHandler.post(() -> {
            NotiToastView notiToastView = new NotiToastView(RingRingApplication.getContext());
            notiToastView.setLoverName(PropertyManager.getInstance().getLoverNickName());
            notiToastView.setMessage(successSendChat.getMessage());
            Toast notiToast = new Toast(RingRingApplication.getContext());
            notiToast.setView(notiToastView);
            notiToast.setDuration(Toast.LENGTH_SHORT);
            notiToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 106);
            notiToast.show();
        });
    }


}
