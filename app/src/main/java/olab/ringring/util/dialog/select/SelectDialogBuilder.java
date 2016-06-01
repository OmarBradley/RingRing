package olab.ringring.util.dialog.select;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by 재화 on 2016-05-31.
 */
@Accessors(chain = true)
@Data
public class SelectDialogBuilder {

    private String dialogTitle;
    private List<SelectDialogItemData> items;
    private boolean isItemViewCenterAlign;

    public SelectDialogFragment build() {
        SelectDialogFragment dialog = new SelectDialogFragment();
        dialog.setDialogBuilder(this);
        return dialog;
    }
}
