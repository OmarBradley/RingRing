package olab.ringring.join.signup.lovercertification.certificationcase;

import lombok.Getter;

/**
 * Created by 재화 on 2016-06-08.
 */
// TODO: 2016-06-08 DEFAULT_CASE의 코드를 500으로 정한 것은 http 내의 서버 내부오류 코드가 500번대이기 때문이다
public enum FailCertificationCase {
    INPUT_USER_PHONE_NUMBER(1, "자신의 번호를 입력했습니다"),
    NOT_MATCHING_PHONE_NUMBER(2, "목록에 없는 번호입니다"),
    DEFAULT_FAIL_CASE(500,"잘못된 케이스입니다(서버 DB 케이스 오류)" );

    FailCertificationCase(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter private int code;
    @Getter private String message;

    public static final FailCertificationCase valueOf(int code) {
        if (code == 1) {
            return INPUT_USER_PHONE_NUMBER;
        } else if (code == 2) {
            return NOT_MATCHING_PHONE_NUMBER;
        }
        return DEFAULT_FAIL_CASE;
    }
}
