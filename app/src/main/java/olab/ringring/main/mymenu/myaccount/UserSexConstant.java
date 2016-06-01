package olab.ringring.main.mymenu.myaccount;

import lombok.Getter;

/**
 * Created by 재화 on 2016-05-27.
 */
public enum UserSexConstant {
    MAN("남", "MAN"), WOMAN("여", "WOMAN");

    UserSexConstant(String sexText, String sexTextUsingRequest){
        this.sexText = sexText;
        this.sexTextUsingRequest = sexTextUsingRequest;
    }
    @Getter private String sexText;
    @Getter private String sexTextUsingRequest;
}
