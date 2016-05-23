package olab.ringring.util.dialog.confirm;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.view.View;

import com.annimon.stream.function.Consumer;

import lombok.Data;
import lombok.experimental.Accessors;
import olab.ringring.util.dialog.DialogBuilder;

/**
 * Created by 재화 on 2016-05-22.
 */
@Accessors(chain=true)
@Data
public class ConfirmDialogBuilder implements DialogBuilder{
    private Drawable dialogTitleIcon;
    private String dialogTitleText;
    private @ColorRes int dialogTitleTextColor;
    private String dialogMessage;
    private Consumer<View> onConfirmButtonClickListener;
    private @ColorRes int confirmButtonTextColor;

    @Override
    public ConfirmDialogBuilder build() {
        return this;
    }
}
