package olab.ringring.main.mymenu.myaccount;

import lombok.Getter;

/**
 * Created by 재화 on 2016-05-27.
 */
public enum UserSexConstant {
    MAN("man"), WOMAN("woman");

    UserSexConstant(String sexText){
        this.sexText = sexText;
    }
    @Getter private String sexText;
}
