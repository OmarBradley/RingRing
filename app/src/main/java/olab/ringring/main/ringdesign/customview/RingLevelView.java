package olab.ringring.main.ringdesign.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daasuu.ahp.AnimateHorizontalProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.main.ringdesign.levelpolicy.RingLevel;
import olab.ringring.main.ringdesign.util.StringMaker;

/**
 * Created by 재화 on 2016-05-23.
 */
public class RingLevelView extends LinearLayout{

    @Bind(R.id.progress_bar_ring_level_gauge) AnimateHorizontalProgressBar progressBarRingLevelGauge;
    @Bind(R.id.text_ring_level_exp) TextView textRingLevelExp;
    private static final RingLevel MAX_LEVEL_EXP = RingLevel.EIGHTEEN;
    @Getter private RingLevel presentRingLevel = RingLevel.FIVE;
    private static final String DIVIDER = " / ";

    public RingLevelView(Context context) {
        super(context);
        initView();
        initViewAttribute();
    }

    public RingLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initViewAttribute();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_ring_level, this);
        ButterKnife.bind(this, view);
    }

    private void initViewAttribute(){
        progressBarRingLevelGauge.setMax(MAX_LEVEL_EXP.getLevelNumber());
        textRingLevelExp.setText(StringMaker.getExpString(presentRingLevel.getLevelNumber(),DIVIDER , MAX_LEVEL_EXP.getLevelNumber()));
    }

    public void setPresentRingLevel(RingLevel ringLevel){
        presentRingLevel = ringLevel;
        progressBarRingLevelGauge.setProgress(presentRingLevel.getLevelNumber());
        textRingLevelExp.setText(StringMaker.getExpString(presentRingLevel.getLevelNumber(),DIVIDER , MAX_LEVEL_EXP.getLevelNumber()));
    }

}
