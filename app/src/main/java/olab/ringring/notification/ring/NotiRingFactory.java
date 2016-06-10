package olab.ringring.notification.ring;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.util.image.ImageHandler;

/**
 * Created by 재화 on 2016-06-06.
 */
public class NotiRingFactory {
    private ImageView shapeImage;
    private ImageView jewelryImage;
    private RingMaterial material;
    private NotiRingJewelry jewelry;
    private NotiRingShape shape;

    public NotiRingFactory(ImageView jewelryImage, ImageView shapeImage,
                           NotiRingJewelry jewelry, NotiRingShape shape, RingMaterial material) {
        this.jewelryImage = jewelryImage;
        this.shapeImage = shapeImage;
        this.material = material;
        this.jewelry = jewelry;
        this.shape = shape;
    }

    private void createRingJewelry(NotiRingJewelry jewelry) {
        jewelryImage.setImageDrawable(jewelry.getImage());
    }

    private void createRingShape(NotiRingShape shape) {
        Drawable presentImage = shape.getImage();
        Bitmap imageAfterColorChange = ImageHandler.changeImageColor(presentImage, material.getColor());
        shapeImage.setImageBitmap(imageAfterColorChange);
    }

    public void build(){
        createRingJewelry(jewelry);
        createRingShape(shape);
    }
}
