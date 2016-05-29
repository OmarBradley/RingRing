package olab.ringring.util.dialog.confirm;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.view.View;

import com.annimon.stream.function.Consumer;

import lombok.Data;
import lombok.experimental.Accessors;
import olab.ringring.util.dialog.DialogBuilder;
import olab.ringring.util.dialog.infomation.DialogInfoPool;

/**
 * Created by 재화 on 2016-05-22.
 */
@Accessors(chain=true)
@Data
public class ConfirmDialogBuilder implements DialogBuilder{
    private DialogInfoPool dialogInfo;
    private DialogInterface.OnClickListener onConfirmButtonClickListener;
    private @ColorRes int confirmButtonTextColor;

    @Override
    public ConfirmDialogFragment build() {
        ConfirmDialogFragment confirmDialog = new ConfirmDialogFragment();
        confirmDialog.setDialogBuilder(this);
        return confirmDialog;
    }
}
