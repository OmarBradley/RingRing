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

import com.google.android.gms.gcm.GcmListenerService;

import org.joda.time.DateTime;

import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
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
    public static final String EXTRA_SENDER_ID = "senderid";
    public static final String EXTRA_RESULT = "result";
    private int unreadCount;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        getRecentReceiveData(CoupleChatDAO.getInstance().getRecentTime());
        SuccessSendChat successSendChat = makeSuccessSendChat(data);
        CoupleChatDAO.getInstance().insertData(successSendChat);
        Intent actionChatIntent = new Intent(ACTION_CHAT);
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(actionChatIntent);
        boolean isProcessed = actionChatIntent.getBooleanExtra(EXTRA_RESULT, false);
        if (!isProcessed) {
            NotiToastView.makeToast(successSendChat);
            sendNotification(data.getString("RECEIVER_ID"), successSendChat);
        }
    }

    private SuccessSendChat makeSuccessSendChat(Bundle data){
        SuccessSendChat successSendChat = new SuccessSendChat();
        successSendChat.setSenderId(""+HomeActivity.LOVER_ID);
        successSendChat.setReceiverId(data.getString("RECEIVER_ID"));
        successSendChat.setMessage(data.getString("MESSAGE_CONTENT"));
        successSendChat.setSendDate(DateTime.now().getMillis());
        successSendChat.setReadStatus(data.getInt("READ_STATUS"));
        return successSendChat;
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

    private void getRecentReceiveData(long recentTime) {
        NetworkManager.getInstance().sendRequest(HomeProtocol.makeReceiveChatMessageRequest(this, recentTime), SuccessReceiveChat.class, (request, result) -> {
            unreadCount = result.getMessageContents().size();
            Log.e("unreadCount", unreadCount + "");
        }, (request, networkResponseCode, throwable) -> {
            Toast.makeText(this, networkResponseCode.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

}