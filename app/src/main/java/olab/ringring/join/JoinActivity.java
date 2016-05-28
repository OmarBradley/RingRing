package olab.ringring.join;

import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.login.LoginFragment;
import olab.ringring.join.signup.SignUpFragment;

public class JoinActivity extends AppCompatActivity {

    private final static int LOGIN_TAB_INDICATOR_VIEW_LOCATION = 0;
    private final static int SIGN_UP_INDICATOR_VIEW_LOCATION = 1;
    private final static @DrawableRes int TAB_INDICATOR_NAV_IMAGE = R.drawable.join_tab_indicator_nav_image;

    @Bind(R.id.tabLayout) TabLayout tabLayout;
    @Bind(R.id.pager) ViewPager pager;
    JoinPageAdapter pageAdapter;
    JoinTabIndicatorView loginTabView;
    JoinTabIndicatorView signUpTabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        ButterKnife.bind(this);
        addFragmentPageInAdapter();
        setAdapterInPager();
        setTabLayout();
        changeTabView();
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
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,android.R.color.transparent));
        initTabView();
        attachTabViewOnTabLayout();
    }

    private void initTabView() {
        loginTabView = new JoinTabIndicatorView(this, R.string.login_tab_indicator);
        loginTabView.setTabIndicatorViewWhenFocused(R.color.colorWhiteTextColor, TAB_INDICATOR_NAV_IMAGE);
        signUpTabView = new JoinTabIndicatorView(this, R.string.sign_up_tab_indicator);
        signUpTabView.setTabIndicatorViewWhenNotFocused(R.color.colorNotFocusedColor);
    }

    private void attachTabViewOnTabLayout(){
        tabLayout.getTabAt(LOGIN_TAB_INDICATOR_VIEW_LOCATION).setCustomView(loginTabView);
        tabLayout.getTabAt(SIGN_UP_INDICATOR_VIEW_LOCATION).setCustomView(signUpTabView);
    }

    private void changeTabView() {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == LOGIN_TAB_INDICATOR_VIEW_LOCATION) {
                    loginTabView.setTabIndicatorViewWhenFocused(R.color.colorWhiteTextColor, TAB_INDICATOR_NAV_IMAGE);
                    signUpTabView.setTabIndicatorViewWhenNotFocused(R.color.colorNotFocusedColor);
                } else {
                    signUpTabView.setTabIndicatorViewWhenFocused(R.color.colorWhiteTextColor, TAB_INDICATOR_NAV_IMAGE);
                    loginTabView.setTabIndicatorViewWhenNotFocused(R.color.colorNotFocusedColor);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
