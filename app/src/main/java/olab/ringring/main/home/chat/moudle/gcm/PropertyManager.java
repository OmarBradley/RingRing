package olab.ringring.main.home.chat.moudle.gcm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-06-01.
 */

public class PropertyManager {
    private static final class SingletonHolder{
        private static final PropertyManager INSTANCE = new PropertyManager();
    }

    public static PropertyManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private PropertyManager(){
        prefs = PreferenceManager.getDefaultSharedPreferences(RingRingApplication.getContext());
        editor = prefs.edit();
    }

    private static final String FIELD_REGISTRATION_ID = "regid";
    public void setRegistrationToken(String token) {
        editor.putString(FIELD_REGISTRATION_ID, token);
        editor.commit();
    }
    public String getRegistrationToken(){
        return prefs.getString(FIELD_REGISTRATION_ID, "");
    }


}
