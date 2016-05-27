package olab.ringring.main.nav;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.main.ringdesign.RingDesignActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainNavigationFragment extends Fragment {

    @Bind(R.id.nav_home)
    NavMenuView homeNav;
    @Bind(R.id.nav_my_menu)
    NavMenuView myMenuNav;
    @Bind(R.id.nav_ring_design)
    NavMenuView ringDesignNav;
    @Bind(R.id.image_nav_user_profile) CircleImageView userProfileImage;
    @Bind(R.id.text_nav_user_name) TextView userNameText;
    @Getter @Setter private DrawerLayout drawer;

    private Map<Integer, Class> menuAndMatchingActivities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainFragmentView = inflater.inflate(R.layout.fragment_main_navigation, container, false);
        ButterKnife.bind(this, mainFragmentView);
        setNavMainMenu();
        setOnClickListenerInNavMenu();
        initIdAndActivities();
        return mainFragmentView;
    }

    private void initIdAndActivities() {
        menuAndMatchingActivities = new HashMap<>();
        menuAndMatchingActivities.put(homeNav.hashCode(), HomeActivity.class);
        menuAndMatchingActivities.put(myMenuNav.hashCode(), MyMenuActivity.class);
        menuAndMatchingActivities.put(ringDesignNav.hashCode(), RingDesignActivity.class);
    }

    private void setNavMainMenu() {
        homeNav.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher), "Home", ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
        myMenuNav.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher), "마이메뉴", ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
        ringDesignNav.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher), "반지만들기", ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
    }

    // TODO: 2016-05-23 네트워크 적용 시 Profile 사진과 이름을 받아올 수 있는 빌더 제작
    public void setNavUserProfileImage(Bitmap profileImage) {
        userProfileImage.setImageBitmap(profileImage);
    }

    public void setNavUserName(String text) {
        userNameText.setText(text);
    }

    private void setOnClickListenerInNavMenu() {
        moveToAnotherActivityWhenMenuClicked(homeNav, HomeActivity.class);
        moveToAnotherActivityWhenMenuClicked(myMenuNav, MyMenuActivity.class);
        moveToAnotherActivityWhenMenuClicked(ringDesignNav, RingDesignActivity.class);
    }

    private void moveToAnotherActivityWhenMenuClicked(NavMenuView navMainMenu, Class anotherActivity) {
        navMainMenu.setOnClickListener(view -> {
            if (menuAndMatchingActivities.get(navMainMenu.hashCode()) == getActivity().getClass()) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                moveToAnotherActivity(getActivity(), anotherActivity);
            }
        });
    }

    private void moveToAnotherActivity(Activity presentActivity, Class anotherActivity) {
        Intent intent = new Intent(presentActivity, anotherActivity);
        presentActivity.startActivity(intent);
        presentActivity.finish();
    }


}
