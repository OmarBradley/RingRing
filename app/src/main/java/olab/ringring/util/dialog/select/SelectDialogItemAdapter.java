package olab.ringring.util.dialog.select;

import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-31.
 */
public class SelectDialogItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<SelectDialogItemData> items = new ArrayList<>();
    DialogFragment dialog;
    @Setter boolean isItemVIewCenterAlign = false;

    public SelectDialogItemAdapter(DialogFragment dialog){
        this.dialog = dialog;
    }

    public void add(SelectDialogItemData item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<SelectDialogItemData> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SelectDialogItemViewHolder(inflater.inflate(R.layout.view_select_dialog_item, parent, false), dialog);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SelectDialogItemViewHolder) holder).setDialogItemViewAttribute(items.get(position));
        if(isItemVIewCenterAlign){
            ((SelectDialogItemViewHolder) holder).setItemViewCenterAlign();
        }
    }
}
