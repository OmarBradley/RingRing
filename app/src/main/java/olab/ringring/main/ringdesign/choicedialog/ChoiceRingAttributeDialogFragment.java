package olab.ringring.main.ringdesign.choicedialog;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Request;
import olab.ringring.R;
import olab.ringring.main.ringdesign.choicedialog.attributeview.RingDetailAttributeViewAdapter;
import olab.ringring.main.ringdesign.choicedialog.attributeview.RingDetailAttributeViewData;
import olab.ringring.main.ringdesign.ringattribute.RingAttributeListConstant;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.ring.RingProtocol;
import olab.ringring.network.response.ring.select.jewelry.SelectJewelryResult;
import olab.ringring.network.response.ring.select.material.SelectMaterialResult;
import olab.ringring.network.response.ring.select.shape.SelectShapeResult;

/**
 * Created by 재화 on 2016-05-24.
 */
public class ChoiceRingAttributeDialogFragment extends DialogFragment {

    @Bind(R.id.text_choice_dialog_title) TextView dialogTitleText;
    @Bind(R.id.btn_choice_dialog_cancel) ImageView dialogCancelBtn;
    @Bind(R.id.image_choice_dialog_title) CircleImageView dialogTitleImage;
    @Bind(R.id.list_attribute_view) GridView listAttributeView;
    @Bind(R.id.image_choice_dialog_background) ImageView dialogBackgroundImage;
    @Bind(R.id.btn_choice_dialog_check) Button dialogCheckBtn;

    private RingDetailAttributeViewAdapter adapter;
    @Setter private ChoiceRingAttributeDialogBuilder dialogBuilder;
    @Setter @Getter private Consumer<RingDetailAttributeViewData> onDataReceiveListener;
    private int checkedPosition;

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
        listAttributeView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void setAttributeInDialog(){
        dialogTitleText.setText(dialogBuilder.getTitle());
        dialogCancelBtn.setOnClickListener(view ->{
            dialogBuilder.getCancelButtonClickListener().accept(getDialog());
        });
        dialogCheckBtn.setOnClickListener(view ->{
            getSelectedPosition();
            showBackGroundImage();
            executeCallbackAction(checkedPosition);
        });
        dialogTitleImage.setImageDrawable(ContextCompat.getDrawable(getContext(), dialogBuilder.getTitleImageRes()));
        showCheckImageInAlreadyCheckedPosition();
        adapter.addAll(dialogBuilder.getAttributeItems());

    }


    private void showCheckImageInAlreadyCheckedPosition(){
        int alreadyCheckedPosition = 0;
        for(RingDetailAttributeViewData item : dialogBuilder.getAttributeItems()){
            if(item.getTag().equals(dialogBuilder.getCheckedItemTag())){
                listAttributeView.setItemChecked(alreadyCheckedPosition,true);
            } else {
                listAttributeView.setItemChecked(alreadyCheckedPosition, false);
            }
            alreadyCheckedPosition++;
        }
    }

    private void getSelectedPosition() {
        if (listAttributeView.getChoiceMode() == GridView.CHOICE_MODE_SINGLE) {
            checkedPosition = listAttributeView.getCheckedItemPosition();
            if (checkedPosition == -1) {
                checkedPosition = adapter.getInitCheckedItemPosition();
            }
        }
    }

    private void showBackGroundImage(){
        dialogBackgroundImage.setOnClickListener(view->{});
        dialogBackgroundImage.setVisibility(View.VISIBLE);
    }

    private void executeCallbackAction(int position){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(()->{
            sendSelectionData();
            dialogBuilder.getCheckButtonClickListener().accept(getDialog(), adapter.getItem(position));
        },SHOW_BACKGROUND_IMAGE_TIME);
    }

    private void sendSelectionData(){
        if (dialogBuilder.getTitle().equals(RingAttributeListConstant.JEWELRY.getChoiceDialogTitle())) {
            sendNetworkRequest(RingProtocol.Select.makeSelectJewelryRequest(getActivity(), adapter.getItem(checkedPosition).getTag()), SelectJewelryResult.class);
        } else if(dialogBuilder.getTitle().equals(RingAttributeListConstant.MATERIAL.getChoiceDialogTitle())){
            sendNetworkRequest(RingProtocol.Select.makeSelectMaterialRequest(getActivity(), adapter.getItem(checkedPosition).getTag()), SelectMaterialResult.class);
        } else {
            sendNetworkRequest(RingProtocol.Select.makeSelectShapeRequest(getActivity(), adapter.getItem(checkedPosition).getTag()), SelectShapeResult.class);
        }
    }

    private void sendNetworkRequest(Request selectRequest, Class selectResultClass) {
        NetworkManager.getInstance().getResult(selectRequest, selectResultClass,
                (request, result) -> {
                    Log.e("result", result.toString());
                }, (request, integer, throwable) -> {
                    Log.e("result error", integer + "");
                });
    }
}
