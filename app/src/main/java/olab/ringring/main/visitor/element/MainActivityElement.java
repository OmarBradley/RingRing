package olab.ringring.main.visitor.element;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import olab.ringring.main.visitor.Visitor;

/**
 * Created by 재화 on 2016-05-19.
 */
public interface MainActivityElement {
    public NavigationView getNavigationView();
    public DrawerLayout getDrawer();
    public Toolbar getToolbar();
    public void accept(Visitor visitor);

}
