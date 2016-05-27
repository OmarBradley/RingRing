package olab.ringring.util.date;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-20.
 */
public class Days {

    private static final Map<Integer, String> DAYS = new HashMap<>();

    private static final class SingletonHolder {
        private static final Days INSTANCE = new Days();
    }

    private Days() {
        Context context = RingRingApplication.getContext();
        DAYS.put(1, context.getString(R.string.monday_string));
        DAYS.put(2, context.getString(R.string.tuesday_string));
        DAYS.put(3, context.getString(R.string.wednesday_string));
        DAYS.put(4, context.getString(R.string.thursday_string));
        DAYS.put(5, context.getString(R.string.friday_string));
        DAYS.put(6, context.getString(R.string.saturday_string));
        DAYS.put(7, context.getString(R.string.sunday_string));
    }

    public static Days getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getDaysString(int DaysConstant) {
        return DAYS.get(DaysConstant);
    }
}
