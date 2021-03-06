package olab.ringring.network.request.users;

import android.content.Context;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import olab.ringring.network.request.RequestBuilder;
import olab.ringring.network.response.users.SuccessSignUp;
import olab.ringring.util.preperance.PropertyManager;

/**
 * Created by 재화 on 2016-06-03.
 */
public class UsersProtocol {

    public static final Request makeCheckEmailRequest(Context tag, String userEmail) {
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("userEmail", userEmail);
        return new RequestBuilder()
                .setUrl(UsersProtocolUrl.CHECK_EMAIL.getUrl())
                .addPageSegment(UsersProtocolUrl.CHECK_EMAIL.getPageSegment())
                .setTag(tag)
                .addBodyParameters(bodyParameters)
                .build();
    }

    public static final Request makeCheckPhoneNumberRequest(Context tag, String userPhoneNumber) {
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("userPhoneNumber", userPhoneNumber);
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(UsersProtocolUrl.CHECK_PHONE_NUMBER.getUrl())
                .addPageSegment(UsersProtocolUrl.CHECK_PHONE_NUMBER.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }

    public static final Request makeSignUpRequest(Context tag, SuccessSignUp signUpData) {
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("userEmail", signUpData.getUserEmail());
        bodyParameters.put("userPhoneNumber", signUpData.getUserPhoneNumber());
        bodyParameters.put("userPassword", signUpData.getUserPassword());
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(UsersProtocolUrl.SIGN_UP.getUrl())
                .addPageSegment(UsersProtocolUrl.SIGN_UP.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }

    public static final Request makeLoginRequest(Context tag, String userEmail, String userPassword) {
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("userEmail", userEmail);
        bodyParameters.put("userPassword", userPassword);
        bodyParameters.put("registrationId", PropertyManager.getInstance().getRegistrationToken());
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(UsersProtocolUrl.LOGIN.getUrl())
                .addPageSegment(UsersProtocolUrl.LOGIN.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }

    public static final Request makeLoverCertificationRequest(Context tag, String loversPhoneNumber) {
        Map<String, String> bodyParameters = new HashMap<>();
        bodyParameters.put("loverPhoneNumber", loversPhoneNumber);
        bodyParameters.put("createDate", "" + DateTime.now().getMillis());
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(UsersProtocolUrl.LOVER_CERTIFICATION.getUrl())
                .addPageSegment(UsersProtocolUrl.LOVER_CERTIFICATION.getPageSegment())
                .addBodyParameters(bodyParameters)
                .build();
    }

    public static final Request makeLogoutRequest(Context tag) {
        return new RequestBuilder()
                .setTag(tag)
                .setUrl(UsersProtocolUrl.LOGOUT.getUrl())
                .addPageSegment(UsersProtocolUrl.LOGOUT.getPageSegment())
                .build();
    }

}
