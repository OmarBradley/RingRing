package olab.ringring.main.ringdesign.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.annimon.stream.function.Consumer;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;
import olab.ringring.main.ringdesign.util.StringMaker;

/**
 * Created by 재화 on 2016-05-24.
 */
public class SetRingAttributeView extends LinearLayout {

    @Bind(R.id.text_ring_attribute) TextView textRingAttribute;
    @Bind(R.id.image_ring_attribute) ImageView imageRingAttribute;
    @Bind(R.id.text_ring_level_exp) TextView textRingLevelExp;

    private static final String DIVIDER = "/";

    public SetRingAttributeView(Context context) {
        super(context);
        initView();
    }

    public SetRingAttributeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_set_ring_attribute, this);
        ButterKnife.bind(this, view);
    }

    public void setRingAttributeText(String text) {
        textRingAttribute.setText(text);
    }

    public void setRingAttributeImage(Drawable RingAttributeImage) {
        imageRingAttribute.setImageDrawable(RingAttributeImage);
    }

    public void setRingLevelExpText(RingCollectCount ringCollectCount) {
        textRingLevelExp.setText(StringMaker.getExpString(ringCollectCount.getCountNumber(),DIVIDER,  RingCollectCount.MAX_COUNTING_NUMBER.getCountNumber()));
    }

    public void setOnClickListenerInView(Consumer<View> onClickListener) {
        imageRingAttribute.setOnClickListener(view -> {
            onClickListener.accept(view);
        });
    }

}
