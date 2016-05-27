package olab.ringring.main.ringdesign.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;

/**
 * Created by 재화 on 2016-05-24.
 */
public class BigRingView extends LinearLayout {

    @Bind(R.id.image_big_ring_jewelry) ImageView jewelryImage;
    @Bind(R.id.image_big_ring_shape) ImageView shapeImage;

    public BigRingView(Context context) {
        super(context);
        initView();
    }

    public BigRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_big_ring, this);
        ButterKnife.bind(this, view);
    }

    public void setJewelryDrawable(RingJewelry jewelry){
        jewelryImage.setImageDrawable(jewelry.getChoiceImage());
    }

    public void setJewelryDrawable(Drawable jewelryDrawable){
        jewelryImage.setImageDrawable(jewelryDrawable);
    }

    public void setShapeImageBitmap(Bitmap shapeImageBitmap){
        shapeImage.setImageBitmap(shapeImageBitmap);
    }

    public Drawable getShapeDrawable(){
        return shapeImage.getDrawable();
    }

    public void setShapeDrawable(RingShape shape){
        shapeImage.setImageDrawable(shape.getBigImage());
    }

}
