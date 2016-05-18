package olab.ringring.join.signup.partnerauthorization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.JoinActivity;

public class PartnerConnectingActivity extends AppCompatActivity {

    @Bind(R.id.btn_goto_d_day_setting) Button btnGoToNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_connecting);
        ButterKnife.bind(this);
        btnGoToNextPage.setOnClickListener(view -> {
            Intent pageMover = new Intent(this, DownloadLinkSendingActivity.class);
            startActivity(pageMover);
        });
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, JoinActivity.class);
        startActivity(pageMover);
    }
}
