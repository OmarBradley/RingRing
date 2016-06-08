package olab.ringring.util.normalvisitor.visitor.concretevisitor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;

/**
 * Created by 재화 on 2016-05-23.
 */
public class SetActionBarTitleVisitor implements NormalActivityVisitor {

    String actionBarTitle;

    public SetActionBarTitleVisitor(String title) {
        this.actionBarTitle = title;
    }

    @Override
    public void visit(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("반지 디자인");
    }
}
