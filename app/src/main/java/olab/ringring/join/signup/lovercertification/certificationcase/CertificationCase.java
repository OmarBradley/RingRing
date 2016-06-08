package olab.ringring.join.signup.lovercertification.certificationcase;

import lombok.Getter;

/**
 * Created by 재화 on 2016-06-08.
 */
// TODO: 2016-06-08 DEFAULT_CASE의 코드를 500으로 정한 것은 http 내의 서버 내부오류 코드가 500번대이기 때문이다
public enum CertificationCase {

    FIRST_REQUEST(1, "상대방 인증 최초 요청"), RE_REQUEST(2, "상대방 인증 재요청"), SUCCESS_MATCHED(3, "상대방과 매칭이 완료되었습니다"), DEFAULT_CASE(500, "잘못된 케이스입니다(서버 DB 케이스 오류)");

    CertificationCase(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Getter private int code;
    @Getter private String message;

    public static final CertificationCase valueOf(int code) {
        if (code == 1) {
            return FIRST_REQUEST;
        } else if (code == 2) {
            return RE_REQUEST;
        } else if (code == 3) {
            return SUCCESS_MATCHED;
        }
        return DEFAULT_CASE;
    }
}
