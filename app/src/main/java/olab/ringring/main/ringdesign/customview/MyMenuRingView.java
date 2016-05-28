package olab.ringring.main.ringdesign.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.ringdesign.levelpolicy.RingLevel;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.main.ringdesign.util.StringMaker;
import olab.ringring.util.colorchanger.ImageColorChanger;

/**
 * Created by 재화 on 2016-05-26.
 */
public class MyMenuRingView extends LinearLayout {

    @Bind(R.id.image_my_menu_ring_jewelry) ImageView jewelryImage;
    @Bind(R.id.image_my_menu_ring_shape) ImageView shapeImage;
    @Bind(R.id.image_my_menu_ring_collecting_count) TextView collectingCountText;
    @Bind(R.id.progress_bar_my_menu_ring_collecting_count) CircularProgressBar collectingCountProgressBar;

    public MyMenuRingView(Context context) {
        super(context);
        initView();
    }

    public MyMenuRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_my_menu_ring, this);
        ButterKnife.bind(this, view);
    }

    public void setJewelryDrawable(RingJewelry jewelry) {
        jewelryImage.setImageDrawable(jewelry.getChoiceImage());
    }

    public void setJewelryDrawable(Drawable jewelryDrawable) {
        jewelryImage.setImageDrawable(jewelryDrawable);
    }

    public void setShapeImageBitmap(Bitmap shapeImageBitmap) {
        shapeImage.setImageBitmap(shapeImageBitmap);
    }

    public void setMaterialColor(RingMaterial material) {
        Drawable shapeDrawable = shapeImage.getDrawable();
        shapeImage.setImageBitmap(ImageColorChanger.changeImageColor(shapeDrawable, material.getColor()));
    }

    public Drawable getShapeDrawable() {
        return shapeImage.getDrawable();
    }

    public void setShapeDrawable(RingShape shape) {
        shapeImage.setImageDrawable(shape.getChoiceImage());
    }

    public void setCollectingCountText(RingLevel level) {
        collectingCountText.setText(StringMaker.getExpString(level.getLevelNumber(), StringMaker.DIVIDER, RingLevel.MAX_LEVEL.getLevelNumber()));
    }

    public void setCollectingCountProgressBar(RingLevel level){
        collectingCountProgressBar.setProgress(level.getProgressLevelNumber());
    }
}
