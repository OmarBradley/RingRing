package olab.ringring.main.ringdesign.ringattribute;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import olab.ringring.main.ringdesign.customview.BigRingView;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.util.image.ImageHandler;

/**
 * Created by 재화 on 2016-05-24.
 */

public class BigRingFactory  {

    private BigRingView ringView;
    private RingShape shape;
    private RingMaterial material;

    public BigRingFactory(BigRingView ringView, RingJewelry jewelry, RingMaterial material, RingShape shape) {
        this.ringView = ringView;
        this.material = material;
        this.shape = shape;
        createRingJewelry(jewelry);
        createRingShape(shape);
        createRingMaterial(material);
    }


    public void createRingJewelry(RingJewelry jewelry) {
        ringView.setJewelryDrawable(jewelry);
    }


    public void createRingShape(RingShape shape) {
        this.shape = shape;
        Drawable presentImage = shape.getBigImage();
        Bitmap imageAfterColorChange = ImageHandler.changeImageColor(presentImage, material.getColor());
        ringView.setShapeImageBitmap(imageAfterColorChange);
    }

    public void createRingMaterial(RingMaterial material) {
        this.material = material;
        Drawable presentImage = shape.getBigImage();
        Bitmap imageAfterColorChange = ImageHandler.changeImageColor(presentImage, material.getColor());
        ringView.setShapeImageBitmap(imageAfterColorChange);

    }

}
