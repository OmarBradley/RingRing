package olab.ringring.join.signup.partnerauthorization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;

public class DownloadLinkSendingActivity extends AppCompatActivity {

    @Bind(R.id.btn_goto_d_day_setting) Button btnGoToNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_download_link);
        ButterKnife.bind(this);
        btnGoToNextPage.setOnClickListener(view -> {
            Intent pageMover = new Intent(this, HomeActivity.class);
            startActivity(pageMover);
        });
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, PartnerConnectingActivity.class);
        startActivity(pageMover);
    }
}
