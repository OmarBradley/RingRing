package olab.ringring.main.ringdesign.util;

import olab.ringring.main.ringdesign.levelpolicy.RingLevel;

/**
 * Created by 재화 on 2016-05-24.
 */
public class StringMaker {

    public static final String DIVIDER = "/";

    public static String getExpString(int presentRingLevel, String divider, int maxRingLevel){
        StringBuilder expStringBuilder = new StringBuilder()
                .append(presentRingLevel)
                .append(divider)
                .append(maxRingLevel);
        return expStringBuilder.toString();
    }

}
