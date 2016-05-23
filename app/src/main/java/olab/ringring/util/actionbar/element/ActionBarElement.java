package olab.ringring.util.actionbar.element;

import android.support.v7.app.AppCompatActivity;

import olab.ringring.util.actionbar.visitor.ActionbarVisitor;

/**
 * Created by 재화 on 2016-05-23.
 */
public interface ActionBarElement {
    public void accept(ActionbarVisitor visitor);
}
