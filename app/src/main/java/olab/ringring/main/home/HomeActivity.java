package olab.ringring.main.home;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.main.ringdesign.RingDesignActivity;
import olab.ringring.main.visitor.Visitor;
import olab.ringring.main.visitor.concretevisitior.NavigationVisitor;
import olab.ringring.main.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.visitor.element.MainActivityElement;
import olab.ringring.util.NextPageMover;

public class HomeActivity extends AppCompatActivity
        implements MainActivityElement {

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Getter @Bind(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        this.accept(new NavigationVisitor());
        this.accept(new SetToggleVisitor());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this, this);
    }
}
