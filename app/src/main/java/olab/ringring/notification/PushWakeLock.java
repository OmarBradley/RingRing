package olab.ringring.notification;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by 재화 on 2016-06-06.
 */
public class PushWakeLock {
    private static PowerManager.WakeLock CPU_WAKE_LOCK;
    private static KeyguardManager.KeyguardLock KEYGUARD_LOCK;
    private static boolean isScreenLock;

    public static void acquireCpuWakeLock(Context context) {
        Log.e("PushWakeLock", "Acquiring cpu wake lock");
        Log.e("PushWakeLock", "wake CPU_WAKE_LOCK = " + CPU_WAKE_LOCK);

        if (CPU_WAKE_LOCK != null) {
            return;
        }
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        CPU_WAKE_LOCK = pm.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "hello");

        CPU_WAKE_LOCK.acquire();
    }

    public static void releaseCpuLock() {
        Log.e("PushWakeLock", "Releasing cpu wake lock");
        Log.e("PushWakeLock", "relase CPU_WAKE_LOCK = " + CPU_WAKE_LOCK);

        if (CPU_WAKE_LOCK != null) {
            CPU_WAKE_LOCK.release();
            CPU_WAKE_LOCK = null;
        }
    }
}
