package olab.ringring.main.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.main.home.chat.ChatFragment;
import olab.ringring.util.preperance.PropertyManager;
import olab.ringring.main.home.customview.ChatProfileView;
import olab.ringring.main.nav.visitor.MainNavigationVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetNavigationFragmentVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.nav.visitor.element.MainNavigationElement;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.DeleteActionBarTitleVisitor;

public class HomeActivity extends AppCompatActivity
        implements MainNavigationElement, ActionBarElement {

    public static final int USER_ID = 1;
    public static final int LOVER_ID = 2;
    public static final int CHAT_DAY_ID = 3;

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.profile_view_user) ChatProfileView userProfileView;
    @Bind(R.id.profile_view_lover) ChatProfileView loverProfileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Log.d("reg", PropertyManager.getInstance().getRegistrationToken());
        this.accept(new SetNavigationFragmentVisitor());
        this.accept(new SetToggleVisitor());
        this.accept(new DeleteActionBarTitleVisitor());
        setElevationInChatProfileView();
        attachChatFragment();
    }

    @Override
    public void accept(ActionbarVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(MainNavigationVisitor visitor) {
        visitor.visit(this, this);
    }

    private void setElevationInChatProfileView(){
        userProfileView.setElevation(2.0f);
        loverProfileView.setElevation(2.0f);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void attachChatFragment(){
        ChatFragment chatFragment = new ChatFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_chat_fragment, chatFragment);
        fragmentTransaction.commit();
    }



}



