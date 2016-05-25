package olab.ringring.main.ringdesign.choicedialog.attributeview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;
import olab.ringring.main.ringdesign.util.ExpStringMaker;

/**
 * Created by 재화 on 2016-05-24.
 */
public class RingDetailAttributeView extends LinearLayout implements Checkable{

    @Bind(R.id.text_detail_ring_attribute) TextView textDetailRingAttribute;
    @Bind(R.id.image_detail_ring_attribute) ImageView imageDetailRingAttribute;
    @Bind(R.id.text_detail_ring_attribute_collecting_count) TextView textDetailRingAttributeCollectingCount;
    @Bind(R.id.image_check_detail_attribute) ImageView imageCheckDetailAttribute;
    private static final String DIVIDER = "/";
    @Setter @Getter public boolean isCheckedView = false;

    public RingDetailAttributeView(Context context) {
        super(context);
        initView();
    }

    public RingDetailAttributeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_ring_detail_attribute, this);
        ButterKnife.bind(this, view);
        initImageCheckDetailAttribute();
    }

    private void initImageCheckDetailAttribute() {
        imageCheckDetailAttribute.setVisibility(INVISIBLE);
    }

    public void setAttributeName(String text) {
        textDetailRingAttribute.setText(text);
    }

    public void setAttributeImage(Drawable imageDrawable) {
        imageDetailRingAttribute.setImageDrawable(imageDrawable);
    }

    public void setCollectingCountText(RingCollectCount ringCollectCount) {
        textDetailRingAttributeCollectingCount.setText(ExpStringMaker.getExpString(ringCollectCount.getCountNumber(), DIVIDER, RingCollectCount.MAX_COUNTING_NUMBER.getCountNumber()));
    }

    public boolean isCheckedImage() {
        return imageCheckDetailAttribute.getVisibility() == VISIBLE;
    }

    private void showCheckImage() {
        imageCheckDetailAttribute.setVisibility(VISIBLE);
    }

    private void hideCheckImage() {
        imageCheckDetailAttribute.setVisibility(INVISIBLE);
    }

    @Override
    public boolean isChecked() {
        return isCheckedView;
    }

    @Override
    public void setChecked(boolean checked) {
        if(isCheckedView != checked){
            isCheckedView = checked;
            executeActionWhenChecked();
        }
    }

    @Override
    public void toggle() {
        setChecked(!isCheckedView);
    }

    private void executeActionWhenChecked(){
        if(isChecked()){
            showCheckImage();
        } else {
            hideCheckImage();
        }
    }

}
