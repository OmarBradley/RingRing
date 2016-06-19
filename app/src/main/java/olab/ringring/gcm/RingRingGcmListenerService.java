package olab.ringring.gcm;

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

import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.home.chat.moudle.badgenumber.BadgeNumber;
import olab.ringring.main.home.chat.moudle.localdb.CoupleChatDAO;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.home.HomeProtocol;
import olab.ringring.network.response.home.SuccessReceiveChat;
import olab.ringring.network.response.home.SuccessSendChat;
import olab.ringring.notification.NotiToastView;
import olab.ringring.util.preperance.PropertyManager;

/**
 * Created by 재화 on 2016-06-01.
 */
public class RingRingGcmListenerService extends GcmListenerService {

    public static final String ACTION_CHAT = "ringring.action.chat";
    public static final String KEYWORD_SUCCESS_DIALOG = "keyword.success.dialog";
    public static final String KEYWORD_DIALOG_KEY = "keywordDialogMessage";
    public static final String EXTRA_RESULT = "result";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Bundle pushData = data;
        if (pushData.getString("SEND_CASE").equals("0")) {
            getRecentReceiveData(CoupleChatDAO.getInstance().getRecentTime());
        } else if (pushData.getString("SEND_CASE").equals("1")) {

        } else if (pushData.getString("SEND_CASE").equals("2")) {
            buildKeywordSuccessDialog(pushData);
        }
    }

    private void sendNotification(String message, SuccessSendChat successSendChat) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
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
            Log.e("receive", result.toString());
            SuccessSendChat lastChatData;
            try {
                lastChatData = result.getLastMessage();
            } catch (Exception e) {
                Log.e("chat receive e", e.toString());
                return;
            }
            Stream.of(result.getMessageContents()).forEach(CoupleChatDAO.getInstance()::insertData);
            Intent actionChatIntent = new Intent(ACTION_CHAT);
            LocalBroadcastManager.getInstance(this).sendBroadcastSync(actionChatIntent);
            boolean isProcessed = actionChatIntent.getBooleanExtra(EXTRA_RESULT, false);
            if (!isProcessed) {
                executeBadgeNumber();
                NotiToastView.makeToast(lastChatData);
                sendNotification(PropertyManager.getInstance().getLoverNickName(), lastChatData);
            } else {
                CoupleChatDAO.getInstance().changeUnReadToRead();
            }
        }, (request, networkResponseCode, throwable) -> {
            Toast.makeText(this, networkResponseCode.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void executeBadgeNumber() {
        BadgeNumber badgeNumber = new BadgeNumber(CoupleChatDAO.getInstance().getUnreadRowCount());
        sendBroadcast(badgeNumber.makeBadgeNumberIntent());
    }

    private void buildKeywordSuccessDialog(Bundle data) {
        Intent keywordSuccessIntent = new Intent(KEYWORD_SUCCESS_DIALOG);
        keywordSuccessIntent.putExtra(KEYWORD_DIALOG_KEY, data.getString("MESSAGE_CONTENT"));
        Log.e("message_content", data.getString("MESSAGE_CONTENT"));
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(keywordSuccessIntent);
    }
}