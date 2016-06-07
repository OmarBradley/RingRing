package olab.ringring.main.home.chat.moudle.gcm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

import org.joda.time.DateTime;

import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDAO;
import olab.ringring.network.response.chat.ChatContent;
import olab.ringring.notification.NotiToastView;

/**
 * Created by 재화 on 2016-06-01.
 */
public class RingRingGcmListenerService extends GcmListenerService {

    private static final String TAG = "RingRingGcmListenerService";

    public static final String ACTION_CHAT = "ringring.action.chat";
    public static final String EXTRA_SENDER_ID = "senderid";
    public static final String EXTRA_RESULT = "result";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        ChatContent chatContent = makeChatContent(data);
        CoupleChatDAO.getInstance().insertData(chatContent);
        Intent actionChatIntent = new Intent(ACTION_CHAT);
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(actionChatIntent);
        boolean isProcessed = actionChatIntent.getBooleanExtra(EXTRA_RESULT, false);
        if (!isProcessed) {
            NotiToastView.makeToast(chatContent);
            sendNotification(data.getString("RECEIVER_ID"), chatContent);
        }
    }

    private ChatContent makeChatContent(Bundle data){
        ChatContent chatContent = new ChatContent();
        chatContent.setSenderId(""+HomeActivity.LOVER_ID);
        chatContent.setReceiverId(data.getString("RECEIVER_ID"));
        chatContent.setMessage(data.getString("MESSAGE_CONTENT"));
        chatContent.setSendDate(DateTime.now().getMillis());
        chatContent.setReadStatus(data.getInt("READ_STATUS"));
        return chatContent;
    }


    private void sendNotification(String message, ChatContent chatContent) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setTicker("RingRing")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(message)
                .setContentText(chatContent.getMessage())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}