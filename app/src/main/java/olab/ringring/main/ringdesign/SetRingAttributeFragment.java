package olab.ringring.main.ringdesign;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.ring.RingProtocol;
import olab.ringring.network.response.ring.intro.SuccessRingIntro;
import olab.ringring.network.response.ring.setwindow.jewelry.SuccessRingJewelry;
import olab.ringring.network.response.ring.setwindow.material.SuccessRingMaterial;
import olab.ringring.network.response.ring.setwindow.shape.SuccessRingShape;
import olab.ringring.util.preperance.PropertyManager;

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
    @Setter private SuccessRingIntro viewData;
    @Setter private SuccessRingShape shapeData;
    @Setter private SuccessRingMaterial materialData;
    @Setter private SuccessRingJewelry jewelryData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View setRingAttributeView = inflater.inflate(R.layout.fragment_set_ring_attribute, container, false);
        ButterKnife.bind(this, setRingAttributeView);
        initSetRingAttributeView(shapeView, "링 모양", RingShape.valueOf(viewData.getRingShape()).getSetImage(), RingCollectCount.MAX_COUNTING_NUMBER);
        initSetRingAttributeView(materialView, "링 재질", RingMaterial.valueOf(viewData.getRingMaterial()).getImage(),RingCollectCount.MAX_COUNTING_NUMBER);
        initSetRingAttributeView(jewelryView, "보석",RingJewelry.valueOf(viewData.getRingJewelry()).getSetImage(), RingCollectCount.MAX_COUNTING_NUMBER);
        initShapeData();
        initMaterialData();
        initJewelryData();
        return setRingAttributeView;
    }

    private void initSetRingAttributeView(SetRingAttributeView setRingAttributeView, String text, Drawable image, RingCollectCount ringCollectCount) {
        setRingAttributeView.setRingAttributeText(text);
        setRingAttributeView.setRingAttributeImage(image);
        setRingAttributeView.setRingLevelExpText(ringCollectCount);
    }

    private void initShapeData() {
        NetworkManager.getInstance().sendRequest(RingProtocol.SetWindow.makeSetShapeWindowRequest(getActivity()), SuccessRingShape.class, (request, result) -> {
            shapeData = result;
            initSetRingAttributeView(shapeView, "링 모양", RingShape.valueOf(shapeData.getRingShape()).getSetImage(),  RingCollectCount.MAX_COUNTING_NUMBER);
            PropertyManager.getInstance().setUserShape(RingShape.valueOf(shapeData.getRingShape()));
            ((RingDesignActivity) getActivity()).getRingFactory().createRingShape(RingShape.valueOf(shapeData.getRingShape()));
        }, ((request, errorCode, throwable) -> {
            Toast.makeText(getActivity(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }));
    }

    private void initMaterialData() {
        NetworkManager.getInstance().sendRequest(RingProtocol.SetWindow.makeSetMaterialWindowRequest(getActivity()), SuccessRingMaterial.class, (request, result) -> {
            materialData = result;
            initSetRingAttributeView(materialView, "링 재질", RingMaterial.valueOf(materialData.getRingMaterial()).getImage(),  RingCollectCount.MAX_COUNTING_NUMBER);
            PropertyManager.getInstance().setUserMaterial(RingMaterial.valueOf(materialData.getRingMaterial()));
            ((RingDesignActivity) getActivity()).getRingFactory().createRingMaterial(RingMaterial.valueOf(materialData.getRingMaterial()));
        }, ((request, errorCode, throwable) -> {
            Toast.makeText(getActivity(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }));
    }

    private void initJewelryData() {
        NetworkManager.getInstance().sendRequest(RingProtocol.SetWindow.makeSetJewelryWindowRequest(getActivity()), SuccessRingJewelry.class, (request, result) -> {
            jewelryData = result;
            initSetRingAttributeView(jewelryView, "보석", RingJewelry.valueOf(jewelryData.getRingJewelry()).getSetImage(),  RingCollectCount.MAX_COUNTING_NUMBER);
            PropertyManager.getInstance().setUserJewelry(RingJewelry.valueOf(jewelryData.getRingJewelry()));
            ((RingDesignActivity) getActivity()).getRingFactory().createRingJewelry(RingJewelry.valueOf(jewelryData.getRingJewelry()));
        }, ((request, errorCode, throwable) -> {
            Toast.makeText(getActivity(), errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        }));
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        buildJewelryDialog();
        buildMaterialDialog();
        buildShapeDialog();
     }

    private void buildJewelryDialog() {
        jewelryView.setOnClickListenerInView(view -> {
            choiceDialog = new ChoiceRingAttributeDialogBuilder()
                    .setTitle(RingAttributeListConstant.JEWELRY.getChoiceDialogTitle())
                    .setCheckButtonClickListener((dialog, data) -> {
                        dialog.dismiss();
                        initJewelryData();
                    }).setCancelButtonClickListener((dialog) -> {
                        dialog.dismiss();
                    }).setTitleImageRes(RingAttributeListConstant.JEWELRY.getChoiceDialogTitleImageRes())
                    .setAttributeItems(Arrays.asList(RingJewelry.CRYSTAL.getAttributeData(RingCollectCount.valueOf(jewelryData.getJewelryCrystalLevel())),
                            RingJewelry.DIAMOND.getAttributeData(RingCollectCount.valueOf(jewelryData.getJewelryDiamondLevel())),
                            RingJewelry.EMERALD.getAttributeData(RingCollectCount.valueOf(jewelryData.getJewelryEmeraldLevel())),
                            RingJewelry.SAPPHIRE.getAttributeData(RingCollectCount.valueOf(jewelryData.getJewelrySapphireLevel())),
                            RingJewelry.RUBY.getAttributeData(RingCollectCount.valueOf(jewelryData.getJewelryRubyLevel())),
                            RingJewelry.PERL.getAttributeData(RingCollectCount.valueOf(jewelryData.getJewelryPerlLevel()))))
                    .setCheckedItemTag(jewelryData.getRingJewelry()).build();
            choiceDialog.show(getActivity().getSupportFragmentManager(), "choice dialog");
        });
    }

    private void buildMaterialDialog() {
        materialView.setOnClickListenerInView(view -> {
            choiceDialog = new ChoiceRingAttributeDialogBuilder()
                    .setTitle(RingAttributeListConstant.MATERIAL.getChoiceDialogTitle())
                    .setCheckButtonClickListener((dialog, data) -> {
                        dialog.dismiss();
                        initMaterialData();
                    }).setCancelButtonClickListener((dialog) -> {
                        dialog.dismiss();
                    }).setTitleImageRes(RingAttributeListConstant.MATERIAL.getChoiceDialogTitleImageRes())
                    .setAttributeItems(Arrays.asList(RingMaterial.PLASTIC.getAttributeData(RingCollectCount.valueOf(materialData.getMaterialPlasticLevel()), viewData)
                            , RingMaterial.IRON.getAttributeData(RingCollectCount.valueOf(materialData.getMaterialIronLevel()), viewData)
                            , RingMaterial.COPPER.getAttributeData(RingCollectCount.valueOf(materialData.getMaterialCopperLevel()), viewData)
                            , RingMaterial.SILVER.getAttributeData(RingCollectCount.valueOf(materialData.getMaterialSilverLevel()), viewData)
                            , RingMaterial.WHITEGOLD.getAttributeData(RingCollectCount.valueOf(materialData.getMaterialWhiteGoldLevel()), viewData)
                            , RingMaterial.GOLD.getAttributeData(RingCollectCount.valueOf(materialData.getMaterialGoldLevel()), viewData)))
                    .setCheckedItemTag(materialData.getRingMaterial()).build();
            choiceDialog.show(getActivity().getSupportFragmentManager(), "choice dialog");
        });
    }

    private void buildShapeDialog() {
        shapeView.setOnClickListenerInView(view -> {
            choiceDialog = new ChoiceRingAttributeDialogBuilder()
                    .setTitle(RingAttributeListConstant.SHAPE.getChoiceDialogTitle())
                    .setCheckButtonClickListener((dialog, data) -> {
                        dialog.dismiss();
                        initShapeData();
                    }).setCancelButtonClickListener((dialog) -> {
                        dialog.dismiss();
                    }).setTitleImageRes(RingAttributeListConstant.SHAPE.getChoiceDialogTitleImageRes())
                    .setAttributeItems(Arrays.asList(RingShape.CIRCLE.getAttributeData(RingCollectCount.valueOf(shapeData.getShapeCircleLevel()))
                            ,RingShape.TRIANGLE.getAttributeData(RingCollectCount.valueOf(shapeData.getShapeTriangleLevel()))
                            ,RingShape.RECTANGLE.getAttributeData(RingCollectCount.valueOf(shapeData.getShapeRectangleLevel()))
                            ,RingShape.PENTAGON.getAttributeData(RingCollectCount.valueOf(shapeData.getShapePentagonLevel()))
                            ,RingShape.HEXAGON.getAttributeData(RingCollectCount.valueOf(shapeData.getShapeHexagonLevel()))
                            ,RingShape.OCTAGON.getAttributeData(RingCollectCount.valueOf(shapeData.getShapeOctagonLevel()))))
                    .setCheckedItemTag(shapeData.getRingShape()).build();
            choiceDialog.show(getActivity().getSupportFragmentManager(), "choice dialog");
        });
    }
}
