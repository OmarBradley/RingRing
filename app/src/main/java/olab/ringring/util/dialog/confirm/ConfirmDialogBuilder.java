package olab.ringring.util.dialog.confirm;

import android.content.DialogInterface;

import lombok.Data;
import lombok.experimental.Accessors;
import olab.ringring.util.dialog.DialogBuilder;

/**
 * Created by 재화 on 2016-05-22.
 */
@Accessors(chain=true)
@Data
public class ConfirmDialogBuilder implements DialogBuilder {
    private ConfirmDialogData dialogInfo;
    private DialogInterface.OnClickListener onConfirmButtonClickListener;

    @Override
    public ConfirmDialogFragment build() {
        ConfirmDialogFragment confirmDialog = new ConfirmDialogFragment();
        confirmDialog.setDialogBuilder(this);
        return confirmDialog;
    }
}
