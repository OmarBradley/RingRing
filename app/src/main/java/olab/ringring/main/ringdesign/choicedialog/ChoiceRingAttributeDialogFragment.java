package olab.ringring.main.ringdesign.choicedialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.function.Consumer;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;
import olab.ringring.main.ringdesign.choicedialog.attributeview.RingDetailAttributeViewAdapter;
import olab.ringring.main.ringdesign.choicedialog.attributeview.RingDetailAttributeViewData;

/**
 * Created by 재화 on 2016-05-24.
 */
public class ChoiceRingAttributeDialogFragment extends DialogFragment {

    @Bind(R.id.text_choice_dialog_title) TextView dialogTitleText;
    @Bind(R.id.btn_choice_dialog_check) ImageView dialogCheckBtn;
    @Bind(R.id.image_choice_dialog_title) CircleImageView dialogTitleImage;
    @Bind(R.id.list_attribute_view) GridView listAttributeView;
    RingDetailAttributeViewAdapter adapter;
    @Setter private ChoiceRingAttributeDialogBuilder dialogBuilder;
    @Setter @Getter Consumer<RingDetailAttributeViewData> onDataReceiveListener;
    @Bind(R.id.image_choice_dialog_background) ImageView dialogBackgroundImage;

    private final static int DIALOG_ITEM_INDEX = 0;
    private final static int SHOW_BACKGROUND_IMAGE_TIME = 2000;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.RingRingDialogTheme);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View choiceRingAttributeDialogView = inflater.inflate(R.layout.dialog_choice_ring_attribute, container, false);
        ButterKnife.bind(this, choiceRingAttributeDialogView);
        initChoiceDialogBackground();
        initListAttributeView();
        setAttributeInDialog();
        return choiceRingAttributeDialogView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_choice_ring_attribute_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_choice_ring_attribute_height);
        getDialog().getWindow().setLayout(width, height);
    }

    private void initChoiceDialogBackground(){
        dialogBackgroundImage.setVisibility(View.GONE);
    }


    private void initListAttributeView(){
        adapter = new RingDetailAttributeViewAdapter();
        listAttributeView.setAdapter(adapter);
    }

    private void setAttributeInDialog(){
        dialogTitleText.setText(dialogBuilder.getTitle());
        dialogCheckBtn.setOnClickListener(view ->{
            int position = getSelectedPosition();
            showBackGroundImage();
            executeCallbackAction(position);
        });
        dialogTitleImage.setImageDrawable(ContextCompat.getDrawable(getContext(), dialogBuilder.getTitleImageRes()));
        adapter.addAll(dialogBuilder.getAttributeItems());
    }

    private int getSelectedPosition(){
        int position = 0;
        if(listAttributeView.getChoiceMode() == GridView.CHOICE_MODE_SINGLE){
            position = listAttributeView.getCheckedItemPosition();
        }
        return position;
    }

    private void showBackGroundImage(){
        dialogBackgroundImage.setVisibility(View.VISIBLE);
    }

    private void executeCallbackAction(int position){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(()->{
            dialogBuilder.getCheckClickListener().accept(getDialog(), adapter.getItem(position));
        },SHOW_BACKGROUND_IMAGE_TIME);
    }
}
