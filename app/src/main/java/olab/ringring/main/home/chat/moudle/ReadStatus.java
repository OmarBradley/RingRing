package olab.ringring.main.home.chat.moudle;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;

/**
 * Created by 재화 on 2016-06-02.
 */
public enum ReadStatus {

    READ(0), UNREAD(1);

    ReadStatus(int readStatus){
        this.readStatus = readStatus;
    }

    @Getter private int readStatus;

    public static final ReadStatus valueOf(int readStatus) {
        if (readStatus == 0) {
            return ReadStatus.READ;
        } else {
            return ReadStatus.UNREAD;
        }
    }

    public static final @ColorRes int READ_STATE_COLOR =  R.color.colorTextColor;
    public static final @ColorRes int UNREAD_STATE_COLOR =  R.color.colorWhiteTextColor;


}
