package olab.ringring.notification.ring;

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
import olab.ringring.main.ringdesign.ringattribute.material.RingMaterial;
import olab.ringring.util.image.ImageHandler;

/**
 * Created by 재화 on 2016-06-11.
 */
public class NotiRingView extends LinearLayout {

    @Bind(R.id.image_noti_ring_jewelry) ImageView ringJewelry;
    @Bind(R.id.image_noti_ring_shape) ImageView ringShape;

    public NotiRingView(Context context) {
        super(context);
        initView();
    }

    public NotiRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        View notiRingView = LayoutInflater.from(getContext()).inflate(R.layout.view_noti_ring, this);
        ButterKnife.bind(notiRingView);
    }

    public void setRingJewelryImage(NotiRingJewelry jewelry){
        ringJewelry.setImageDrawable(jewelry.getImage());
    }

    public void setRingShapeImage(NotiRingShape shape, RingMaterial material){
        Drawable presentImage = shape.getImage();
        Bitmap imageAfterColorChange = ImageHandler.changeImageColor(presentImage, material.getColor());
        ringShape.setImageBitmap(imageAfterColorChange);
    }
}
