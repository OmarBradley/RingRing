package olab.ringring.join.signup.lovercertification;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.util.normalvisitor.element.NormalActivityElement;
import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;
import olab.ringring.util.normalvisitor.visitor.concretevisitor.SetActionBarIconVisitor;
import olab.ringring.util.normalvisitor.visitor.concretevisitor.SetActionBarTitleVisitor;

public class SendDownloadLinkActivity extends AppCompatActivity implements NormalActivityElement {

    @Bind(R.id.btn_send_download_link) Button sendDownloadLinkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_download_link);
        ButterKnife.bind(this);
        this.accept(new SetActionBarTitleVisitor("회원가입"));
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.drawable.actionbar_home_as_up_image)));
        sendDownloadLinkBtn.setOnClickListener(view -> {
            Intent pageMover = new Intent(this, HomeActivity.class);
            startActivity(pageMover);
        });
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, LoverConnectingActivity.class);
        startActivity(pageMover);
        finish();
    }

    @Override
    public void accept(NormalActivityVisitor visitor) {
        visitor.visit(this);
    }
}