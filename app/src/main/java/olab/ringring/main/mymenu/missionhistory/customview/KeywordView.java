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
import de.hdodenhof.circleimageview.CircleImageView;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-26.
 */
public class KeywordView extends LinearLayout {

    @Bind(R.id.image_keyword) ImageView keywordImage;
    @Bind(R.id.text_keyword_success_count) TextView keywordSuccessCountText;
    @Bind(R.id.image_keyword_success_count_background) CircleImageView countTextBgImage;

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

    public void setKeywordImage(@DrawableRes int drawableRes) {
        keywordImage.setImageDrawable(ContextCompat.getDrawable(getContext(), drawableRes));
    }

    public void setKeywordSuccessCountText(String countText) {
        keywordSuccessCountText.setText(countText);
        showKeyword("" + countText);
    }

    // TODO: 2016-06-08 성공 카운트 숫자 type에 따라 바꿔주기,,,,
    private void showKeyword(String countText) {
        if (countText.equals("0")) {
            hideCountTextBackgroundImage();
            setKeywordImage(R.drawable.keyword_default_image);
        } else {
            showCountTextBackgroundImage();
        }
    }


    private void hideCountTextBackgroundImage() {
        countTextBgImage.setVisibility(INVISIBLE);
        keywordSuccessCountText.setVisibility(INVISIBLE);
    }

    private void showCountTextBackgroundImage() {
        countTextBgImage.setVisibility(VISIBLE);
        keywordSuccessCountText.setVisibility(VISIBLE);
    }

}
