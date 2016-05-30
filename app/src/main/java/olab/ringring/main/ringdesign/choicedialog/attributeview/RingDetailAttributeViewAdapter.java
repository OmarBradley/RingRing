package olab.ringring.main.ringdesign.choicedialog.attributeview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import olab.ringring.main.ringdesign.levelpolicy.RingCollectCount;

/**
 * Created by 재화 on 2016-05-25.
 */
public class RingDetailAttributeViewAdapter extends BaseAdapter {

    List<RingDetailAttributeViewData> items = new ArrayList<>();
    @Getter @Setter private int initCheckedItemPosition;

    public void add(RingDetailAttributeViewData item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<RingDetailAttributeViewData> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public RingDetailAttributeViewData getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RingDetailAttributeView itemView;
        if(convertView == null){
            itemView = new RingDetailAttributeView(parent.getContext());
        } else {
            itemView = (RingDetailAttributeView) convertView;
        }
        itemView.setAttributeName(items.get(position).getAttributeName());
        itemView.setAttributeImage(items.get(position).getAttributeImage());
        itemView.setCollectingCountText(items.get(position).getCollectCount());
        setItemViewCheckable(itemView, items.get(position).getCollectCount());
        return itemView;
    }

    private void setItemViewCheckable(RingDetailAttributeView itemView, RingCollectCount count){
        if(count == RingCollectCount.MAX_COUNTING_NUMBER){
            itemView.setClickable(false);
        } else {
            itemView.setClickable(true);
        }
    }

}
