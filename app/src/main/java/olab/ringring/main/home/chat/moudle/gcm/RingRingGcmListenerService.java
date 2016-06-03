package olab.ringring.main.home.chat.moudle.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDAO;
import olab.ringring.network.response.chat.ChatContent;

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
        Intent intent = new Intent(ACTION_CHAT);
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(intent);
        boolean isProcessed =  intent.getBooleanExtra(EXTRA_RESULT, false);
        if(!isProcessed){
            sendNotification(data.getString("RECEIVER_ID"), chatContent);
        }
     }

    private ChatContent makeChatContent(Bundle data){
        ChatContent chatContent = new ChatContent();
        chatContent.setSenderId(data.getString("SENDER_ID"));
        chatContent.setReceiverId(data.getString("RECEIVER_ID"));
        chatContent.setMessage(data.getString("MESSAGE_CONTENT"));
        chatContent.setSendDate(data.getLong("SEND_DATE"));
        chatContent.setReadStatus(data.getInt("READ_STATUS"));
        return chatContent;
    }

    private void sendNotification(String message ,  ChatContent chatContent) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setTicker("GCM message")
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