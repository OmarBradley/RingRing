package olab.ringring.main.ringdesign;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import olab.ringring.R;
import olab.ringring.main.home.chat.ChatFragment;
import olab.ringring.main.nav.MainNavigationFragment;
import olab.ringring.main.nav.visitor.element.MainNavigationElement;
import olab.ringring.main.ringdesign.customview.SetRingAttributeView;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;

/**
 * A simple {@link Fragment} subclass.
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SetRingAttributeFragment extends Fragment {

    @Bind(R.id.view_set_ring_shape) SetRingAttributeView setRingShapeView;
    @Bind(R.id.view_set_ring_material) SetRingAttributeView setRingMaterialView;
    @Bind(R.id.view_set_ring_jewelry) SetRingAttributeView setRingJewelryView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View setRingAttributeView = inflater.inflate(R.layout.fragment_set_ring_attribute, container, false);
        ButterKnife.bind(this, setRingAttributeView);
        initSetRingAttributeView(setRingShapeView, "링 모양", RingShape.PENTAGON.getRingShapeSetViewImage(), RingCollectCount.NINE);
        initSetRingAttributeView(setRingMaterialView, "링 재질", RingMaterial.COPPER.getRingMaterialSetViewImage(), RingCollectCount.SEVEN);
        initSetRingAttributeView(setRingJewelryView, "보석",RingJewelry.DIAMOND.getRingJewelrySetViewImage(), RingCollectCount.TWO);
        return setRingAttributeView;
    }

    private void initSetRingAttributeView(SetRingAttributeView setRingAttributeView, String text, Drawable image, RingCollectCount ringCollectCount) {
        setRingAttributeView.setRingAttributeText(text);
        setRingAttributeView.setRingAttributeImage(image);
        setRingAttributeView.setRingLevelExpText(ringCollectCount);
    }

}
