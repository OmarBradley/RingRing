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
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;


public class DDaySettingActivity extends AppCompatActivity implements ActionBarElement {

    @Bind(R.id.calendar_set_d_day) CalendarView dDaySettingCalendar;
    @Bind(R.id.text_today) TextView todayText;
    private long selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_day_setting);
        ButterKnife.bind(this);
        initDDaySettingCalendar();
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.drawable.actionbar_home_as_up_image)));
    }

    private void initDDaySettingCalendar() {
        dDaySettingCalendar.setIsOverflowDateVisible(false);

        dDaySettingCalendar.refreshCalendar(Calendar.getInstance(Locale.getDefault()));
        dDaySettingCalendar.setOnDateSelectedListener(date -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault());
            selectDate = date.getTime();
            dDaySettingCalendar.setDateAsSelected(date);
            dDaySettingCalendar.setSelectedDayBackground(R.color.colorPrimaryDark);
            dDaySettingCalendar.setCurrentDay(date);
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

    // TODO: 2016-06-03 사귄날을 서버로 보내는 네트워킹 로직을 작성한다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_d_day_setting:
                Toast.makeText(this, selectDate+"", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
