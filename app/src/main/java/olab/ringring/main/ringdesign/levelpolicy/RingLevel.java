package olab.ringring.main.ringdesign.levelpolicy;

import android.util.Log;

import com.annimon.stream.Stream;

import lombok.Getter;

/**
 * Created by 재화 on 2016-05-23.
 */
public enum RingLevel {
    ZERO(0),ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), ELEVEN(11), TWELVE(12), THIRTEEN(13), FOURTEEN(14), FIFTEEN(15),
    SIXTEEN(16), SEVENTEEN(17), EIGHTEEN(18);

    RingLevel(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    @Getter int levelNumber;

    public int getProgressLevelNumber() {
        float progress = (float)levelNumber / (float)RingLevel.EIGHTEEN.getLevelNumber();
        return (int)(progress * 100);
    }

    public static final RingLevel MAX_LEVEL = RingLevel.EIGHTEEN;
}
