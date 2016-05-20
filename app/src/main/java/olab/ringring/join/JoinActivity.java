package olab.ringring.join;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.login.LoginFragment;
import olab.ringring.join.signup.SignUpFragment;
import olab.ringring.join.signup.partnerauthorization.PartnerConnectingActivity;
import olab.ringring.main.home.HomeActivity;

public class JoinActivity extends AppCompatActivity {

    private final static int LOGIN_TAB_INDICATOR_VIEW_LOCATION = 0;
    private final static int SIGN_UP_INDICATOR_VIEW_LOCATION = 1;

    @Bind(R.id.tabLayout) TabLayout tabLayout;
    @Bind(R.id.pager) ViewPager pager;
    JoinPageAdapter pageAdapter;
    JoinTabIndicatorView loginTabIndicatorView;
    JoinTabIndicatorView signUpTabIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);
        addFragmentPageInAdapter();
        setAdapterInPager();
        setTabLayout();
        changeTabIndicator();
    }

    private void addFragmentPageInAdapter() {
        pageAdapter = new JoinPageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new LoginFragment());
        pageAdapter.addFragment(new SignUpFragment());
    }

    private void setAdapterInPager() {
        pager.setAdapter(pageAdapter);
    }

    private void setTabLayout() {
        tabLayout.setupWithViewPager(pager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.transparent));
        initIndicatorView();
        attachIndicatorViewOnTabLayout();
    }

    private void initIndicatorView() {
        loginTabIndicatorView = new JoinTabIndicatorView(this, R.string.login_tab_indicator);
        loginTabIndicatorView.setTabIndicatorViewWhenFocused(R.color.colorTabFocused, android.R.drawable.btn_radio);
        signUpTabIndicatorView = new JoinTabIndicatorView(this, R.string.sign_up_tab_indicator);
        signUpTabIndicatorView.setTabIndicatorViewWhenNotFocused(R.color.colorTabNotFocused);
    }

    private void attachIndicatorViewOnTabLayout(){
        tabLayout.getTabAt(LOGIN_TAB_INDICATOR_VIEW_LOCATION).setCustomView(loginTabIndicatorView);
        tabLayout.getTabAt(SIGN_UP_INDICATOR_VIEW_LOCATION).setCustomView(signUpTabIndicatorView);
    }

    private void changeTabIndicator() {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == LOGIN_TAB_INDICATOR_VIEW_LOCATION) {
                    loginTabIndicatorView.setTabIndicatorViewWhenFocused(R.color.colorTabFocused, android.R.drawable.btn_radio);
                    signUpTabIndicatorView.setTabIndicatorViewWhenNotFocused(R.color.colorTabNotFocused);
                } else {
                    signUpTabIndicatorView.setTabIndicatorViewWhenFocused(R.color.colorTabFocused, android.R.drawable.btn_radio);
                    loginTabIndicatorView.setTabIndicatorViewWhenNotFocused(R.color.colorTabNotFocused);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
