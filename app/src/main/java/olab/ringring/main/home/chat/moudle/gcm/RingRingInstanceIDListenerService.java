package olab.ringring.main.home.chat.moudle.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by 재화 on 2016-06-01.
 */
public class RingRingInstanceIDListenerService extends InstanceIDListenerService {

    private static final String TAG = "RingRingInstanceIDLS";

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
