package olab.ringring.util.dialog.select;

import android.view.View;

import lombok.Data;

/**
 * Created by 재화 on 2016-05-31.
 */
@Data
public class SelectDialogItemData {
    private String itemText;
    private View.OnClickListener itemClickListener;
}
