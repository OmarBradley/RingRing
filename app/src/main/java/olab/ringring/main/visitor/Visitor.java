package olab.ringring.main.visitor;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by 재화 on 2016-05-18.
 */
// TODO: 2016-05-18 집에서 visitor 패턴 적용시키기... 지금은 기능구현만 일단 하기
public interface Visitor {
    void visit(AppCompatActivity activity);
}
