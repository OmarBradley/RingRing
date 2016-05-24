package olab.ringring.main.ringdesign.levelpolicy;

import lombok.Getter;

/**
 * Created by 재화 on 2016-05-24.
 */
public enum RingCollectCount {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

    RingCollectCount(int countNumber) {
        this.countNumber = countNumber;
    }

    @Getter int countNumber;

    public static final RingCollectCount MAX_COUNTING_NUMBER = TEN;

}
