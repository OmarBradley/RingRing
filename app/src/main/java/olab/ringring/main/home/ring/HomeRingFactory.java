package olab.ringring.main.home.ring;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.util.colorchanger.ImageColorChanger;

/**
 * Created by 재화 on 2016-06-05.
 */
public class HomeRingFactory {

    private ImageView shapeImage;
    private ImageView jewelryImage;
    private RingMaterial material;
    private HomeRingJewelry jewelry;
    private HomeRingShape shape;

    public HomeRingFactory(ImageView jewelryImage, ImageView shapeImage,
                           HomeRingJewelry jewelry, HomeRingShape shape, RingMaterial material) {
        this.jewelryImage = jewelryImage;
        this.shapeImage = shapeImage;
        this.material = material;
        this.jewelry = jewelry;
        this.shape = shape;
    }

    private void createRingJewelry(HomeRingJewelry jewelry) {
        jewelryImage.setImageDrawable(jewelry.getImage());
    }

    private void createRingShape(HomeRingShape shape) {
        Drawable presentImage = shape.getImage();
        Bitmap imageAfterColorChange = ImageColorChanger.changeImageColor(presentImage, material.getColor());
        shapeImage.setImageBitmap(imageAfterColorChange);
    }

    public void build(){
        createRingJewelry(jewelry);
        createRingShape(shape);
    }
}
