package olab.ringring.main.mymenu.profile;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.showprofile.ShowProfileResult;

public class ProfileImageActivity extends AppCompatActivity {

    private ProfileImageAdapter pageAdapter;
    @Bind(R.id.btn_profile_activity_cancel) Button cancelBtn;
    @Bind(R.id.pager_profile_image) ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_image);
        ButterKnife.bind(this);
        initCancelButton();
        setPager();
        addFragment();
    }

    private void initCancelButton(){
        cancelBtn.setOnClickListener(view ->{
            Intent intent = new Intent(this, MyMenuActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setPager() {
        pageAdapter = new ProfileImageAdapter(getSupportFragmentManager());
        pager.setAdapter(pageAdapter);
    }

    private void addFragment(){
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeShowProfileRequest(this), ShowProfileResult.class , (request, result) -> {
            pageAdapter.addFragment(new ProfileImageFragment(), result.getUserProfile());
        }, (request, integer, throwable) -> {
            Toast.makeText(this, "newwork error", Toast.LENGTH_SHORT).show();
        });


    }

}
