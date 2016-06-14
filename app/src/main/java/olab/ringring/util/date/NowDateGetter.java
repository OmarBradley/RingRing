package olab.ringring.util.date;

import android.content.Context;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-05-20.
 */
public class NowDateGetter {

    DateTime nowDate;
    private static final String BLANK = " ";
    private static final String ZERO = "0";
    private static final String HOUR_AND_MINUTE_DIVIDER = ":";
    private static final String TODAY_STRING = "Today";

    public NowDateGetter() {
        nowDate = DateTime.now();
    }

    public int getNowYear(DateTime nowDate) {
        return nowDate.getYear();
    }

    public int getNowMonth(DateTime nowDate) {
        return nowDate.getMonthOfYear();
    }

    public int getNowDay(DateTime nowDate) {
        return nowDate.getDayOfMonth();
    }

    public String getNowDayOfTheWeek(DateTime nowDate) {
        return Days.getInstance().getDaysString(nowDate.getDayOfWeek());
    }

    public String getNowDayString(DateTime nowDate) {
        Context context = RingRingApplication.getContext();
        if (isSameDay(DateTime.now(), nowDate)) {
            return TODAY_STRING;
        } else {
            return getNowYear(nowDate) + context.getString(R.string.year_string) + BLANK + getNowMonth(nowDate) + context.getString(R.string.month_string) +
                    BLANK + getNowDay(nowDate) + context.getString(R.string.day_string) + BLANK + getNowDayOfTheWeek(nowDate);
        }
    }

    private boolean isSameDay(DateTime Date1, DateTime date2) {
        final boolean sameYear = Date1.getYear() == date2.getYear();
        final boolean sameMonth = Date1.getMonthOfYear() == date2.getMonthOfYear();
        final boolean sameDay = Date1.getDayOfMonth() == date2.getDayOfMonth();
        if (sameYear && sameMonth && sameDay) {
            return true;
        } else {
            return false;
        }
    }

    public String getChatTimeString(DateTime nowDate) {
        return getMeridiem(nowDate) + BLANK + getHour(nowDate) + HOUR_AND_MINUTE_DIVIDER + getMinute(nowDate);
    }

    private String getMeridiem(DateTime nowDate) {
        Context context = RingRingApplication.getContext();
        int hour = nowDate.getHourOfDay();
        if (hour >= 1 && hour <= 11) {
            return context.getString(R.string.am_string);
        } else {
            return context.getString(R.string.pm_string);
        }
    }

    private String getHour(DateTime nowDate) {
        int hour = nowDate.getHourOfDay();
        if (hour >= 1 && hour <= 12) {
            return Integer.toString(hour);
        } else {
            return Integer.toString(hour - 12);
        }
    }

    private String getMinute(DateTime nowDate) {
        int minute = nowDate.getMinuteOfHour();
        if (minute < 10) {
            return ZERO + Integer.toString(minute);
        } else {
            return Integer.toString(minute);
        }
    }

    public String getChatTimeString(long timeMillis) {
        DateFormat chatTimeDateFormat = new SimpleDateFormat("h:mm"); // HH=24h, hh=12h
        String timeString = chatTimeDateFormat.format(timeMillis);
        return getMeridiem(new DateTime(timeMillis)) + BLANK + timeString;
    }

    public String calculateDDay(long timeMillis){
        long dDayMillis = DateTime.now().getMillis() - timeMillis;
        int dDay = nowDate.minus(timeMillis).getDayOfMonth();
        return ""+dDay;
    }

    public int extractDayOfTimeMills(long timeMillis){
        DateTime dateTime = new DateTime(timeMillis);
        return dateTime.getDayOfYear();
    }

}
