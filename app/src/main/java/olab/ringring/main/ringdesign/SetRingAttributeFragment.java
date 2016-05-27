package olab.ringring.main.ringdesign;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.function.Consumer;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.main.ringdesign.choicedialog.ChoiceRingAttributeDialogBuilder;
import olab.ringring.main.ringdesign.choicedialog.ChoiceRingAttributeDialogFragment;
import olab.ringring.main.ringdesign.choicedialog.attributeview.RingDetailAttributeViewData;
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
    ChoiceRingAttributeDialogFragment choiceDialog;
    @Setter @Getter private Consumer<RingDetailAttributeViewData> onDataReceiveListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View setRingAttributeView = inflater.inflate(R.layout.fragment_set_ring_attribute, container, false);
        ButterKnife.bind(this, setRingAttributeView);
        initSetRingAttributeView(setRingShapeView, "링 모양", RingShape.PENTAGON.getSetImage(), RingCollectCount.NINE);
        initSetRingAttributeView(setRingMaterialView, "링 재질", RingMaterial.COPPER.getImage(), RingCollectCount.SEVEN);
        initSetRingAttributeView(setRingJewelryView, "보석",RingJewelry.DIAMOND.getChoiceImage(), RingCollectCount.TWO);
        return setRingAttributeView;
    }

    private void initSetRingAttributeView(SetRingAttributeView setRingAttributeView, String text, Drawable image, RingCollectCount ringCollectCount) {
        setRingAttributeView.setRingAttributeText(text);
        setRingAttributeView.setRingAttributeImage(image);
        setRingAttributeView.setRingLevelExpText(ringCollectCount);
    }

    @Override
    public void onResume() {
        super.onResume();
        buildJewelryDialog();
    }

    private void buildJewelryDialog(){
        setRingShapeView.setOnClickListenerInView(view->{
            choiceDialog = new ChoiceRingAttributeDialogBuilder()
                    .setTitle("보석 설정")
                    .setCheckClickListener((dialog, data)->{
                        dialog.dismiss();
                        setRingShapeView.setRingAttributeImage(data.getAttributeImage());
                        setRingShapeView.setRingLevelExpText(data.getCollectCount());
                        onDataReceiveListener.accept(data);
                    }).setTitleImageRes(R.drawable.ic_menu_gallery)
                    .setAttributeItems(Arrays.asList(new RingDetailAttributeViewData(RingJewelry.CRYSTAL.getName(),RingJewelry.CRYSTAL.getChoiceImage(), RingCollectCount.FOUR),
                            new RingDetailAttributeViewData(RingJewelry.DIAMOND.getName(),RingJewelry.DIAMOND.getChoiceImage(), RingCollectCount.FIVE),
                            new RingDetailAttributeViewData(RingJewelry.EMERALD.getName(),RingJewelry.EMERALD.getChoiceImage(), RingCollectCount.EIGHT),
                            new RingDetailAttributeViewData(RingJewelry.SAPPHIRE.getName(),RingJewelry.SAPPHIRE.getChoiceImage(), RingCollectCount.ONE),
                            new RingDetailAttributeViewData(RingJewelry.RUBY.getName(),RingJewelry.RUBY.getChoiceImage(), RingCollectCount.NINE),
                            new RingDetailAttributeViewData(RingJewelry.PERL.getName(),RingJewelry.PERL.getChoiceImage(), RingCollectCount.ONE)))
                    .build();
            choiceDialog.show(getActivity().getSupportFragmentManager(), "choice dialog");
        });
    }
}
