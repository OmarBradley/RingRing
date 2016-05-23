package olab.ringring.main.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.main.home.chat.ChatFragment;
import olab.ringring.main.home.customview.ChatProfileView;
import olab.ringring.main.nav.visitor.Visitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetNavigationFragmentVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.nav.visitor.element.MainActivityElement;

public class HomeActivity extends AppCompatActivity
        implements MainActivityElement {

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
        deleteActionBarTitle();
        this.accept(new SetNavigationFragmentVisitor());
        this.accept(new SetToggleVisitor());
        setElevationInChatProfileView();
        setChatFragment();
    }

    private void deleteActionBarTitle(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    @Override
    public void accept(Visitor visitor) {
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

    private void setChatFragment(){
        ChatFragment chatFragment = new ChatFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_chat_fragment, chatFragment);
        fragmentTransaction.commit();
    }



}



