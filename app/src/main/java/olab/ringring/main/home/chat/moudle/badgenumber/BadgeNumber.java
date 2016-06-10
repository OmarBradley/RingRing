package olab.ringring.main.home.chat.moudle.badgenumber;

import android.content.Intent;

import lombok.Setter;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.splash.SplashActivity;

/**
 * Created by 재화 on 2016-06-09.
 */
public class BadgeNumber {
    private static final String BADGE_NUMBER_INTENT = "android.intent.action.BADGE_COUNT_UPDATE";
    private static final String PUT_BADGE_COUNT = "badge_count";
    private static final String PUT_BADGE_COUNT_PACKAGE_NAME ="badge_count_package_name";
    private static final String PUT_BADGE_COUNT_CLASS_NAME ="badge_count_class_name";

    @Setter private int unreadCount;

    public BadgeNumber(int unreadCount){
        this.unreadCount = unreadCount;
    }

    public Intent makeBadgeNumberIntent(){
        Intent badgeNumberIntent = new Intent(BADGE_NUMBER_INTENT);
        badgeNumberIntent.putExtra(PUT_BADGE_COUNT, unreadCount);
        badgeNumberIntent.putExtra(PUT_BADGE_COUNT_PACKAGE_NAME, RingRingApplication.getContext().getApplicationContext().getPackageName());
        badgeNumberIntent.putExtra(PUT_BADGE_COUNT_CLASS_NAME, SplashActivity.class.getName());
        return badgeNumberIntent;
    }
}
