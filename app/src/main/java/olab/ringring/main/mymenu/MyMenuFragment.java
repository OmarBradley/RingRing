package olab.ringring.main.mymenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.NoArgsConstructor;
import olab.ringring.R;
import olab.ringring.main.mymenu.dday.DDaySettingActivity;
import olab.ringring.main.mymenu.missionhistory.MissionHistoryActivity;
import olab.ringring.main.mymenu.myaccount.MyAccountActivity;
import olab.ringring.main.nav.NavMenuView;
import olab.ringring.main.ringdesign.customview.MyMenuRingView;
import olab.ringring.main.ringdesign.levelpolicy.RingLevel;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.intro.MyMenuIntroCoupleRing;
import olab.ringring.network.response.mymenu.intro.MyMenuIntroResult;

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
    @Bind(R.id.view_nav_my_account) NavMenuView navMyAccountView;
    @Bind(R.id.view_nav_setting_d_day) NavMenuView navSettingDdayView;
    @Bind(R.id.view_nav_mission_history) NavMenuView navMissionHistoryView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myMenuFragmentView = inflater.inflate(R.layout.fragment_my_menu, container, false);
        ButterKnife.bind(this, myMenuFragmentView);
        setNavView(navMyAccountView, MyAccountActivity.class);
        setNavView(navSettingDdayView, DDaySettingActivity.class);
        setNavView(navMissionHistoryView, MissionHistoryActivity.class);
        getResponseData();
        return myMenuFragmentView;
    }

    private void setNavView(NavMenuView navMenuView, Class anotherActivity ){
        navMenuView.setOnClickListener(view ->{
            moveToAnotherActivity(anotherActivity);
        });
    }

    private void moveToAnotherActivity(Class anotherActivity) {
        Intent destinationActivityIntent = new Intent(getActivity(), anotherActivity);
        startActivity(destinationActivityIntent);
    }

    private void getResponseData() {
        NetworkManager.getInstance().getResult(MyMenuProtocol.makeIntroRequest(getActivity()), MyMenuIntroResult.class, (request, result) -> {
            MyMenuIntroResult data = result;
            attachResultDataInView(data);
        }, ((request, integer, throwable) -> {
            Toast.makeText(getContext(), "알수 없는 에러" + integer, Toast.LENGTH_SHORT).show();
        }));
    }

    private void attachResultDataInView(MyMenuIntroResult data){
        if (data.getUserProfile() != null || !TextUtils.isEmpty(data.getUserProfile())) {
            Glide.with(this).load(data.getUserProfile()).into(userProfileImage);
        } else {
            userProfileImage.setImageResource(R.mipmap.ic_launcher);
        }
        userNameText.setText(data.getUserNickname());
        loverNameText.setText(data.getLoverNickname());
        setMyRingView(data);
        ringLevelText.setText(""+RingLevel.valueOf(data.getCoupleExp()).getLevelNumber());
    }

    private void setMyRingView(MyMenuIntroResult data){
        myRingView.setCollectingCountProgressBar(RingLevel.valueOf(data.getCoupleExp()));
        myRingView.setCollectingCountText(RingLevel.valueOf(data.getCoupleExp()));
        MyMenuIntroCoupleRing coupleRing = data.getCoupleRings().get(0);
        myRingView.setJewelryDrawable(RingJewelry.valueOf(coupleRing.getRingJewelry()));
        myRingView.setShapeDrawable(RingShape.valueOf(coupleRing.getRingShape()));
        myRingView.setMaterialColor(RingMaterial.valueOf(coupleRing.getRingMaterial()));

    }
}
