package olab.ringring.main.ringdesign.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.main.ringdesign.levelpolicy.RingLevel;

/**
 * Created by 재화 on 2016-05-23.
 */
public class RingLevelView extends LinearLayout{

    @Bind(R.id.progress_bar_ring_level_gauge) ProgressBar progressBarRingLevelGauge;
    @Bind(R.id.text_ring_level_exp) TextView textRingLevelExp;
    private static final int MAX_LEVEL_EXP = RingLevel.EIGHTEEN.getLevelNumber();
    @Setter private RingLevel presentRingLevel = RingLevel.ONE;

    public RingLevelView(Context context) {
        super(context);
        initView();
        setMaxRingLevelGauge(RingLevel.EIGHTEEN);
        setPresentRingLevelGauge(RingLevel.THREE);
    }

    public RingLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        setMaxRingLevelGauge(RingLevel.EIGHTEEN);
        setPresentRingLevelGauge(RingLevel.THREE);
        buildRingLevelExpText();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_ring_level, this);
        ButterKnife.bind(this, view);
    }

    private void buildRingLevelExpText() {
        textRingLevelExp.setText(presentRingLevel.getLevelNumber() + " / " + MAX_LEVEL_EXP);
    }

    public void setMaxRingLevelGauge(RingLevel maxLevel) {
        progressBarRingLevelGauge.setMax(maxLevel.getLevelNumber());
    }

    public void setPresentRingLevelGauge(RingLevel presentLevel) {
        progressBarRingLevelGauge.setProgress(presentLevel.getLevelNumber());
    }
}
