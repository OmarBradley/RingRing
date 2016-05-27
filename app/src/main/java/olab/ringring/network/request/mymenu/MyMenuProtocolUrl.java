package olab.ringring.network.request.mymenu;

import lombok.Getter;

/**
 * Created by 재화 on 2016-05-26.
 */
public enum MyMenuProtocolUrl {

    INTRO("http://52.36.101.232:3000", "mymenu"), USER_ACCOUNT("http://52.36.101.232:3000/mymenu", "accountinfo"),
    CHANGE_USER_NAME("http://52.36.101.232:3000/mymenu", "changename"), CHANGE_USER_SEX("http://52.36.101.232:3000/mymenu", "changeusersex");

    MyMenuProtocolUrl(String url, String pageSegment){
        this.url = url;
        this.pageSegment = pageSegment;
    }

    @Getter private String url;
    @Getter private String pageSegment;
}



