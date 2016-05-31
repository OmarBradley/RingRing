package olab.ringring.util.dialog.select;

import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-31.
 */
public class SelectDialogItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.text_select_dialog_item) TextView itemText;
    private View itemView;
    private DialogFragment dialog;

    public SelectDialogItemViewHolder(View itemView, DialogFragment dialog) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
        this.dialog = dialog;
    }

    public void setDialogItemViewAttribute(SelectDialogItemData data){
        itemText.setText(data.getItemText());
        itemView.setOnClickListener(view -> {
            data.getItemClickListener().onClick(dialog.getDialog() ,2);
        });
    }
}
