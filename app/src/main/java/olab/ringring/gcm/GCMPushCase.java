package olab.ringring.gcm;

import android.os.Bundle;

import lombok.Getter;

/**
 * Created by 재화 on 2016-06-14.
 */
public enum GCMPushCase {

    MESSAGE_PUSH(0),LOVER_AUTH_PUSH(1), KEYWORD_MISSION_SUCCESS(2);

    GCMPushCase(int pushCaseCode){
        this.pushCaseCode = pushCaseCode;
    }

    @Getter private int pushCaseCode;

    public static final GCMPushCase valueOf(Bundle data) {
        int pushCase = data.getInt("SEND_CASE");
        if (pushCase == 0) {
            return MESSAGE_PUSH;
        } else if (pushCase == 1) {
            return LOVER_AUTH_PUSH;
        } else if(pushCase == 2) {
            return KEYWORD_MISSION_SUCCESS;
        }
        return null;
    }
}
