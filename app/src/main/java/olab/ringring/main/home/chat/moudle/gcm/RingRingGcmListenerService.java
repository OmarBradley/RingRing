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
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.google.android.gms.gcm.GcmListenerService;

import org.joda.time.DateTime;

import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.home.chat.moudle.badgenumber.BadgeNumber;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDAO;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.home.HomeProtocol;
import olab.ringring.network.response.home.SuccessReceiveChat;
import olab.ringring.network.response.home.SuccessSendChat;
import olab.ringring.notification.NotiToastView;

/**
 * Created by 재화 on 2016-06-01.
 */
public class RingRingGcmListenerService extends GcmListenerService {

    private static final String TAG = "RingRingGcmListenerService";
    public static final String ACTION_CHAT = "ringring.action.chat";
    public static final String EXTRA_RESULT = "result";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.e("bundle origin",data.toString());
        Log.e("bundle",new SuccessSendChat().mapBundle(data).toString());
        getRecentReceiveData(CoupleChatDAO.getInstance().getRecentTime(), data);
    }

    private void sendNotification(String message, SuccessSendChat successSendChat) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setTicker("RingRing")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(message)
                .setContentText(successSendChat.getMessage())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void getRecentReceiveData(long recentTime, Bundle data) {
        NetworkManager.getInstance().sendRequest(HomeProtocol.makeReceiveChatMessageRequest(this, recentTime), SuccessReceiveChat.class, (request, result) -> {
            SuccessSendChat successSendChat = new SuccessSendChat().mapBundle(data);
            Stream.of(result.getMessageContents()).forEach(CoupleChatDAO.getInstance()::insertData);
            Intent actionChatIntent = new Intent(ACTION_CHAT);
            LocalBroadcastManager.getInstance(this).sendBroadcastSync(actionChatIntent);
            boolean isProcessed = actionChatIntent.getBooleanExtra(EXTRA_RESULT, false);
            if (!isProcessed) {
                executeBadgeNumber();
                NotiToastView.makeToast(successSendChat);
                sendNotification(data.getString("RECEIVER_ID"), successSendChat);
            } else {
                CoupleChatDAO.getInstance().changeUnReadToRead();
            }
        }, (request, networkResponseCode, throwable) -> {
            Toast.makeText(this, networkResponseCode.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void executeBadgeNumber(){
        BadgeNumber badgeNumber = new BadgeNumber(CoupleChatDAO.getInstance().getUnreadRowCount());
        sendBroadcast(badgeNumber.makeBadgeNumberIntent());
    }

}