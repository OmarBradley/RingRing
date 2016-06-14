package olab.ringring.splash;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import olab.ringring.join.JoinActivity;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.users.UsersProtocol;
import olab.ringring.network.response.users.SuccessLogin;
import olab.ringring.util.preperance.PropertyManager;
import olab.ringring.gcm.RegistrationIntentService;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION_TIME = 2000;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                executeAutoLogin();
            }
        };
        setUpIfNeeded();
    }

    private void executeAutoLogin() {
        if (PropertyManager.getInstance().isDefaultUserLoginProperty()) {
            moveToAnotherActivity(JoinActivity.class);
        } else {
            NetworkManager.getInstance().sendRequest(UsersProtocol.makeLoginRequest(this, PropertyManager.getInstance().getUserEmail(), PropertyManager.getInstance().getUserPassword()), SuccessLogin.class, (request, result) -> {
                Log.e("autoLogin data", result.toString());
                Log.e("autoLogin data2", PropertyManager.getInstance().getUserEmail());
                moveToAnotherActivity(HomeActivity.class);
            }, (request, networkResponseCode, throwable) -> {
                Log.e("autoLogin fail data", networkResponseCode.getMessage());
                moveToAnotherActivity(JoinActivity.class);
            });
        }
    }

    private void moveToAnotherActivity(Class<? extends Activity> anotherActivity) {
        Handler moveHandler = new Handler(Looper.getMainLooper());
        moveHandler.postDelayed(() -> {
            Intent intent = new Intent(this, anotherActivity);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION_TIME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_SERVICES_RESOLUTION_REQUEST &&
                resultCode == Activity.RESULT_OK) {
            setUpIfNeeded();
        }
    }

    private void setUpIfNeeded() {
        if (checkPlayServices()) {
            String regId = PropertyManager.getInstance().getRegistrationToken();
            Log.e("token", regId);
            if (!regId.equals("")) {
                executeAutoLogin();
            } else {
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Dialog dialog = apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }
}
