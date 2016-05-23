package olab.ringring.main.nav.visitor.concretevisitior;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import olab.ringring.R;
import olab.ringring.main.nav.visitor.Visitor;
import olab.ringring.main.nav.visitor.element.MainActivityElement;

/**
 * Created by 재화 on 2016-05-19.
 */
public class SetToggleVisitor implements Visitor {

    @Override
    public void visit(MainActivityElement element, AppCompatActivity activity) {
        DrawerLayout drawer = element.getDrawer();
        Toolbar toolbar = element.getToolbar();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
}
