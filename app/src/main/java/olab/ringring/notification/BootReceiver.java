package olab.ringring.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.home.chat.moudle.gcm.RingRingGcmListenerService;

public class BootReceiver extends BroadcastReceiver {

    public BootReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("boot", "re");
        Intent notiActivityIntent = new Intent(context, ChatNotificationActivity.class);
        notiActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(notiActivityIntent);
    }
}
