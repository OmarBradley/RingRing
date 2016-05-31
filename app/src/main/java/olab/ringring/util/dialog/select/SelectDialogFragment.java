package olab.ringring.util.dialog.select;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Setter;
import olab.ringring.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectDialogFragment extends DialogFragment {

    @Bind(R.id.text_select_dialog_title) TextView dialogTitle;
    @Bind(R.id.list_select_dialog_items) RecyclerView dialogItems;
    @Bind(R.id.text_select_dialog_cancel) TextView dialogCancel;

    @Setter SelectDialogBuilder dialogBuilder;
    SelectDialogItemAdapter adapter;
    private static final int DIVIDER_WIDTH = 1;

    public SelectDialogFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.RingRingDialogTheme);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.select_dialog_width);
        getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View selectDialogView = inflater.inflate(R.layout.dialog_select, container, false);
        ButterKnife.bind(this, selectDialogView);
        setOnClickListenerInDialogCancel();
        setDialogItemsListView();
        setDialogAttribute();
        return selectDialogView;
    }

    private void setOnClickListenerInDialogCancel(){
        dialogCancel.setOnClickListener(view ->{
            getDialog().dismiss();
        });
    }

    private void setDialogItemsListView() {
        adapter = new SelectDialogItemAdapter(this);
        dialogItems.setAdapter(adapter);
        dialogItems.setLayoutManager(new LinearLayoutManager(getContext()));
        dialogItems.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(ContextCompat.getColor(getActivity(), R.color.colorDialogGray))
                .size(DIVIDER_WIDTH)
                .build());
    }

    private void setDialogAttribute(){
        dialogTitle.setText(dialogBuilder.getDialogTitle());
        adapter.addAll(dialogBuilder.getItems());
    }
}
