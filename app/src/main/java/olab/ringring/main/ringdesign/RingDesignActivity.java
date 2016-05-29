package olab.ringring.main.ringdesign;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.main.nav.MainNavigationFragment;
import olab.ringring.main.nav.visitor.concretevisitior.SetNavigationFragmentVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.nav.visitor.element.MainNavigationElement;
import olab.ringring.main.nav.visitor.MainNavigationVisitor;
import olab.ringring.main.ringdesign.customview.BigRingView;
import olab.ringring.main.ringdesign.customview.RingLevelView;
import olab.ringring.main.ringdesign.levelpolicy.RingLevel;
import olab.ringring.main.ringdesign.ringattribute.RingAttributeListConstant;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.main.ringdesign.ringfactory.contretefactory.BigRingFactory;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.ring.RingProtocol;
import olab.ringring.network.response.ring.intro.RingIntroResult;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarTitleVisitor;
import olab.ringring.util.colorchanger.ImageColorChanger;

public class RingDesignActivity extends AppCompatActivity
        implements MainNavigationElement, ActionBarElement {

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Getter MainNavigationFragment navigationView;
    @Bind(R.id.view_ring_level) RingLevelView ringLevelView;
    @Bind(R.id.view_big_ring) BigRingView bigRingView;

    private BigRingFactory ringFactory;
    @Setter private RingMaterial selectedMaterial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_design);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        this.accept(new SetNavigationFragmentVisitor());
        this.accept(new SetToggleVisitor());
        displayPresentRingLevel();
        initBigRingView();
    }

    @Override
    public void accept(MainNavigationVisitor visitor) {
        visitor.visit(this, this);
    }

    @Override
    public void accept(ActionbarVisitor visitor) {
        visitor.visit(this);
    }

    private void displayPresentRingLevel(){
        ringLevelView.setPresentRingLevel(RingLevel.FIFTEEN);
    }

    private void initBigRingView(){
        NetworkManager.getInstance().getResult(RingProtocol.makeIntroRequest(this), RingIntroResult.class, (request, result) -> {
            RingIntroResult data = result;
            setSelectedMaterial(RingMaterial.valueOf(data.getRingMaterial()));
            ringFactory = new BigRingFactory(bigRingView, RingJewelry.valueOf(data.getRingJewelry()),RingMaterial.valueOf(data.getRingMaterial()),RingShape.valueOf(data.getRingShape()));
            ringLevelView.setPresentRingLevel(RingLevel.valueOf(data.getCoupleExp()));
            attachSetRingAttributeFragment(data);
        }, ((request, integer, throwable) -> {
            Toast.makeText(this, "알수 없는 에러" + integer, Toast.LENGTH_SHORT).show();
        }));
    }

    private void attachSetRingAttributeFragment(RingIntroResult data) {
        SetRingAttributeFragment setRingAttributeFragment = new SetRingAttributeFragment();
        setRingAttributeFragment.setViewData(data);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_set_ring_attribute_fragment, setRingAttributeFragment);
        fragmentTransaction.commit();
        changeBigRingJewelryImage(setRingAttributeFragment);
    }

    private void changeBigRingJewelryImage(SetRingAttributeFragment setRingAttributeFragment){
        setRingAttributeFragment.setOnDataReceiveListener((data, attributeListConstant) -> {
            if (attributeListConstant.getAttributeName() == RingAttributeListConstant.JEWELRY.getAttributeName()) {
                bigRingView.setJewelryDrawable(data.getBigImage());
            } else {
                Drawable whiteDrawable = ImageColorChanger.changeWhiteImage(data.getBigImage());
                bigRingView.setShapeDrawable(ImageColorChanger.changeDrawableImageColor(whiteDrawable, selectedMaterial.getColor()));
            }
        });
    }

    // TODO: 2016-05-28 반지 설정 변경 후 갱신되는 로직 삽입하기..
    @Override
    protected void onResume() {
        super.onResume();
        /*initBigRingView();*/
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



}
