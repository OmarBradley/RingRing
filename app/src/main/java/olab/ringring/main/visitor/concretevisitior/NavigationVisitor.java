package olab.ringring.main.visitor.concretevisitior;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import olab.ringring.R;
import olab.ringring.main.visitor.Visitor;
import olab.ringring.main.visitor.element.MainActivityElement;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.main.ringdesign.RingDesignActivity;

/**
 * Created by 재화 on 2016-05-18.
 */
public class NavigationVisitor implements Visitor {

    private Map<Integer, Class> idAndActivities;

    public NavigationVisitor() {
        initIdAndActivities();
    }

    private void initIdAndActivities() {
        idAndActivities = new HashMap<>();
        idAndActivities.put(R.id.nav_home, HomeActivity.class);
        idAndActivities.put(R.id.nav_my_menu, MyMenuActivity.class);
        idAndActivities.put(R.id.nav_ring_design, RingDesignActivity.class);
    }

    @Override
    public void visit(MainActivityElement element, AppCompatActivity activity) {
        DrawerLayout drawer = element.getDrawer();
        NavigationView navigationView = element.getNavigationView();
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (idAndActivities.get(id) == activity.getClass()) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                executeActionWhenItemClick(id, activity);
            }
            return true;
        });
    }

    private void executeActionWhenItemClick(int id, AppCompatActivity activity) {
        if (id == R.id.nav_home) {
            moveToAnotherActivity(activity, HomeActivity.class);
        } else if (id == R.id.nav_my_menu) {
            moveToAnotherActivity(activity, MyMenuActivity.class);
        } else if (id == R.id.nav_ring_design) {
            moveToAnotherActivity(activity, RingDesignActivity.class);
        }
    }

    private void moveToAnotherActivity(AppCompatActivity presentActivity, Class anotherActivity) {
        Intent intent = new Intent(presentActivity, anotherActivity);
        presentActivity.startActivity(intent);
        presentActivity.finish();
    }
}
