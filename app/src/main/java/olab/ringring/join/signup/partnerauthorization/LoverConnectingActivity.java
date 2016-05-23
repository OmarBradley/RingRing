package olab.ringring.join.signup.partnerauthorization;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.JoinActivity;

public class LoverConnectingActivity extends AppCompatActivity {

    @Bind(R.id.edit_lover_phone_number) EditText editLoverPhoneNumber;
    @Bind(R.id.btn_lover_connect) Button btnLoverConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lover_connecting);
        ButterKnife.bind(this);
        setActionBarIcon();
        setActionBarTitle();
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

    private void setActionBarIcon() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(getResources().getDrawable(android.R.mipmap.sym_def_app_icon));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void setActionBarTitle(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("회원가입");
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
