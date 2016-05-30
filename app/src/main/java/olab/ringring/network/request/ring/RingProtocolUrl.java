package olab.ringring.network.request.ring;

import lombok.Getter;
import olab.ringring.network.request.RequestBuilder;

/**
 * Created by 재화 on 2016-05-27.
 */
public enum RingProtocolUrl {

    INTRO(RequestBuilder.BASE_URL, "ring"),
    SET_SHAPE_WINDOW(RequestBuilder.BASE_URL + RingProtocolUrl.RING_PROTOCOL_PAGE_SEGMENT, "shape"),
    SELECT_SHAPE(RequestBuilder.BASE_URL + RingProtocolUrl.RING_PROTOCOL_PAGE_SEGMENT, "shape"),
    SET_MATERIAL_WINDOW(RequestBuilder.BASE_URL + RingProtocolUrl.RING_PROTOCOL_PAGE_SEGMENT, "material"),
    SELECT_MATERIAL(RequestBuilder.BASE_URL + RingProtocolUrl.RING_PROTOCOL_PAGE_SEGMENT, "material"),
    SET_JEWELRY_WINDOW(RequestBuilder.BASE_URL + RingProtocolUrl.RING_PROTOCOL_PAGE_SEGMENT, "jewelry"),
    SELECT_JEWELRY(RequestBuilder.BASE_URL + RingProtocolUrl.RING_PROTOCOL_PAGE_SEGMENT, "jewelry");

    RingProtocolUrl(String url, String pageSegment) {
        this.url = url;
        this.pageSegment = pageSegment;
    }

    @Getter private String url;
    @Getter private String pageSegment;

    private static final String RING_PROTOCOL_PAGE_SEGMENT ="/ring";
}
