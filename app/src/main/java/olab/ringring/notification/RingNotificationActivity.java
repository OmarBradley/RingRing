package olab.ringring.notification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.notification.ring.NotiRingFactory;
import olab.ringring.notification.ring.NotiRingJewelry;
import olab.ringring.notification.ring.NotiRingShape;
import olab.ringring.util.preperance.PropertyManager;

public class RingNotificationActivity extends AppCompatActivity implements SimpleGestureFilter.SimpleGestureListener{

    @Bind(R.id.image_noti_rignt_send) ImageView rightSend;
    @Bind(R.id.image_noti_left_send) ImageView leftSend;
    @Bind(R.id.image_noti_ring_jewelry) ImageView ringJewelry;
    @Bind(R.id.image_noti_ring_shape) ImageView ringShape;
    @Bind(R.id.image_noti_chat) ImageView notiChat;
    private static final int MIN_SWIPE_DISTANCE = 10;
    private static final int MAX_SWIPE_DISTANCE = 900;



    private NotiRingFactory ringFactory;
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
        ringFactory = new NotiRingFactory(ringJewelry, ringShape,
                NotiRingJewelry.valueOf(PropertyManager.getInstance().getUserJewelry().name()),
                NotiRingShape.valueOf(PropertyManager.getInstance().getUserShape().name()),
                PropertyManager.getInstance().getUserMaterial());
        ringFactory.build();
    }

    private void initGestureDetector(){
        detector = new SimpleGestureFilter(this,this);
        detector.setSwipeMaxDistance(MIN_SWIPE_DISTANCE);
        detector.setSwipeMaxDistance(MAX_SWIPE_DISTANCE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onDoubleTap() {}

    @Override
    public void onSwipe(int direction) {
        String str = "";
        switch (direction) {
            case SimpleGestureFilter.SWIPE_RIGHT:
                str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT:
                str = "Swipe Left";
                break;
            case SimpleGestureFilter.SWIPE_DOWN:
                moveHomeActivity();
                break;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void moveHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
