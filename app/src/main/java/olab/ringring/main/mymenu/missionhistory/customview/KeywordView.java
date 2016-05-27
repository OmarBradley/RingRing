package olab.ringring.main.mymenu.missionhistory.customview;

import android.content.Context;
import android.support.annotation.DrawableRes;
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
 * Created by 재화 on 2016-05-26.
 */
public class KeywordView extends LinearLayout {

    @Bind(R.id.image_keyword) ImageView keywordImage;
    @Bind(R.id.text_keyword_success_count) TextView keywordSuccessCountText;

    public KeywordView(Context context) {
        super(context);
        initView();
    }

    public KeywordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_keyword, this);
        ButterKnife.bind(this, view);
    }

    public void setKeywordImage(@DrawableRes int drawableRes){
        keywordImage.setImageDrawable(ContextCompat.getDrawable(getContext(), drawableRes));
    }

    public void setKeywordSuccessCountText(String countText){
        keywordSuccessCountText.setText(countText);
    }

}
