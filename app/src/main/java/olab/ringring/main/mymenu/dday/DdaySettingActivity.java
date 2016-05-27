package olab.ringring.main.mymenu.dday;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;


public class DDaySettingActivity extends AppCompatActivity implements ActionBarElement{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_day_setting);
        ButterKnife.bind(this);
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this,R.mipmap.ic_launcher)));
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
        switch (item.getItemId()){
            case R.id.action_d_day_setting:
                Toast.makeText(this,"menu",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
