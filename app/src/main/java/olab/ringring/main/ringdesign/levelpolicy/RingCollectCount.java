package olab.ringring.main.ringdesign.levelpolicy;

import com.annimon.stream.Stream;

import lombok.Getter;

/**
 * Created by 재화 on 2016-05-24.
 */
public enum RingCollectCount {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

    RingCollectCount(int countNumber) {
        this.countNumber = countNumber;
    }

    @Getter int countNumber;

    public static final RingCollectCount MAX_COUNTING_NUMBER = TEN;

    public static RingCollectCount valueOf(int resultNumber){
        RingCollectCount element = Stream.of(RingCollectCount.values())
                .filter(value -> {return value.getCountNumber() == resultNumber;})
                .findFirst()
                .get();
        return element;
    }

}
