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
import olab.ringring.network.response.ring.intro.RingIntroResult;

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
    @Setter private RingIntroResult viewData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View setRingAttributeView = inflater.inflate(R.layout.fragment_set_ring_attribute, container, false);
        ButterKnife.bind(this, setRingAttributeView);
        initSetRingAttributeView(setRingShapeView, "링 모양", RingShape.valueOf(viewData.getRingShape()).getSetImage(), RingCollectCount.valueOf(viewData.getRingShapeLevel()));
        initSetRingAttributeView(setRingMaterialView, "링 재질", RingMaterial.valueOf(viewData.getRingMaterial()).getImage(), RingCollectCount.valueOf(viewData.getRingMaterialLevel()));
        initSetRingAttributeView(setRingJewelryView, "보석",RingJewelry.valueOf(viewData.getRingJewelry()).getSetImage(), RingCollectCount.valueOf(viewData.getRingJewelryLevel()));
        buildJewelryDialog();
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

    }

    private void buildJewelryDialog() {
        setRingJewelryView.setOnClickListenerInView(view -> {
            choiceDialog = new ChoiceRingAttributeDialogBuilder()
                    .setTitle("보석 설정")
                    .setCheckClickListener((dialog, data) -> {
                        dialog.dismiss();
                        setRingJewelryView.setRingAttributeImage(data.getSetImage());
                        setRingJewelryView.setRingLevelExpText(data.getCollectCount());
                        onDataReceiveListener.accept(data);
                    }).setTitleImageRes(R.drawable.choice_dialog_jewelry_title_image)
                    .setAttributeItems(Arrays.asList(new RingDetailAttributeViewData(RingJewelry.CRYSTAL.getName(), RingJewelry.CRYSTAL.getChoiceImage(), RingJewelry.CRYSTAL.getSetImage(),RingJewelry.CRYSTAL.getBigImage(), RingCollectCount.FOUR),
                            new RingDetailAttributeViewData(RingJewelry.DIAMOND.getName(), RingJewelry.DIAMOND.getChoiceImage(), RingJewelry.DIAMOND.getSetImage(), RingJewelry.DIAMOND.getBigImage(),RingCollectCount.FIVE),
                            new RingDetailAttributeViewData(RingJewelry.EMERALD.getName(), RingJewelry.EMERALD.getChoiceImage(), RingJewelry.EMERALD.getSetImage(), RingJewelry.EMERALD.getBigImage(),RingCollectCount.EIGHT),
                            new RingDetailAttributeViewData(RingJewelry.SAPPHIRE.getName(), RingJewelry.SAPPHIRE.getChoiceImage(), RingJewelry.SAPPHIRE.getSetImage(), RingJewelry.SAPPHIRE.getBigImage(),RingCollectCount.ONE),
                            new RingDetailAttributeViewData(RingJewelry.RUBY.getName(), RingJewelry.RUBY.getChoiceImage(), RingJewelry.RUBY.getSetImage(), RingJewelry.RUBY.getBigImage(),RingCollectCount.NINE),
                            new RingDetailAttributeViewData(RingJewelry.PERL.getName(), RingJewelry.PERL.getChoiceImage(), RingJewelry.PERL.getSetImage(),RingJewelry.PERL.getBigImage(), RingCollectCount.ONE)))
                    .build();
            choiceDialog.show(getActivity().getSupportFragmentManager(), "choice dialog");
        });
    }
}
