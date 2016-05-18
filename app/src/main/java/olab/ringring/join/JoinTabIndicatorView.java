package olab.ringring.join;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-15.
 */
public class JoinTabIndicatorView extends LinearLayout {

    @Bind(R.id.icon_tabIndicator) ImageView tabIndicatorIcon;
    @Bind(R.id.text_tabIndicator) TextView tabIndicatorText;

    public JoinTabIndicatorView(Context context, @StringRes int indicatorTextResource) {
        super(context);
        initView(indicatorTextResource);
    }

    public JoinTabIndicatorView(Context context, AttributeSet attrs, @StringRes int indicatorTextResource) {
        super(context, attrs);
        initView(indicatorTextResource);
    }

    private void initView(@StringRes int indicatorTextResource) {
        View JoinTabIndicatorView = LayoutInflater.from(getContext()).inflate(R.layout.view_join_tab_indicator, this);
        ButterKnife.bind(JoinTabIndicatorView, this);
        tabIndicatorText.setText(indicatorTextResource);
    }

    public void setTabIndicatorViewWhenNotFocused(@ColorRes int indicatorTextColorResource) {
        tabIndicatorText.setTextColor(getResources().getColor(indicatorTextColorResource));
        tabIndicatorIcon.setVisibility(INVISIBLE);
    }

    public void setTabIndicatorViewWhenFocused(@ColorRes int indicatorTextColorResource, @DrawableRes int indicatorIconResource) {
        tabIndicatorText.setTextColor(getResources().getColor(indicatorTextColorResource));
        tabIndicatorIcon.setVisibility(VISIBLE);
        tabIndicatorIcon.setImageResource(indicatorIconResource);
    }

}
