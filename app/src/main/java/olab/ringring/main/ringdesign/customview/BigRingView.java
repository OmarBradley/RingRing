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
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.main.ringdesign.ringattribute.jewelry.RingJewelry;
import olab.ringring.main.ringdesign.ringattribute.shape.RingShape;
import olab.ringring.main.ringdesign.ringfactory.RingFactory;
import olab.ringring.main.ringdesign.ringfactory.contretefactory.BigRingFactory;

/**
 * Created by 재화 on 2016-05-24.
 */
public class BigRingView extends LinearLayout {

    @Bind(R.id.image_big_ring_jewelry) ImageView imageBigRingJewelry;
    @Bind(R.id.image_big_ring_shape) ImageView imageBigRingShape;

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
        imageBigRingJewelry.setImageDrawable(jewelry.getRingJewelryImage());
    }

    public void setJewelryDrawable(Drawable jewelryDrawable){
        imageBigRingJewelry.setImageDrawable(jewelryDrawable);
    }

    public void setShapeImageBitmap(Bitmap shapeImageBitmap){
        imageBigRingShape.setImageBitmap(shapeImageBitmap);
    }

    public Drawable getShapeDrawable(){
        return imageBigRingShape.getDrawable();
    }

    public void setShapeDrawable(RingShape shape){
        imageBigRingShape.setImageDrawable(shape.getRingShapeBigImage());
    }

}
