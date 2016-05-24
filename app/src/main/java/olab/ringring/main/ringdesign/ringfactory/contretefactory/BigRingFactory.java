package olab.ringring.main.ringdesign.ringfactory.contretefactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import olab.ringring.main.ringdesign.customview.BigRingView;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.main.ringdesign.ringfactory.RingFactory;
import olab.ringring.util.colorchanger.ColorChanger;

/**
 * Created by 재화 on 2016-05-24.
 */

public class BigRingFactory implements RingFactory {

    BigRingView ringView;

    public BigRingFactory(BigRingView ringView, RingJewelry jewelry, RingMaterial material, RingShape shape) {
        this.ringView = ringView;
        createRingJewelry(jewelry);
        createRingShape(shape);
        createRingMaterial(material);
    }

    @Override
    public void createRingJewelry(RingJewelry jewelry) {
        ringView.getImageBigRingJewelry().setImageDrawable(jewelry.getRingJewelryImage());
    }

    @Override
    public void createRingMaterial(RingMaterial material) {
        Drawable presentImage = ringView.getImageBigRingShape().getDrawable();
        Bitmap imageAfterColorChange = ColorChanger.changeImageColor(presentImage, material.getRingMaterialColor());
        ringView.getImageBigRingShape().setImageBitmap(imageAfterColorChange);
    }

    @Override
    public void createRingShape(RingShape shape) {
        ringView.getImageBigRingShape().setImageDrawable(shape.getRingShapeBigImage());
    }
}
