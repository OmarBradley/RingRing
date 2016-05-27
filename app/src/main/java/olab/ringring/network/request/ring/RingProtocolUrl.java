package olab.ringring.network.request.ring;

import lombok.Getter;

/**
 * Created by 재화 on 2016-05-27.
 */
public enum RingProtocolUrl {

    INTRO("http://52.36.101.232:3000", "ring"),
    SET_SHAPE_WINDOW("http://52.36.101.232:3000/ring", "shape"),
    SELECT_SHAPE("http://52.36.101.232:3000/ring","shape");

    RingProtocolUrl(String url, String pageSegment){
        this.url = url;
        this.pageSegment = pageSegment;
    }

    @Getter private String url;
    @Getter private String pageSegment;
}
