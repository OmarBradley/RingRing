package olab.ringring.main.nav;

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

/**
 * Created by 재화 on 2016-05-23.
 */
public class MainNavMenuView extends LinearLayout {

    @Bind(R.id.image_nav_menu_icon) ImageView imageNavMenuIcon;
    @Bind(R.id.text_nav_menu_text) TextView textNavMenuText;
    @Bind(R.id.image_nav_menu_arrow_icon) ImageView imageNavMenuArrowIcon;
    @Getter @Setter private Consumer<View> viewOnClickListener;

    public MainNavMenuView(Context context) {
        super(context);
        initView();
    }

    public MainNavMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.nav_menu_element, this);
        ButterKnife.bind(this, view);
        setOnClickListenerInView(view);
    }

    private void setOnClickListenerInView(View navMenuElementView) {
        navMenuElementView.setOnClickListener(view -> {
            viewOnClickListener.accept(view);
        });
    }

    public void setNavMenuIcon(@DrawableRes Drawable menuIcon) {
        imageNavMenuIcon.setImageDrawable(menuIcon);
    }

    public void setNavMenuText(String menuText) {
        textNavMenuText.setText(menuText);
    }

    public void setNavMenuArrowIcon(@DrawableRes Drawable menuArrowIcon) {
        imageNavMenuArrowIcon.setImageDrawable(menuArrowIcon);
    }

    public void setNavMenuAttributes(@DrawableRes Drawable menuIcon, String menuText, @DrawableRes Drawable menuArrowIcon) {
        imageNavMenuIcon.setImageDrawable(menuIcon);
        textNavMenuText.setText(menuText);
        imageNavMenuArrowIcon.setImageDrawable(menuArrowIcon);
    }

}
