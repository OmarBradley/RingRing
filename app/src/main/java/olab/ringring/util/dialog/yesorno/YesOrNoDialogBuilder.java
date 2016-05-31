package olab.ringring.util.dialog.yesorno;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.annimon.stream.function.Consumer;

import lombok.Data;
import lombok.experimental.Accessors;
import olab.ringring.util.dialog.DialogBuilder;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;

/**
 * Created by 재화 on 2016-05-22.
 */

@Accessors(chain=true)
@Data
public class YesOrNoDialogBuilder implements DialogBuilder{

    private YesOrNoDialogInfoPool dialogInfo;
    private DialogInterface.OnClickListener onPositiveButtonClickListener;
    private DialogInterface.OnClickListener onNegativeButtonClickListener;

    @Override
    public YesOrNoDialogFragment build() {
        YesOrNoDialogFragment yesOrNoDialog = new YesOrNoDialogFragment();
        yesOrNoDialog.setDialogBuilder(this);
        return yesOrNoDialog;
    }
}
