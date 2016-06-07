package olab.ringring.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

    public BootReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("boot", "re");
        Intent notiActivityIntent = new Intent(context, RingNotificationActivity.class);
        notiActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(notiActivityIntent);
    }
}
