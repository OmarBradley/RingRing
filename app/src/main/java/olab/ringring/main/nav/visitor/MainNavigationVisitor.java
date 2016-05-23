package olab.ringring.main.nav.visitor;

import android.support.v7.app.AppCompatActivity;

import olab.ringring.main.nav.visitor.element.MainNavigationElement;

/**
 * Created by 재화 on 2016-05-18.
 */
public interface MainNavigationVisitor {
    void visit(MainNavigationElement element, AppCompatActivity activity);
}
