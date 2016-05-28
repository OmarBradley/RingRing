package olab.ringring.main.ringdesign.ringfactory.contretefactory;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import olab.ringring.main.ringdesign.customview.MyMenuRingView;
import olab.ringring.main.ringdesign.levelpolicy.RingLevel;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.main.ringdesign.ringfactory.RingFactory;
import olab.ringring.util.colorchanger.ImageColorChanger;

/**
 * Created by 재화 on 2016-05-24.
 */

public class MyMenuRingFactory implements RingFactory {

    MyMenuRingView ringView;

    public MyMenuRingFactory(MyMenuRingView smallRingView, RingJewelry jewelry, RingMaterial material, RingShape shape, RingLevel level) {
        this.ringView = ringView;
        createRingJewelry(jewelry);
        createRingShape(shape);
        createRingMaterial(material);
        createRingLevelText(level);
        createCollectingCountProgressBar(level);
    }

    @Override
    public void createRingJewelry(RingJewelry jewelry) {
        ringView.setJewelryDrawable(jewelry);
    }

    @Override
    public void createRingShape(RingShape shape) {
        ringView.setShapeDrawable(shape);
    }

    @Override
    public void createRingMaterial(RingMaterial material) {
        Drawable presentImage = ringView.getShapeDrawable();
        Bitmap imageAfterColorChange = ImageColorChanger.changeImageColor(presentImage, material.getColor());
        ringView.setShapeImageBitmap(imageAfterColorChange);
    }

    public void createRingLevelText(RingLevel level) {
        ringView.setCollectingCountText(level);
    }

    public void createCollectingCountProgressBar(RingLevel level) {
        ringView.setCollectingCountText(level);
    }

}
