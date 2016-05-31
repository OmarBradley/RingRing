package olab.ringring.util.dialog.select;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 재화 on 2016-05-31.
 */
public class SelectDialogItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<SelectDialogItemData> items = new ArrayList<>();

    public void add(SelectDialogItemData item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<SelectDialogItemData> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
