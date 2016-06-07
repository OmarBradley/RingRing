package olab.ringring.main.mymenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.NoArgsConstructor;
import olab.ringring.R;
import olab.ringring.main.mymenu.dday.DDaySettingActivity;
import olab.ringring.main.mymenu.missionhistory.MissionHistoryActivity;
import olab.ringring.main.mymenu.myaccount.MyAccountActivity;
import olab.ringring.main.mymenu.profile.ProfileImageActivity;
import olab.ringring.main.mymenu.ringattribute.MyMenuRingJewelry;
import olab.ringring.main.mymenu.ringattribute.MyMenuRingShape;
import olab.ringring.main.ringdesign.customview.MyMenuRingView;
import olab.ringring.main.ringdesign.levelpolicy.RingLevel;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.home.MyMenuIntroCoupleRing;
import olab.ringring.network.response.mymenu.home.SuccessMyMenuIntro;
import olab.ringring.util.preperance.PropertyManager;

/**
 * A simple {@link Fragment} subclass.
 */
@NoArgsConstructor
public class MyMenuFragment extends Fragment {

    @Bind(R.id.image_my_menu_user_profile) CircleImageView userProfileImage;
    @Bind(R.id.text_my_menu_user_name) TextView userNameText;
    @Bind(R.id.text_my_menu_lover_name) TextView loverNameText;
    @Bind(R.id.view_my_menu_ring) MyMenuRingView myRingView;
    @Bind(R.id.text_my_menu_ring_level) TextView ringLevelText;
    @Bind(R.id.view_nav_my_account) NavMyMenuSubElement navMyAccountView;
    @Bind(R.id.view_nav_setting_d_day) NavMyMenuSubElement navSettingDdayView;
    @Bind(R.id.view_nav_mission_history) NavMyMenuSubElement navMissionHistoryView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myMenuFragmentView = inflater.inflate(R.layout.fragment_my_menu, container, false);
        ButterKnife.bind(this, myMenuFragmentView);
        setNavView(navMyAccountView, MyAccountActivity.class, R.drawable.nav_my_account_image, "나의 계정");
        setNavView(navSettingDdayView, DDaySettingActivity.class,R.drawable.nav_set_d_day_image ,"사귄날 설정" );
        setNavView(navMissionHistoryView, MissionHistoryActivity.class,R.drawable.nav_history_image, "히스토리" );
        initMyMenuRingView();
        initProfileImageViewOnClickListener();
        return myMenuFragmentView;
    }

    private void setNavView(NavMyMenuSubElement navMyMenuSubElement, Class anotherActivity, @DrawableRes int navIconRes, String navMenuText){
        navMyMenuSubElement.setOnClickListener(view ->{
            moveToAnotherActivity(anotherActivity);
        });
        navMyMenuSubElement.setNavMenuIcon(navIconRes);
        navMyMenuSubElement.setNavMenuText(navMenuText);
    }

    private void moveToAnotherActivity(Class anotherActivity) {
        Intent destinationActivityIntent = new Intent(getActivity(), anotherActivity);
        startActivity(destinationActivityIntent);
    }

    private void initMyMenuRingView(){
        if(PropertyManager.getInstance().isDefaultRingProperty()){
            getResponseData();
        } else {
            myRingView.setJewelryDrawable(MyMenuRingJewelry.valueOf(PropertyManager.getInstance().getUserJewelry().name()));
            myRingView.setShapeDrawable(MyMenuRingShape.valueOf(PropertyManager.getInstance().getUserShape().name()));
            myRingView.setMaterialColor(RingMaterial.valueOf(PropertyManager.getInstance().getUserMaterial().name()));
            getResponseData();
        }
    }

    private void getResponseData() {
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeHomeRequest(getActivity()), SuccessMyMenuIntro.class, (request, result) -> {
            SuccessMyMenuIntro data = result;
            attachResultDataInView(data);
            setJewelryProperty(data);
            PropertyManager.getInstance().setUserProperty(result);
        }, ((request, errorCode, throwable) -> {
            Toast.makeText(getContext(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }));
    }

    private void attachResultDataInView(SuccessMyMenuIntro data){
        if (data.getUserProfile() != null || !TextUtils.isEmpty(data.getUserProfile())) {
            Glide.with(this).load(data.getUserProfile()).into(userProfileImage);
        } else {
            userProfileImage.setImageResource(R.drawable.default_profile_image);
        }
        userNameText.setText(data.getUserNickname());
        loverNameText.setText(data.getLoverNickname() + getResources().getString(R.string.my_menu_lover_text_view_add_text));
        setMyRingView(data);
        ringLevelText.setText(""+RingLevel.valueOf(data.getCoupleExp()).getLevelNumber());
    }

    private void setMyRingView(SuccessMyMenuIntro data){
        myRingView.setCollectingCountProgressBar(RingLevel.valueOf(data.getCoupleExp()));
        myRingView.setCollectingCountText(RingLevel.valueOf(data.getCoupleExp()));
        MyMenuIntroCoupleRing coupleRing = data.getCoupleRings().get(0);
        myRingView.setJewelryDrawable(MyMenuRingJewelry.valueOf(coupleRing.getRingJewelry()));
        myRingView.setShapeDrawable(MyMenuRingShape.valueOf(coupleRing.getRingShape()));
        myRingView.setMaterialColor(RingMaterial.valueOf(coupleRing.getRingMaterial()));
    }

    private void setJewelryProperty(SuccessMyMenuIntro data){
        MyMenuIntroCoupleRing coupleRing = data.getCoupleRings().get(0);
        PropertyManager.getInstance().setUserJewelry(RingJewelry.valueOf(coupleRing.getRingJewelry()));
        PropertyManager.getInstance().setUserMaterial(RingMaterial.valueOf(coupleRing.getRingMaterial()));
        PropertyManager.getInstance().setUserShape(RingShape.valueOf(coupleRing.getRingShape()));
    }

    private void initProfileImageViewOnClickListener(){
        userProfileImage.setOnClickListener(view -> {
            moveToAnotherActivity(ProfileImageActivity.class);
        });
    }
}
