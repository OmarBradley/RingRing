package olab.ringring.join;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-15.
 */
public class JoinTabIndicatorView extends LinearLayout {

    @Bind(R.id.image_tabIndicator) ImageView tabViewIcon;
    @Bind(R.id.text_tabIndicator) TextView tabViewText;

    public JoinTabIndicatorView(Context context, @StringRes int indicatorTextResource) {
        super(context);
        initView(indicatorTextResource);
    }

    public JoinTabIndicatorView(Context context, AttributeSet attrs, @StringRes int indicatorTextResource) {
        super(context, attrs);
        initView(indicatorTextResource);
    }

    private void initView(@StringRes int indicatorTextResource) {
        View joinTabIndicatorView = LayoutInflater.from(getContext()).inflate(R.layout.view_join_tab_indicator, this);
        ButterKnife.bind(joinTabIndicatorView, this);
        tabViewText.setText(indicatorTextResource);
    }

    public void setTabIndicatorViewWhenNotFocused(@ColorRes int indicatorTextColorResource) {
        tabViewText.setTextColor(ContextCompat.getColor(getContext(),indicatorTextColorResource));
        tabViewIcon.setVisibility(INVISIBLE);
    }

    public void setTabIndicatorViewWhenFocused(@ColorRes int indicatorTextColorResource, @DrawableRes int indicatorIconResource) {
        tabViewText.setTextColor(ContextCompat.getColor(getContext(),indicatorTextColorResource));
        tabViewIcon.setVisibility(VISIBLE);
        tabViewIcon.setImageDrawable(ContextCompat.getDrawable(getContext(),indicatorIconResource ));
    }
}
