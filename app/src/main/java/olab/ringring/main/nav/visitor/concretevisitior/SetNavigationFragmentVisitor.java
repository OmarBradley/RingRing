package olab.ringring.main.nav.visitor.concretevisitior;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import olab.ringring.R;
import olab.ringring.main.nav.MainNavigationFragment;
import olab.ringring.main.nav.visitor.Visitor;
import olab.ringring.main.nav.visitor.element.MainActivityElement;

/**
 * Created by 재화 on 2016-05-23.
 */
public class SetNavigationFragmentVisitor implements Visitor{

    @Override
    public void visit(MainActivityElement element, AppCompatActivity activity) {
        MainNavigationFragment navigationView = new MainNavigationFragment();
        navigationView.setDrawer(element.getDrawer());
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_nav_main, navigationView);
        fragmentTransaction.commit();
    }
}
