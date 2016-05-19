package olab.ringring.main.mymenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.mymenu.dday.DdaySettingActivity;
import olab.ringring.main.mymenu.missionhistory.MissionHistoryActivity;
import olab.ringring.main.mymenu.myaccount.MyAccountActivity;
import olab.ringring.main.ringdesign.RingDesignActivity;
import olab.ringring.main.visitor.Visitor;
import olab.ringring.main.visitor.concretevisitior.NavigationVisitor;
import olab.ringring.main.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.visitor.element.MainActivityElement;

public class MyMenuActivity extends AppCompatActivity
        implements MainActivityElement {

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Getter @Bind(R.id.nav_view) NavigationView navigationView;

    @Bind(R.id.btn_goto_mission_history) Button btnGotoMissionHistoryPage;
    @Bind(R.id.btn_goto_my_account) Button btnGotoMyAccountPage;
    @Bind(R.id.btn_goto_d_day_page) Button btnGotoSetDdayPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        this.accept(new NavigationVisitor());
        this.accept(new SetToggleVisitor());

        btnGotoMissionHistoryPage.setOnClickListener((view) -> {
            moveToAnotherActivity(MissionHistoryActivity.class);
        });
        btnGotoMyAccountPage.setOnClickListener((view) -> {
            moveToAnotherActivity(MyAccountActivity.class);
        });

        btnGotoSetDdayPage.setOnClickListener((view) -> {
            moveToAnotherActivity(DdaySettingActivity.class);
        });
    }

    @Override
    public void accept(Visitor visitor) {
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
