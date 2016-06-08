package olab.ringring.join.login;

import lombok.Getter;
import olab.ringring.join.signup.lovercertification.LoverConnectingActivity;
import olab.ringring.main.home.HomeActivity;

/**
 * Created by 재화 on 2016-06-08.
 */
public enum LoginCase {

    NO_LOVER_AUTHORIZATION(0, LoverConnectingActivity.class), YES_LOVER_AUTHORIZATION(1, HomeActivity.class);

    LoginCase(int loginCode, Class nextActivityClass) {
        this.loginCode = loginCode;
        this.nextActivityClass = nextActivityClass;
    }

    @Getter private int loginCode;
    @Getter private Class nextActivityClass;

    public static final LoginCase valueOf(int loginCode) {
        if (loginCode == 0) {
            return LoginCase.NO_LOVER_AUTHORIZATION;
        } else {
            return LoginCase.YES_LOVER_AUTHORIZATION;
        }
    }
}
