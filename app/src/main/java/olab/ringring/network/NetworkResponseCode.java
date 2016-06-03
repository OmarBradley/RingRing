package olab.ringring.network;

import lombok.Getter;

/**
 * Created by 재화 on 2016-06-03.
 */
public enum NetworkResponseCode {

    CONNECTION_FAIL(-3, "연결이 되지 않습니다") , CHECK_FAIL(-2, "중복되었습니다"), CHECK_SUCCESS(100, "성공하였습니다");

    NetworkResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Getter private int code;
    @Getter private String message;

}
