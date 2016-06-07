package olab.ringring.main.mymenu.dday;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.samsistemas.calendarview.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.changecreatedate.SuccessChangeCreateDate;
import olab.ringring.network.response.mymenu.showcreatedate.SuccessShowCreateDate;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;
import olab.ringring.util.dialog.confirm.ConfirmDialogBuilder;
import olab.ringring.util.dialog.confirm.ConfirmDialogData;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;
import olab.ringring.util.dialog.confirm.ConfirmDialogInfoPool;


public class DDaySettingActivity extends AppCompatActivity implements ActionBarElement {

    @Bind(R.id.calendar_set_d_day) CalendarView dDaySettingCalendar;
    @Bind(R.id.text_today) TextView todayText;
    private long selectDate;
    private int coupleIndex;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_day_setting);
        ButterKnife.bind(this);
        getTodayText();
        initDDaySettingCalendar();
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.drawable.actionbar_home_as_up_image)));
    }

    private void getTodayText(){
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeShowCreateDateRequest(this), SuccessShowCreateDate.class, (request, result) -> {
            coupleIndex = result.getCoupleIndex();
            todayText.setText(dateFormat.format(result.getCoupleCreateDate()));
        }, (request, errorCode, throwable) -> {
            Toast.makeText(this, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initDDaySettingCalendar() {
        dDaySettingCalendar.setIsOverflowDateVisible(false);
        dDaySettingCalendar.refreshCalendar(Calendar.getInstance(Locale.getDefault()));
        dDaySettingCalendar.setOnDateSelectedListener(date -> {
            selectDate = date.getTime();
            todayText.setText(dateFormat.format(date));
        });
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, MyMenuActivity.class);
        pageMover.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pageMover);
        finish();
    }

    @Override
    public void accept(ActionbarVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_d_day_setting, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_d_day_setting:
                makeDialog();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeDialog(){
        ConfirmDialogFragment setDDayDialog = new ConfirmDialogBuilder()
                .setDialogInfo(new ConfirmDialogData()
                        .setDialogMessage("처음 사귄날이\n" + dateFormat.format(selectDate)+" 맞아?")
                        .setDialogTitle("사귄날 설정")
                        .setDialogTextColor(ContextCompat.getColor(this,R.color.colorDialogCheck ))
                        .setDialogConfirmButtonMessage("좋아")
                        .setDialogTitleIcon(ContextCompat.getDrawable(this,R.drawable.dialog_check_image ))
                ).setOnConfirmButtonClickListener((dialog, dialogItemIndex) -> {
                    dialog.dismiss();
                    sendNetworkRequest();
                }).build();
        setDDayDialog.show(getSupportFragmentManager(), "select create date dialog");
    }
    private void sendNetworkRequest(){
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeChangeCreateDate(this, selectDate, coupleIndex), SuccessChangeCreateDate.class , (request, result) -> {
            todayText.setText(dateFormat.format(result.getCoupleCreatedDate()));
        }, (request, errorCode, throwable) -> {
            Toast.makeText(this, errorCode.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

}
