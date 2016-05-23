package olab.ringring.main.nav.visitor.element;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import olab.ringring.main.nav.visitor.MainNavigationVisitor;

/**
 * Created by 재화 on 2016-05-19.
 */
public interface MainNavigationElement {
    public DrawerLayout getDrawer();
    public Toolbar getToolbar();
    public void accept(MainNavigationVisitor visitor);
}
