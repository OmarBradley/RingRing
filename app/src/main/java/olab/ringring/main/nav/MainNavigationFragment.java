package olab.ringring.main.nav;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.function.BiConsumer;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.main.mymenu.NavMyMenuSubElement;
import olab.ringring.main.ringdesign.RingDesignActivity;
import olab.ringring.util.preperance.PropertyManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainNavigationFragment extends Fragment {

    @Bind(R.id.nav_home) NavMainView homeNav;
    @Bind(R.id.nav_my_menu) NavMainView myMenuNav;
    @Bind(R.id.nav_ring_design) NavMainView ringDesignNav;
    @Bind(R.id.image_nav_user_profile) CircleImageView userProfileImage;
    @Bind(R.id.text_nav_user_name) TextView userNameText;
    @Getter @Setter private DrawerLayout drawer;

    private Map<Integer, Class> menuAndMatchingActivities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainFragmentView = inflater.inflate(R.layout.fragment_main_navigation, container, false);
        ButterKnife.bind(this, mainFragmentView);
        initUserProfile();
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
        homeNav.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.drawable.nav_main_home_image), "Home");
        myMenuNav.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.drawable.nav_main_my_menu_image), "마이메뉴");
        ringDesignNav.setNavMenuAttributes(ContextCompat.getDrawable(getContext(),R.drawable.nav_main_ring_design_image), "반지만들기");
    }

    private void initUserProfile(){
        if (PropertyManager.getInstance().getUserProfileImageUrl() != null || !TextUtils.isEmpty(PropertyManager.getInstance().getUserProfileImageUrl())) {
            Glide.with(this).load(PropertyManager.getInstance().getUserProfileImageUrl()).into(userProfileImage);
        } else {
            userProfileImage.setImageDrawable(ContextCompat.getDrawable(RingRingApplication.getContext(), R.drawable.default_profile_image));
        }
        userNameText.setText(PropertyManager.getInstance().getUserName());
    }

    private void setOnClickListenerInNavMenu() {
        moveToAnotherActivityWhenMenuClicked(homeNav, HomeActivity.class);
        moveToAnotherActivityWhenMenuClicked(myMenuNav, MyMenuActivity.class);
        moveToAnotherActivityWhenMenuClicked(ringDesignNav, RingDesignActivity.class);
    }

    private void moveToAnotherActivityWhenMenuClicked(NavMainView navMainMenu, Class anotherActivity) {
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
