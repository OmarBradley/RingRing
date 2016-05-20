package olab.ringring.util.application;

import android.content.Context;

/**
 * Created by 재화 on 2016-05-20.
 */
public class RingRingApplication extends android.app.Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }

    public static Context getContext() {
        return context;
    }
}
