package olab.ringring.main.nav;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
 * Created by 재화 on 2016-05-30.
 */
public class NavMainView extends LinearLayout {

    @Bind(R.id.image_nav_menu_icon) ImageView menuIconImage;
    @Bind(R.id.text_nav_menu_title) TextView menuTitleText;
    @Bind(R.id.image_nav_menu_arrow_icon) ImageView menuArrowIconImage;

    public NavMainView(Context context) {
        super(context);
        initView();
    }

    public NavMainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.nav_main_element, this);
        ButterKnife.bind(this, view);

    }

    public void setNavMenuIcon(@DrawableRes int menuIconRes) {
        menuIconImage.setImageDrawable(ContextCompat.getDrawable(getContext(),menuIconRes));
    }

    public void setNavMenuText(String menuText) {
        this.menuTitleText.setText(menuText);
    }

    public void setNavMenuArrowIcon(@DrawableRes Drawable menuArrowIcon) {
        menuArrowIconImage.setImageDrawable(menuArrowIcon);
    }

    public void setNavMenuAttributes(@DrawableRes Drawable menuIcon, String menuText) {
        menuIconImage.setImageDrawable(menuIcon);
        this.menuTitleText.setText(menuText);
    }

}