package olab.ringring.util.dialog.confirm;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-22.
 */
public class ConfirmDialogFragment extends DialogFragment {

    private final static int DIALOG_ITEM_INDEX = 0;

    @Getter @Bind(R.id.image_dialog_title) ImageView iconDialogTitle;
    @Getter @Bind(R.id.text_dialog_title) TextView textDialogTitle;
    @Getter @Bind(R.id.text_dialog_message) TextView textDialogMessage;
    @Getter @Bind(R.id.btn_dialog_confirm) Button btnDialogConfirm;
    @Setter private ConfirmDialogBuilder dialogBuilder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.RingRingDialogTheme);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View confirmDialogView = inflater.inflate(R.layout.dialog_confirm, container, false);
        ButterKnife.bind(this, confirmDialogView);
        setAttributeInDialog();
        return confirmDialogView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }

    private void setAttributeInDialog() {
        iconDialogTitle.setImageDrawable(dialogBuilder.getDialogTitleIcon());
        textDialogTitle.setText(dialogBuilder.getDialogTitleText());
        textDialogTitle.setTextColor(dialogBuilder.getDialogTitleTextColor());
        textDialogMessage.setText(dialogBuilder.getDialogMessage());
        btnDialogConfirm.setOnClickListener(view -> {
            dialogBuilder.getOnConfirmButtonClickListener().onClick(getDialog(),DIALOG_ITEM_INDEX);
        });
        btnDialogConfirm.setTextColor(dialogBuilder.getConfirmButtonTextColor());
    }

}
