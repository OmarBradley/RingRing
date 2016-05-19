package olab.ringring.main.visitor;

import android.support.v7.app.AppCompatActivity;

import olab.ringring.main.visitor.element.MainActivityElement;

/**
 * Created by 재화 on 2016-05-18.
 */
public interface Visitor {
    void visit(MainActivityElement element, AppCompatActivity activity);
}
