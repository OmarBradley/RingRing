package olab.ringring.main.home.chat.moudle.gcm;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

import olab.ringring.network.response.chat.ChatContent;

/**
 * Created by 재화 on 2016-06-01.
 */
public class RingRingGcmListenerService extends GcmListenerService {

    private static final String TAG = "RingRingGcmListenerService";

    public static final String ACTION_CHAT = "com.begentgroup.miniapplication.action.chat";
    public static final String EXTRA_SENDER_ID = "senderid";
    public static final String EXTRA_RESULT = "result";
    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        /*String type = data.getString("type");
        String senderid = data.getString("sender");
        String message = data.getString("message");
        Log.d("gcmService", "From: " + from);
        Log.d("gcmService", "ChatMessage: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
            if (type.equals("chat")) {
                long serverid = Long.parseLong(senderid);
                String lastDate = DataManager.getInstance().getLastDate(serverid);
                try {
                    MyResult<List<ChatMessage>> result = NetworkManager.getInstance().getMessageSync(lastDate);
                    String notiMessage = null;
                    User u = null;
                    for (ChatMessage m : result.result) {
                        long id = DataManager.getInstance().getUserTableId(m.sender);
                        DataManager.getInstance().addChatMessage(id, DataConstant.ChatTable.TYPE_RECEIVE, m.message, m.date);
                        notiMessage = m.sender.userName + ":" + m.message;
                        u = m.sender;
                    }
                    Intent intent = new Intent(ACTION_CHAT);
                    intent.putExtra(EXTRA_SENDER_ID, serverid);
                    LocalBroadcastManager.getInstance(this).sendBroadcastSync(intent);
                    boolean isProcessed = intent.getBooleanExtra(EXTRA_RESULT, false);
                    if (!isProcessed) {
                        sendNotification(notiMessage, u);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/

//        sendNotification(message);
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message, ChatContent data) {
/*        Intent intent = new Intent(this, ChattingActivity.class);
        intent.putExtra(ChattingActivity.EXTRA_USER, user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//* Request code *//*, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setTicker("GCM message")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("GCM ChatMessage")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());*/
    }
}