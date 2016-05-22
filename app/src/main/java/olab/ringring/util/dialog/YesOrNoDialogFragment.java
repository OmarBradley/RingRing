package olab.ringring.util.dialog;

import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.function.Consumer;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Setter;
import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-21.
 */
public class YesOrNoDialogFragment extends DialogFragment {

    @Bind(R.id.icon_dialog_title) ImageView iconDialogTitle;
    @Bind(R.id.text_dialog_title) TextView textDialogTitle;
    @Bind(R.id.text_dialog_message) TextView textDialogMessage;
    @Bind(R.id.btn_dialog_positive) Button btnDialogPositive;
    @Bind(R.id.btn_dialog_negative) Button btnDialogNegative;
    @Setter private YesOrNoDialogBuilder dialogBuilder;

    public YesOrNoDialogFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.YesOrNoDialogTheme);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View yesOrNoDialogView = inflater.inflate(R.layout.dialog_yes_or_no, container, false);
        ButterKnife.bind(this, yesOrNoDialogView);
        setAttributeInDialog();
        return yesOrNoDialogView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }

    private void setAttributeInDialog(){
        iconDialogTitle.setImageDrawable(dialogBuilder.getDialogTitleIcon());
        textDialogTitle.setText(dialogBuilder.getDialogTitleText());
        textDialogMessage.setText(dialogBuilder.getDialogMessage());
        btnDialogPositive.setOnClickListener(view -> {
            dialogBuilder.getOnPositiveButtonClickListener().accept(view);
        });
        btnDialogNegative.setOnClickListener(view -> {
            dialogBuilder.getOnNegativeButtonClickListener().accept(view);
        });
    }
}
