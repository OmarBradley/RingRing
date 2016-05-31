package olab.ringring.util.dialog.select;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectDialogFragment extends DialogFragment {

    @Bind(R.id.text_select_dialog_title) TextView dialogTitle;
    @Bind(R.id.list_select_dialog_items) RecyclerView dialogItems;


    public SelectDialogFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.RingRingDialogTheme);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View selectDialogView = inflater.inflate(R.layout.dialog_select, container, false);
        ButterKnife.bind(this, selectDialogView);
        return selectDialogView;
    }

}
