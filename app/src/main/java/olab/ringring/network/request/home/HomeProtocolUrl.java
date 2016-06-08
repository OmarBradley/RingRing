package olab.ringring.network.request.home;

import lombok.Getter;
import olab.ringring.network.request.RequestBuilder;

/**
 * Created by 재화 on 2016-06-02.
 */
public enum HomeProtocolUrl {
    SEND_CHAT_MESSAGE(RequestBuilder.BASE_URL + HomeProtocolUrl.HOME_PROTOCOL_PAGE_SEGMENT, "sendmessage"),
    RECEIVE_CHAT_MESSAGE(RequestBuilder.BASE_URL + HomeProtocolUrl.HOME_PROTOCOL_PAGE_SEGMENT, "receivemessage");

    HomeProtocolUrl(String url, String pageSegment){
        this.url = url;
        this.pageSegment = pageSegment;
    }

    @Getter private String url;
    @Getter private String pageSegment;

    private static final String HOME_PROTOCOL_PAGE_SEGMENT ="/home";
}
