package olab.ringring.main.home.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-19.
 */
public class ChatProfileView extends LinearLayout {

    @Bind(R.id.text_user_name) TextView userNameText;
    @Getter @Bind(R.id.image_user_profile) CircleImageView userProfileImage;
    float elevation = 0;

    public ChatProfileView(Context context) {
        super(context);
        initView();
    }

    public ChatProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_chat_user_profile, this);
        ButterKnife.bind(this, view);
    }

    public void setUserName(String userName) {
        userNameText.setText(userName);
    }

    public void setUserProfile(Bitmap bitmap) {
        userProfileImage.setImageBitmap(bitmap);
    }

    public void setUserProfile(@DrawableRes int imageRes){
        userProfileImage.setImageDrawable(ContextCompat.getDrawable(RingRingApplication.getContext(),imageRes ));
    }


    public float getElevation() {
        if (Build.VERSION.SDK_INT < 21) {
            return elevation;
        } else {
            return super.getElevation();
        }
    }

    public void setElevation(float elevation) {
        if (Build.VERSION.SDK_INT < 21) {
            this.elevation = elevation;
        } else {
            super.setElevation(elevation);
        }
    }
}
