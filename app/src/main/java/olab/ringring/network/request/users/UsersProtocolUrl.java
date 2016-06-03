package olab.ringring.network.request.users;

import lombok.Getter;
import olab.ringring.network.request.RequestBuilder;

/**
 * Created by 재화 on 2016-06-03.
 */
public enum UsersProtocolUrl {
    CHECK_EMAIL(RequestBuilder.BASE_URL + UsersProtocolUrl.USERS_PROTOCOL_PAGE_SEGMENT, "checkemail"),
    CHECK_PHONE_NUMBER(RequestBuilder.BASE_URL + UsersProtocolUrl.USERS_PROTOCOL_PAGE_SEGMENT, "checkphonenumber"),
    SIGN_UP(RequestBuilder.BASE_URL + UsersProtocolUrl.USERS_PROTOCOL_PAGE_SEGMENT , "signup");

    UsersProtocolUrl(String url, String pageSegment) {
        this.url = url;
        this.pageSegment = pageSegment;
    }

    @Getter private String url;
    @Getter private String pageSegment;

    private static final String USERS_PROTOCOL_PAGE_SEGMENT ="/users";


}
