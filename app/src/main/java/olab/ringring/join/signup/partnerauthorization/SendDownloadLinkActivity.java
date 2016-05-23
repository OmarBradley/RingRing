package olab.ringring.join.signup.partnerauthorization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;

public class SendDownloadLinkActivity extends AppCompatActivity {

    @Bind(R.id.btn_send_download_link) Button btnSendDownloadLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_download_link);
        ButterKnife.bind(this);
        btnSendDownloadLink.setOnClickListener(view -> {
            Intent pageMover = new Intent(this, HomeActivity.class);
            startActivity(pageMover);
        });
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, LoverConnectingActivity.class);
        startActivity(pageMover);
    }
}