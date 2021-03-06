package olab.ringring.util.normalvisitor.visitor.concretevisitor;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;

/**
 * Created by 재화 on 2016-05-23.
 */
public class SetActionBarIconVisitor implements NormalActivityVisitor {

    @DrawableRes Drawable actionBarIcon;

    public SetActionBarIconVisitor(@DrawableRes Drawable actionBarIcon) {
        actionBarIcon.setBounds(0, 0, 28, 28);
        this.actionBarIcon = actionBarIcon;
    }

    @Override
    public void visit(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setHomeAsUpIndicator(this.actionBarIcon);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }
}
