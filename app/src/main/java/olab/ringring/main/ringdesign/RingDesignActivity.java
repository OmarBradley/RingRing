package olab.ringring.main.ringdesign;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
import olab.ringring.main.nav.visitor.concretevisitior.OnBackPressedVisitor;
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
import olab.ringring.main.ringdesign.ringattribute.BigRingFactory;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.ring.RingProtocol;
import olab.ringring.network.response.ring.intro.SuccessRingIntro;
import olab.ringring.util.normalvisitor.element.NomalActivityElement;
import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;
import olab.ringring.util.image.ImageHandler;
import olab.ringring.util.preperance.PropertyManager;

public class RingDesignActivity extends AppCompatActivity
        implements MainNavigationElement, NomalActivityElement {

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Getter MainNavigationFragment navigationView;
    @Bind(R.id.view_ring_level) RingLevelView ringLevelView;
    @Bind(R.id.view_big_ring) BigRingView bigRingView;

    @Getter private BigRingFactory ringFactory;
    @Setter private RingMaterial selectedMaterial;

    private MainNavigationVisitor onBackPressedVisitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_design);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        this.accept(new SetNavigationFragmentVisitor());
        this.accept(new SetToggleVisitor());
        initOnBackPressedVisitor();
        displayPresentRingLevel();
        initBigRingView();
    }

    private void initOnBackPressedVisitor(){
        onBackPressedVisitor = new OnBackPressedVisitor();
    }

    @Override
    public void accept(MainNavigationVisitor visitor) {
        visitor.visit(this, this);
    }

    @Override
    public void accept(NormalActivityVisitor visitor) {
        visitor.visit(this);
    }

    private void displayPresentRingLevel(){
        ringLevelView.setPresentRingLevel(RingLevel.FIFTEEN);
    }


    private void initBigRingView() {
        if (PropertyManager.getInstance().isDefaultRingProperty()) {
            syncBigRingView();
        } else {
            ringFactory = new BigRingFactory(bigRingView, PropertyManager.getInstance().getUserJewelry(), PropertyManager.getInstance().getUserMaterial(), PropertyManager.getInstance().getUserShape());
        }
    }

    private void syncBigRingView(){
        NetworkManager.getInstance().sendRequest(RingProtocol.makeIntroRequest(this), SuccessRingIntro.class, (request, result) -> {
            SuccessRingIntro data = result;
            setSelectedMaterial(RingMaterial.valueOf(data.getRingMaterial()));
            ringFactory = new BigRingFactory(bigRingView, RingJewelry.valueOf(data.getRingJewelry()),RingMaterial.valueOf(data.getRingMaterial()),RingShape.valueOf(data.getRingShape()));
            ringLevelView.setPresentRingLevel(RingLevel.valueOf(data.getCoupleExp()));
            attachSetRingAttributeFragment(data);
            PropertyManager.getInstance().setAllRingAttribute(RingJewelry.valueOf(data.getRingJewelry()),RingShape.valueOf(data.getRingShape()),RingMaterial.valueOf(data.getRingMaterial()));
        }, ((request, errorCode, throwable) -> {
            Toast.makeText(this, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }));
    }

    private void attachSetRingAttributeFragment(SuccessRingIntro data) {
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
                Drawable whiteDrawable = ImageHandler.changeWhiteImage(data.getBigImage());
                bigRingView.setShapeDrawable(ImageHandler.changeDrawableImageColor(whiteDrawable, selectedMaterial.getColor()));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        syncBigRingView();
    }

    @Override
    public void onBackPressed() {
        this.accept(onBackPressedVisitor);
    }



}
