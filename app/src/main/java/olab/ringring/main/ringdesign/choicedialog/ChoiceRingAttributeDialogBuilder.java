package olab.ringring.main.ringdesign.choicedialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.DialogFragment;

import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.Consumer;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import olab.ringring.main.ringdesign.choicedialog.attributeview.RingDetailAttributeViewData;
import olab.ringring.util.dialog.DialogBuilder;

/**
 * Created by 재화 on 2016-05-25.
 */
@Accessors(chain=true)
@Data
public class ChoiceRingAttributeDialogBuilder implements DialogBuilder {

    private String title;
    private BiConsumer<Dialog, RingDetailAttributeViewData> checkClickListener;
    private @DrawableRes int titleImageRes;
    private List<RingDetailAttributeViewData> attributeItems;

    @Override
    public ChoiceRingAttributeDialogFragment build() {
        ChoiceRingAttributeDialogFragment dialog = new ChoiceRingAttributeDialogFragment();
        dialog.setDialogBuilder(this);
        return dialog;
    }
}
