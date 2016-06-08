package olab.ringring.util.normalvisitor.visitor.concretevisitor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;

/**
 * Created by 재화 on 2016-05-23.
 */
public class DeleteActionBarTitleVisitor implements NormalActivityVisitor {

    @Override
    public void visit(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
    }
}
