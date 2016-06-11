package olab.ringring.notification;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnimRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.annimon.stream.Stream;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.notification.ring.NotiRingJewelry;
import olab.ringring.notification.ring.NotiRingShape;
import olab.ringring.notification.ring.NotiRingView;
import olab.ringring.util.preperance.PropertyManager;

public class RingNotificationActivity extends AppCompatActivity implements SimpleGestureFilter.SimpleGestureListener{

    @Bind(R.id.image_noti_rignt_send) ImageView rightSend;
    @Bind(R.id.image_noti_left_send) ImageView leftSend;
    @Bind(R.id.view_noti_ring) NotiRingView ringView;
    @Bind(R.id.image_noti_chat) ImageView notiChat;

    private static final int MIN_SWIPE_DISTANCE = 10;
    private static final int MAX_SWIPE_DISTANCE = 1500;
    private static final int MOVE_HOME_ACTIVITY_DELAY_TIME = 200;

    private SimpleGestureFilter detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_notification);
        ButterKnife.bind(this);
        initRing();
        initGestureDetector();
    }

    private void initRing() {
        ringView.setRingJewelryImage(NotiRingJewelry.valueOf(PropertyManager.getInstance().getUserJewelry().name()));
        ringView.setRingShapeImage(NotiRingShape.valueOf(PropertyManager.getInstance().getUserShape().name())
                , PropertyManager.getInstance().getUserMaterial());
    }

    private void initGestureDetector(){
        detector = new SimpleGestureFilter(this,this);
        detector.setSwipeMaxDistance(MIN_SWIPE_DISTANCE);
        detector.setSwipeMaxDistance(MAX_SWIPE_DISTANCE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveHomeActivity();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onDoubleTap() {}

    @Override
    public void onSwipe(int direction) {
        switch (direction) {
            case SimpleGestureFilter.SWIPE_RIGHT:
                addAnimationInViews(R.anim.translate_ring_right_swipe, ringView);
                break;
            case SimpleGestureFilter.SWIPE_LEFT:
                addAnimationInViews(R.anim.translate_ring_left_swipe, ringView);
                break;
            case SimpleGestureFilter.SWIPE_DOWN:
                addAnimationInViews(R.anim.translate_ring_down_swipe, ringView);
                Handler ringMoveHandler = new Handler(Looper.getMainLooper());
                ringMoveHandler.postDelayed(this::moveHomeActivity, MOVE_HOME_ACTIVITY_DELAY_TIME);
                break;
        }
    }

    private void moveHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void addAnimationInViews(@AnimRes int animRes, View view) {
        Animation swipeAnimator = AnimationUtils.loadAnimation(this, animRes);
        view.startAnimation(swipeAnimator);
    }

}
