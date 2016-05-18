package olab.ringring.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import olab.ringring.join.JoinActivity;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        executeAutoLogin();
    }

    // TODO: 2016-05-16 자동 로그인 기능 삽입 후 메소드 이름을 적당한 이름으로 바꾸기
    private void executeAutoLogin() {
        if (isAutoLoginSuccess()) {
            moveToAnotherActivity(HomeActivity.class);
        } else {
            moveToAnotherActivity(JoinActivity.class);
        }
    }

    // TODO: 2016-05-16 자동 로그인 로직 삽입하기
    private boolean isAutoLoginSuccess() {
        return false;
    }

    private void moveToAnotherActivity(Class<? extends Activity> anotherActivity) {
        Handler moveHandler = new Handler(Looper.getMainLooper());
        moveHandler.postDelayed(() -> {
            Intent intent = new Intent(this, anotherActivity);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION_TIME);
    }
}
