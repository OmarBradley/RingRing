package olab.ringring.main.ringdesign.ringattribute.dialog.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.annimon.stream.function.Consumer;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;

/**
 * Created by 재화 on 2016-05-24.
 */
public class RingDetailAttributeView extends LinearLayout {

    @Bind(R.id.text_detail_ring_attribute) TextView textDetailRingAttribute;
    @Bind(R.id.image_detail_ring_attribute) ImageView imageDetailRingAttribute;
    @Bind(R.id.text_detail_ring_attribute_collecting_count) TextView textDetailRingAttributeCollectingCount;
    @Bind(R.id.image_check_detail_attribute) ImageView imageCheckDetailAttribute;
    private static final String DIVIDER = "/";
    @Setter @Getter private Consumer<View> onClickListener;

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
        initImageDetailRingAttributeOnClickListener();
    }

    private void initImageCheckDetailAttribute() {
        imageCheckDetailAttribute.setVisibility(GONE);
    }

    private void initImageDetailRingAttributeOnClickListener() {
        imageCheckDetailAttribute.setOnClickListener(view -> {
            onClickListener.accept(view);
        });
    }

    public void setDetailRingAttributeText(String text) {
        textDetailRingAttribute.setText(text);
    }

    public void setDetailRingAttributeImage(Drawable imageDrawable) {
        imageDetailRingAttribute.setImageDrawable(imageDrawable);
    }

    public void setDetailRingAttributeCollectingCountText(RingCollectCount ringCollectCount) {
        textDetailRingAttributeCollectingCount.setText(ringCollectCount.getCountNumber() + DIVIDER + RingCollectCount.MAX_COUNTING_NUMBER.getCountNumber());
    }

    public boolean isCheckedImage() {
        return imageCheckDetailAttribute.getVisibility() == VISIBLE;
    }

    public void showCheckImage() {
        imageCheckDetailAttribute.setVisibility(VISIBLE);
    }

    public void hideCheckImage() {
        imageCheckDetailAttribute.setVisibility(GONE);
    }
}
