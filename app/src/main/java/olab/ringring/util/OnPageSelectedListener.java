package olab.ringring.util;

import android.support.v4.view.ViewPager;

/**
 * Created by 재화 on 2016-05-15.
 */
public interface OnPageSelectedListener extends ViewPager.OnPageChangeListener {
    @Override
    public void onPageSelected(int position);
}
