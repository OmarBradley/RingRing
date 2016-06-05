package olab.ringring.network.request.mymenu;

import lombok.Getter;
import olab.ringring.network.request.RequestBuilder;

/**
 * Created by 재화 on 2016-05-26.
 */
public enum MyMenuProtocolUrl {

    HOME(RequestBuilder.BASE_URL, "mymenu"),
    USER_ACCOUNT(RequestBuilder.BASE_URL + MyMenuProtocolUrl.MY_MENU_PROTOCOL_PAGE_SEGMENT, "accountinfo"),
    CHANGE_USER_NAME(RequestBuilder.BASE_URL + MyMenuProtocolUrl.MY_MENU_PROTOCOL_PAGE_SEGMENT, "changename"),
    CHANGE_USER_SEX(RequestBuilder.BASE_URL + MyMenuProtocolUrl.MY_MENU_PROTOCOL_PAGE_SEGMENT, "changeusersex"),
    CHANGE_USER_PROFILE(RequestBuilder.BASE_URL + MyMenuProtocolUrl.MY_MENU_PROTOCOL_PAGE_SEGMENT, "changeprofile"),
    SHOW_PROFILE(RequestBuilder.BASE_URL + MyMenuProtocolUrl.MY_MENU_PROTOCOL_PAGE_SEGMENT, "showprofile");

    MyMenuProtocolUrl(String url, String pageSegment){
        this.url = url;
        this.pageSegment = pageSegment;
    }

    @Getter private String url;
    @Getter private String pageSegment;

    private static final String MY_MENU_PROTOCOL_PAGE_SEGMENT ="/mymenu";
}



