package olab.ringring.main.mymenu.ringattribute;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import olab.ringring.main.ringdesign.customview.MyMenuRingView;
import olab.ringring.main.ringdesign.levelpolicy.RingLevel;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.util.image.ImageHandler;

/**
 * Created by 재화 on 2016-05-24.
 */

public class MyMenuRingFactory {

    MyMenuRingView ringView;

    public MyMenuRingFactory(MyMenuRingView smallRingView, MyMenuRingJewelry jewelry, RingMaterial material, MyMenuRingShape shape, RingLevel level) {
        this.ringView = ringView;
        createRingJewelry(jewelry);
        createRingShape(shape);
        createRingMaterial(material);
        createRingLevelText(level);
        createCollectingCountProgressBar(level);
    }

    public void createRingJewelry(MyMenuRingJewelry jewelry) {
        ringView.setJewelryDrawable(jewelry);
    }

    public void createRingShape(MyMenuRingShape shape) {
        ringView.setShapeDrawable(shape);
    }

    public void createRingMaterial(RingMaterial material) {
        Drawable presentImage = ringView.getShapeDrawable();
        Bitmap imageAfterColorChange = ImageHandler.changeImageColor(presentImage, material.getColor());
        ringView.setShapeImageBitmap(imageAfterColorChange);
    }

    public void createRingLevelText(RingLevel level) {
        ringView.setCollectingCountText(level);
    }

    public void createCollectingCountProgressBar(RingLevel level) {
        ringView.setCollectingCountText(level);
    }

}
