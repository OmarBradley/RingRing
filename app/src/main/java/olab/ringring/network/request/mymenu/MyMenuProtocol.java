package olab.ringring.network.request.mymenu;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import olab.ringring.main.mymenu.myaccount.UserSexConstant;
import olab.ringring.network.request.ImageFileFormData;
import olab.ringring.network.request.RequestBuilder;

/**
 * Created by 재화 on 2016-05-26.
 */
public class MyMenuProtocol {

    public static final Request makeIntroRequest(Context tag){
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(MyMenuProtocolUrl.INTRO.getUrl())
                .addPageSegment(MyMenuProtocolUrl.INTRO.getPageSegment())
                .build();
    }

    public static final Request makeMyAccountRequest(Context tag){
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(MyMenuProtocolUrl.USER_ACCOUNT.getUrl())
                .addPageSegment(MyMenuProtocolUrl.USER_ACCOUNT.getPageSegment())
                .build();
    }

    public static final Request makeChangeNameRequest(Context tag, String changeNameText){
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("nickname", changeNameText);
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(MyMenuProtocolUrl.CHANGE_USER_NAME.getUrl())
                .addPageSegment(MyMenuProtocolUrl.CHANGE_USER_NAME.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }

    public static final Request makeChangeUserSexRequest(Context tag, UserSexConstant sex) {
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("userSex", sex.getSexTextUsingRequest());
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(MyMenuProtocolUrl.CHANGE_USER_SEX.getUrl())
                .addPageSegment(MyMenuProtocolUrl.CHANGE_USER_SEX.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }

    public static final Request makeSetProfileImageRequest(Context tag, ImageFileFormData imageData){
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(MyMenuProtocolUrl.CHANGE_USER_PROFILE.getUrl())
                .addPageSegment(MyMenuProtocolUrl.CHANGE_USER_PROFILE.getPageSegment())
                .addImageFileParameter(imageData)
                .build();
    }


}
