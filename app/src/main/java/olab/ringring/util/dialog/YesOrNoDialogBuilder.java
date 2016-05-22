package olab.ringring.util.dialog;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.annimon.stream.function.Consumer;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by 재화 on 2016-05-22.
 */

@Accessors(chain=true)
@Data
public class YesOrNoDialogBuilder {

    private Drawable dialogTitleIcon;
    private String dialogTitleText;
    private String dialogMessage;
    private Consumer<View> onPositiveButtonClickListener;
    private Consumer<View> onNegativeButtonClickListener;

    public YesOrNoDialogBuilder build() {
        return this;
    }
}
