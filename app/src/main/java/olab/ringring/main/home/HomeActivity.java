package olab.ringring.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.main.home.chat.ChatFragment;
import olab.ringring.main.home.ring.HomeRingFactory;
import olab.ringring.main.home.ring.HomeRingJewelry;
import olab.ringring.main.home.ring.HomeRingShape;
import olab.ringring.main.mymenu.myaccount.MyAccountActivity;
import olab.ringring.main.nav.visitor.concretevisitior.OnBackPressedVisitor;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.home.MyMenuIntroCoupleRing;
import olab.ringring.network.response.mymenu.home.SuccessMyMenuIntro;
import olab.ringring.notification.RingNotificationActivity;
import olab.ringring.util.preperance.PropertyManager;
import olab.ringring.main.home.customview.ChatProfileView;
import olab.ringring.main.nav.visitor.MainNavigationVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetNavigationFragmentVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.nav.visitor.element.MainNavigationElement;
import olab.ringring.util.normalvisitor.element.NomalActivityElement;
import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;
import olab.ringring.util.normalvisitor.visitor.concretevisitor.DeleteActionBarTitleVisitor;

public class HomeActivity extends AppCompatActivity
        implements MainNavigationElement, NomalActivityElement {

    public static final int USER_ID = 1;
    public static final int LOVER_ID = 2;
    public static final int CHAT_DAY_ID = 3;

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.text_couple_d_day) TextView coupleDDayText;
    @Bind(R.id.image_home_ring_shape) ImageView shapeView;
    @Bind(R.id.image_home_ring_jewelry) ImageView jewelryView;
    @Bind(R.id.profile_view_user) ChatProfileView userProfileView;
    @Bind(R.id.profile_view_lover) ChatProfileView loverProfileView;
    private HomeRingFactory ringFactory;

    private MainNavigationVisitor onBackPressedVisitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        this.accept(new SetNavigationFragmentVisitor());
        this.accept(new SetToggleVisitor());
        this.accept(new DeleteActionBarTitleVisitor());
        initOnBackPressedVisitor();
        setElevationInChatProfileView();
        attachChatFragment();
        getHomeInfo();
        initHomeRing();
        setOnClickListenerInUserProfile();
    }

    @Override
    public void accept(NormalActivityVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(MainNavigationVisitor visitor) {
        visitor.visit(this, this);
    }

    private void initOnBackPressedVisitor(){
        onBackPressedVisitor = new OnBackPressedVisitor();
    }

    private void setElevationInChatProfileView(){
        userProfileView.setElevation(2.0f);
        loverProfileView.setElevation(2.0f);
    }

    @Override
    public void onBackPressed() {
        this.accept(onBackPressedVisitor);
    }

    private void initHomeRing() {
        if (!PropertyManager.getInstance().isDefaultRingProperty()) {
            ringFactory = new HomeRingFactory(jewelryView, shapeView, HomeRingJewelry.valueOf(PropertyManager.getInstance().getUserJewelry().name()),
                    HomeRingShape.valueOf(PropertyManager.getInstance().getUserShape().name()), RingMaterial.valueOf(PropertyManager.getInstance().getUserMaterial().name()));
            ringFactory.build();
        }
        ringFactory.setOnClickListener((view) -> {
            Intent intent = new Intent(this, RingNotificationActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void attachChatFragment(){
        ChatFragment chatFragment = new ChatFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_chat_fragment, chatFragment);
        fragmentTransaction.commit();
    }

    private void getHomeInfo() {
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeHomeRequest(this), SuccessMyMenuIntro.class, (request, result) -> {
            initView(result);
            PropertyManager.getInstance().setUserProperty(result);
        }, (request, errorCode, throwable) -> {
            Toast.makeText(this, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initView(SuccessMyMenuIntro homeInfo){
        coupleDDayText.setText(""+homeInfo.getCoupleDuringDate());
        attachImageInImageView(userProfileView.getUserProfileImage(),homeInfo.getUserProfile(), R.drawable.default_profile_image );

        // TODO: 2016-06-05 lover image 속성 저장을 하지 않고 있음
        attachImageInImageView(loverProfileView.getUserProfileImage(), homeInfo.getUserProfile(), R.drawable.default_profile_image);
        userProfileView.setUserName(homeInfo.getUserNickname());
        loverProfileView.setUserName(homeInfo.getLoverNickname());
        initHomeRing(homeInfo);
    }

    private void attachImageInImageView(ImageView imageView, String imageUrl, int defaultImageRes) {
        if (imageUrl != null || !TextUtils.isEmpty(imageUrl) || !imageUrl.contains("127.0.0.1:3000") || !imageUrl.equals("-")) {
            Glide.with(this).load(imageUrl).into(imageView);
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(RingRingApplication.getContext(), defaultImageRes));
        }
    }

    private void initHomeRing(SuccessMyMenuIntro homeInfo){
        MyMenuIntroCoupleRing coupleRing = homeInfo.getCoupleRings().get(0);
        ringFactory = new HomeRingFactory(jewelryView, shapeView , HomeRingJewelry.valueOf(coupleRing.getRingJewelry()),
                HomeRingShape.valueOf(coupleRing.getRingShape()), RingMaterial.valueOf(coupleRing.getRingMaterial()));
        ringFactory.build();
        PropertyManager.getInstance().setAllRingAttribute(RingJewelry.valueOf(coupleRing.getRingJewelry()), RingShape.valueOf(coupleRing.getRingShape()), RingMaterial.valueOf(coupleRing.getRingMaterial()));
    }


    private void setOnClickListenerInUserProfile(){
        userProfileView.setOnClickListener(view -> {
            Intent intent = new Intent(this, MyAccountActivity.class);
            startActivity(intent);
        });
    }
}



