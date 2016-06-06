package olab.ringring.notification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.notification.ring.NotiRingFactory;
import olab.ringring.notification.ring.NotiRingJewelry;
import olab.ringring.notification.ring.NotiRingShape;
import olab.ringring.util.preperance.PropertyManager;

public class ChatNotificationActivity extends AppCompatActivity {

    @Bind(R.id.image_noti_rignt_send) ImageView rightSend;
    @Bind(R.id.image_noti_left_send) ImageView leftSend;
    @Bind(R.id.image_noti_ring_jewelry) ImageView ringJewelry;
    @Bind(R.id.image_noti_ring_shape) ImageView ringShape;
    @Bind(R.id.image_noti_chat) ImageView notiChat;

    private NotiRingFactory ringFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_notification);
        ButterKnife.bind(this);
        initRing();
    }

    private void initRing() {
        ringFactory = new NotiRingFactory(ringJewelry, ringShape,
                NotiRingJewelry.valueOf(PropertyManager.getInstance().getUserJewelry().name()),
                NotiRingShape.valueOf(PropertyManager.getInstance().getUserShape().name()),
                PropertyManager.getInstance().getUserMaterial());
        ringFactory.build();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action){
            case (MotionEvent.ACTION_DOWN) :
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        turnOnScreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseLock();
    }

    private void turnOnScreen(){
        PushWakeLock.acquireCpuWakeLock(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

    private void releaseLock(){
        PushWakeLock.releaseCpuLock();
    }
}
