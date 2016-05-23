package olab.ringring.join.signup.partnerauthorization;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.JoinActivity;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarTitleVisitor;

public class LoverConnectingActivity extends AppCompatActivity implements ActionBarElement {

    @Bind(R.id.edit_lover_phone_number) EditText editLoverPhoneNumber;
    @Bind(R.id.btn_lover_connect) Button btnLoverConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lover_connecting);
        ButterKnife.bind(this);
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)));
        this.accept(new SetActionBarTitleVisitor("회원가입"));
        btnLoverConnect.setOnClickListener(view -> {
            Intent nextPageIntent = new Intent(this, SendDownloadLinkActivity.class);
            startActivity(nextPageIntent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, JoinActivity.class);
        startActivity(pageMover);
    }

    @Override
    public void accept(ActionbarVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
