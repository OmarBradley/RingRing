package olab.ringring.main.ringdesign;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.main.nav.MainNavigationFragment;
import olab.ringring.main.nav.visitor.concretevisitior.SetNavigationFragmentVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.nav.visitor.element.MainNavigationElement;
import olab.ringring.main.nav.visitor.MainNavigationVisitor;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarTitleVisitor;

public class RingDesignActivity extends AppCompatActivity
        implements MainNavigationElement, ActionBarElement {

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Getter MainNavigationFragment navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_design);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        this.accept(new SetNavigationFragmentVisitor());
        this.accept(new SetToggleVisitor());
        this.accept(new SetActionBarTitleVisitor("반지 디자인"));
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)));
    }

    @Override
    public void accept(MainNavigationVisitor visitor) {
        visitor.visit(this, this);
    }

    @Override
    public void accept(ActionbarVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
