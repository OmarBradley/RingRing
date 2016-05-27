package olab.ringring.main.mymenu.missionhistory;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;

public class MissionHistoryActivity extends AppCompatActivity implements ActionBarElement{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_history);
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



}
