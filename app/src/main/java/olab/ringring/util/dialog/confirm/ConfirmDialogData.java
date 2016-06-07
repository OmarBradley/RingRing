package olab.ringring.util.dialog.confirm;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by 재화 on 2016-06-07.
 */
@Data
@Accessors(chain = true)
public class ConfirmDialogData {
    private String dialogTitle;
    private Drawable dialogTitleIcon;
    private String dialogMessage;
    @ColorRes private int dialogTextColor;
    private String dialogConfirmButtonMessage;


}
