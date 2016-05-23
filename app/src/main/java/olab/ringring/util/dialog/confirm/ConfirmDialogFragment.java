package olab.ringring.util.dialog.confirm;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.function.Consumer;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import olab.ringring.R;
import olab.ringring.util.dialog.DialogBuilder;
import olab.ringring.util.dialog.yesorno.YesOrNoDialogBuilder;

/**
 * Created by 재화 on 2016-05-22.
 */
public class ConfirmDialogFragment extends DialogFragment {

    @Getter @Bind(R.id.icon_dialog_title) ImageView iconDialogTitle;
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
            dialogBuilder.getOnConfirmButtonClickListener().accept(view);
        });
        btnDialogConfirm.setTextColor(dialogBuilder.getConfirmButtonTextColor());
    }

}
