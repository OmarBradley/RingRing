package olab.ringring.main.ringdesign;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.function.BiConsumer;

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
import olab.ringring.main.ringdesign.ringattribute.RingAttributeListConstant;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.network.response.ring.intro.RingIntroResult;
import olab.ringring.util.colorchanger.ImageColorChanger;

/**
 * A simple {@link Fragment} subclass.
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SetRingAttributeFragment extends Fragment {

    @Bind(R.id.view_set_ring_shape) SetRingAttributeView shapeView;
    @Bind(R.id.view_set_ring_material) SetRingAttributeView materialView;
    @Bind(R.id.view_set_ring_jewelry) SetRingAttributeView jewelryView;

    private ChoiceRingAttributeDialogFragment choiceDialog;
    @Setter @Getter private BiConsumer<RingDetailAttributeViewData, RingAttributeListConstant> onDataReceiveListener;
    @Setter private RingIntroResult viewData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View setRingAttributeView = inflater.inflate(R.layout.fragment_set_ring_attribute, container, false);
        ButterKnife.bind(this, setRingAttributeView);
        initSetRingAttributeView(shapeView, "링 모양", RingShape.valueOf(viewData.getRingShape()).getSetImage(), RingCollectCount.valueOf(viewData.getRingShapeLevel()));
        initSetRingAttributeView(materialView, "링 재질", RingMaterial.valueOf(viewData.getRingMaterial()).getImage(), RingCollectCount.valueOf(viewData.getRingMaterialLevel()));
        initSetRingAttributeView(jewelryView, "보석",RingJewelry.valueOf(viewData.getRingJewelry()).getSetImage(), RingCollectCount.valueOf(viewData.getRingJewelryLevel()));
        buildJewelryDialog();
        buildMaterialDialog();
        buildShapeDialog();
        return setRingAttributeView;
    }

    private void initSetRingAttributeView(SetRingAttributeView setRingAttributeView, String text, Drawable image, RingCollectCount ringCollectCount) {
        setRingAttributeView.setRingAttributeText(text);
        setRingAttributeView.setRingAttributeImage(image);
        setRingAttributeView.setRingLevelExpText(ringCollectCount);
    }

    private void buildJewelryDialog() {
        jewelryView.setOnClickListenerInView(view -> {
            choiceDialog = new ChoiceRingAttributeDialogBuilder()
                    .setTitle(RingAttributeListConstant.JEWELRY.getChoiceDialogTitle())
                    .setCheckButtonClickListener((dialog, data) -> {
                        dialog.dismiss();
                        jewelryView.setRingAttributeImage(data.getSetImage());
                        jewelryView.setRingLevelExpText(data.getCollectCount());
                        onDataReceiveListener.accept(data, RingAttributeListConstant.JEWELRY);
                    }).setCancelButtonClickListener((dialog)->{
                        dialog.dismiss();
                    }).setTitleImageRes(RingAttributeListConstant.JEWELRY.getChoiceDialogTitleImageRes())
                    .setAttributeItems(Arrays.asList(new RingDetailAttributeViewData(RingJewelry.CRYSTAL.getName(), RingJewelry.getJewelryImageUsingChoiceDialog(RingJewelry.CRYSTAL,RingCollectCount.FOUR),
                            RingJewelry.CRYSTAL.getSetImage(),RingJewelry.CRYSTAL.getBigImage(), RingCollectCount.FOUR),
                            new RingDetailAttributeViewData(RingJewelry.DIAMOND.getName(), RingJewelry.getJewelryImageUsingChoiceDialog(RingJewelry.DIAMOND,RingCollectCount.TEN),
                                    RingJewelry.DIAMOND.getSetImage(), RingJewelry.DIAMOND.getBigImage(),RingCollectCount.TEN),
                            new RingDetailAttributeViewData(RingJewelry.EMERALD.getName(), RingJewelry.getJewelryImageUsingChoiceDialog(RingJewelry.EMERALD,RingCollectCount.TEN),
                                    RingJewelry.EMERALD.getSetImage(), RingJewelry.EMERALD.getBigImage(),RingCollectCount.TEN),
                            new RingDetailAttributeViewData(RingJewelry.SAPPHIRE.getName(), RingJewelry.getJewelryImageUsingChoiceDialog(RingJewelry.SAPPHIRE,RingCollectCount.ONE),
                                    RingJewelry.SAPPHIRE.getSetImage(), RingJewelry.SAPPHIRE.getBigImage(),RingCollectCount.ONE),
                            new RingDetailAttributeViewData(RingJewelry.RUBY.getName(), RingJewelry.getJewelryImageUsingChoiceDialog(RingJewelry.RUBY,RingCollectCount.NINE),
                                    RingJewelry.RUBY.getSetImage(), RingJewelry.RUBY.getBigImage(),RingCollectCount.NINE),
                            new RingDetailAttributeViewData(RingJewelry.PERL.getName(), RingJewelry.getJewelryImageUsingChoiceDialog(RingJewelry.PERL,RingCollectCount.ONE),
                                    RingJewelry.PERL.getSetImage(),RingJewelry.PERL.getBigImage(), RingCollectCount.ONE)))
                    .build();
            choiceDialog.show(getActivity().getSupportFragmentManager(), "choice dialog");
        });
    }
    private void buildMaterialDialog() {
        materialView.setOnClickListenerInView(view -> {
            choiceDialog = new ChoiceRingAttributeDialogBuilder()
                    .setTitle(RingAttributeListConstant.MATERIAL.getChoiceDialogTitle())
                    .setCheckButtonClickListener((dialog, data) -> {
                        dialog.dismiss();
                        materialView.setRingAttributeImage(data.getSetImage());
                        materialView.setRingLevelExpText(data.getCollectCount());
                        onDataReceiveListener.accept(data, RingAttributeListConstant.MATERIAL);
                    }).setCancelButtonClickListener((dialog)->{
                        dialog.dismiss();
                    }).setTitleImageRes(RingAttributeListConstant.MATERIAL.getChoiceDialogTitleImageRes())
                    .setAttributeItems(Arrays.asList(new RingDetailAttributeViewData(RingMaterial.PLASTIC.getName(), RingMaterial.getMaterialImageUsingChoiceDialog(RingMaterial.PLASTIC, RingCollectCount.FOUR),
                            RingMaterial.PLASTIC.getImage(), ImageColorChanger.changeDrawableImageColor(RingShape.valueOf(viewData.getRingShape()).getBigImage(),RingMaterial.PLASTIC.getColor()) , RingCollectCount.FOUR),
                            new RingDetailAttributeViewData(RingMaterial.IRON.getName(), RingMaterial.getMaterialImageUsingChoiceDialog(RingMaterial.IRON, RingCollectCount.SEVEN),
                                    RingMaterial.IRON.getImage(), ImageColorChanger.changeDrawableImageColor(RingShape.valueOf(viewData.getRingShape()).getBigImage(),RingMaterial.IRON.getColor()) , RingCollectCount.SEVEN),
                            new RingDetailAttributeViewData(RingMaterial.COPPER.getName(), RingMaterial.getMaterialImageUsingChoiceDialog(RingMaterial.COPPER, RingCollectCount.TEN),
                                    RingMaterial.COPPER.getImage(), ImageColorChanger.changeDrawableImageColor(RingShape.valueOf(viewData.getRingShape()).getBigImage(),RingMaterial.COPPER.getColor()) , RingCollectCount.TEN),
                            new RingDetailAttributeViewData(RingMaterial.SILVER.getName(), RingMaterial.getMaterialImageUsingChoiceDialog(RingMaterial.SILVER, RingCollectCount.TEN),
                                    RingMaterial.SILVER.getImage(), ImageColorChanger.changeDrawableImageColor(RingShape.valueOf(viewData.getRingShape()).getBigImage(),RingMaterial.SILVER.getColor()) , RingCollectCount.TEN),
                            new RingDetailAttributeViewData(RingMaterial.WHITEGOLD.getName(), RingMaterial.getMaterialImageUsingChoiceDialog(RingMaterial.WHITEGOLD, RingCollectCount.FOUR),
                                    RingMaterial.WHITEGOLD.getImage(), ImageColorChanger.changeDrawableImageColor(RingShape.valueOf(viewData.getRingShape()).getBigImage(),RingMaterial.WHITEGOLD.getColor()) , RingCollectCount.EIGHT),
                            new RingDetailAttributeViewData(RingMaterial.GOLD.getName(), RingMaterial.getMaterialImageUsingChoiceDialog(RingMaterial.GOLD, RingCollectCount.TEN),
                                    RingMaterial.GOLD.getImage(), ImageColorChanger.changeDrawableImageColor(RingShape.valueOf(viewData.getRingShape()).getBigImage(),RingMaterial.GOLD.getColor()) , RingCollectCount.TEN)))
                    .build();
            choiceDialog.show(getActivity().getSupportFragmentManager(), "choice dialog");
        });
    }

    private void buildShapeDialog() {
        shapeView.setOnClickListenerInView(view -> {
            choiceDialog = new ChoiceRingAttributeDialogBuilder()
                    .setTitle(RingAttributeListConstant.SHAPE.getChoiceDialogTitle())
                    .setCheckButtonClickListener((dialog, data) -> {
                        dialog.dismiss();
                        shapeView.setRingAttributeImage(data.getSetImage());
                        shapeView.setRingLevelExpText(data.getCollectCount());
                        onDataReceiveListener.accept(data, RingAttributeListConstant.SHAPE);
                    }).setCancelButtonClickListener((dialog) -> {
                        dialog.dismiss();
                    }).setTitleImageRes(RingAttributeListConstant.SHAPE.getChoiceDialogTitleImageRes())
                    .setAttributeItems(Arrays.asList(new RingDetailAttributeViewData(RingShape.CIRCLE.getName(), RingShape.getShapeImageUsingChoiceDialog(RingShape.CIRCLE, RingCollectCount.FOUR),
                            RingShape.CIRCLE.getSetImage(), RingShape.CIRCLE.getBigImage(), RingCollectCount.FOUR),
                            new RingDetailAttributeViewData(RingShape.TRIANGLE.getName(), RingShape.getShapeImageUsingChoiceDialog(RingShape.TRIANGLE, RingCollectCount.FIVE),
                                    RingShape.TRIANGLE.getSetImage(), RingShape.TRIANGLE.getBigImage(), RingCollectCount.FIVE),
                            new RingDetailAttributeViewData(RingShape.RECTANGLE.getName(), RingShape.getShapeImageUsingChoiceDialog(RingShape.RECTANGLE, RingCollectCount.EIGHT),
                                    RingShape.RECTANGLE.getSetImage(), RingShape.RECTANGLE.getBigImage(), RingCollectCount.EIGHT),
                            new RingDetailAttributeViewData(RingShape.PENTAGON.getName(), RingShape.getShapeImageUsingChoiceDialog(RingShape.PENTAGON, RingCollectCount.TEN),
                                    RingShape.PENTAGON.getSetImage(), RingShape.PENTAGON.getBigImage(), RingCollectCount.TEN),
                            new RingDetailAttributeViewData(RingShape.HEXAGON.getName(), RingShape.getShapeImageUsingChoiceDialog(RingShape.HEXAGON, RingCollectCount.TEN),
                                    RingShape.HEXAGON.getSetImage(), RingShape.HEXAGON.getBigImage(), RingCollectCount.TEN),
                            new RingDetailAttributeViewData(RingShape.OCTAGON.getName(), RingShape.getShapeImageUsingChoiceDialog(RingShape.OCTAGON, RingCollectCount.ONE),
                                    RingShape.OCTAGON.getSetImage(), RingShape.OCTAGON.getBigImage(), RingCollectCount.ONE)))
                    .build();
            choiceDialog.show(getActivity().getSupportFragmentManager(), "choice dialog");
        });
    }

    // TODO: 2016-05-28 반지 설정 변경 후 갱신되는 로직 삽입하기..
    @Override
    public void onResume() {
        super.onResume();
    }
}
