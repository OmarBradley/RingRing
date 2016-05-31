package olab.ringring.util.dialog.select;

import android.content.DialogInterface;
import android.view.View;

import com.annimon.stream.function.Consumer;

import lombok.Data;
import lombok.Getter;

/**
 * Created by 재화 on 2016-05-31.
 */
public class SelectDialogItemData {
    @Getter private String itemText;
    @Getter private DialogInterface.OnClickListener itemClickListener;

    public SelectDialogItemData(DialogInterface.OnClickListener itemClickListener, String itemText) {
        this.itemClickListener = itemClickListener;
        this.itemText = itemText;
    }
}
