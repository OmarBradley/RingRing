package olab.ringring.main.mymenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.main.mymenu.dday.DdaySettingActivity;
import olab.ringring.main.mymenu.missionhistory.MissionHistoryActivity;
import olab.ringring.main.mymenu.myaccount.MyAccountActivity;
import olab.ringring.main.nav.MainNavigationFragment;
import olab.ringring.main.nav.visitor.MainNavigationVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetNavigationFragmentVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.nav.visitor.element.MainNavigationElement;

public class MyMenuActivity extends AppCompatActivity
        implements MainNavigationElement {

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Getter MainNavigationFragment navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        this.accept(new SetNavigationFragmentVisitor());
        this.accept(new SetToggleVisitor());

    }

    @Override
    public void accept(MainNavigationVisitor visitor) {
        visitor.visit(this, this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void moveToAnotherActivity(Class anotherActivity) {
        Intent destinationActivityIntent = new Intent(this, anotherActivity);
        startActivity(destinationActivityIntent);
        finish();
    }
}
