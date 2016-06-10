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

    public NowDateGetter() {
        nowDate = DateTime.now();
    }

    public int getNowYear() {
        return nowDate.getYear();
    }

    public int getNowMonth() {
        return nowDate.getMonthOfYear();
    }

    public int getNowDay() {
        return nowDate.getDayOfMonth();
    }

    public String getNowDayOfTheWeek() {
        return Days.getInstance().getDaysString(nowDate.getDayOfWeek());
    }

    public String getNowDayString() {
        Context context = RingRingApplication.getContext();
        return getNowYear() + context.getString(R.string.year_string) + BLANK + getNowMonth() + context.getString(R.string.month_string) + BLANK + getNowDay() + context.getString(R.string.day_string) + BLANK + getNowDayOfTheWeek();
    }

    public String getChatTimeString() {
        return getMeridiem() + BLANK + getHour() + HOUR_AND_MINUTE_DIVIDER + getMinute();
    }

    private String getMeridiem() {
        Context context = RingRingApplication.getContext();
        int hour = nowDate.getHourOfDay();
        if (hour >= 1 && hour <= 11) {
            return context.getString(R.string.am_string);
        } else {
            return context.getString(R.string.pm_string);
        }
    }

    private String getHour() {
        int hour = nowDate.getHourOfDay();
        if (hour >= 1 && hour <= 12) {
            return Integer.toString(hour);
        } else {
            return Integer.toString(hour - 12);
        }
    }

    private String getMinute() {
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
        return getMeridiem() + BLANK + timeString;
    }

    public String calculateDDay(long timeMillis){
        long dDayMillis = DateTime.now().getMillis() - timeMillis;
        int dDay = nowDate.minus(timeMillis).getDayOfMonth();
        return ""+dDay;
    }



}
