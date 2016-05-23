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

    @Bind(R.id.nav_home) MainNavMenuView navHome;
    @Bind(R.id.nav_my_menu) MainNavMenuView navMyMenu;
    @Bind(R.id.nav_ring_design) MainNavMenuView navRingDesign;
    @Bind(R.id.image_nav_user_profile) CircleImageView imageNavUserProfile;
    @Bind(R.id.text_nav_user_name) TextView textNavUserName;
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
        menuAndMatchingActivities.put(navHome.hashCode(), HomeActivity.class);
        menuAndMatchingActivities.put(navMyMenu.hashCode(), MyMenuActivity.class);
        menuAndMatchingActivities.put(navRingDesign.hashCode(), RingDesignActivity.class);
    }

    private void setNavMainMenu() {
        navHome.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher), "Home", ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
        navMyMenu.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher), "마이메뉴", ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
        navRingDesign.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher), "반지만들기", ContextCompat.getDrawable(getContext(),R.mipmap.ic_launcher));
    }

    // TODO: 2016-05-23 네트워크 적용 시 Profile 사진과 이름을 받아올 수 있는 빌더 제작
    public void setNavUserProfileImage(Bitmap profileImage) {
        imageNavUserProfile.setImageBitmap(profileImage);
    }

    public void setNavUserName(String text) {
        textNavUserName.setText(text);
    }

    private void setOnClickListenerInNavMenu() {
        moveToAnotherActivityWhenMenuClicked(navHome, HomeActivity.class);
        moveToAnotherActivityWhenMenuClicked(navMyMenu, MyMenuActivity.class);
        moveToAnotherActivityWhenMenuClicked(navRingDesign, RingDesignActivity.class);
    }

    private void moveToAnotherActivityWhenMenuClicked(MainNavMenuView navMainMenu, Class anotherActivity) {
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
