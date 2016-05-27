package olab.ringring.main.mymenu.myaccount;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.accountinfo.AccountInfoResult;
import olab.ringring.network.response.mymenu.changename.ChangeNameResult;
import olab.ringring.network.response.mymenu.changeusersex.ChangeUserSexResult;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;

public class MyAccountActivity extends AppCompatActivity implements ActionBarElement {

    @Bind(R.id.image_my_account_user_profile) ImageView userProfileImage;
    @Bind(R.id.edit_my_account_user_name) EditText userName;
    @Bind(R.id.edit_my_account_lover_name) EditText loverName;
    @Bind(R.id.edit_my_account_ring_size) EditText ringSize;
    @Bind(R.id.edit_my_account_user_gender) EditText userGender;
    @Bind(R.id.edit_my_account_user_phone_number) EditText userPhoneNumber;
    @Bind(R.id.edit_my_account_user_info) EditText userInfo;
    @Bind(R.id.edit_my_account_user_password_change) EditText userPasswordChange;
    @Bind(R.id.edit_my_account_user_password_confirm) EditText userPasswordConfirm;
    @Bind(R.id.btn_my_account_logout) Button logoutBtn;
    @Bind(R.id.btn_my_account_secession) Button secessionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)));

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
        getMenuInflater().inflate(R.menu.menu_my_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_my_menu_check:
                Toast.makeText(this,"sss",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        getResponseData();
        changeUserName();
        changeUserGender();
        super.onResume();
    }

    private void getResponseData(){
        NetworkManager.getInstance().getResult(MyMenuProtocol.makeMyAccountRequest(this), AccountInfoResult.class, (request, result) -> {
            AccountInfoResult data = result;
            attachResultDataInView(data);
        }, ((request, integer, throwable) -> {
            Toast.makeText(this, "알수 없는 에러" + integer, Toast.LENGTH_SHORT).show();
        }));
    }

    private void attachResultDataInView(AccountInfoResult data){
        if (data.getUserProfile() != null || !TextUtils.isEmpty(data.getUserProfile())) {
            Glide.with(this).load(data.getUserProfile()).into(userProfileImage);
        } else {
            userProfileImage.setImageResource(R.drawable.crystal_choice_image);
        }
        userName.setText(data.getUserName());
        loverName.setText(data.getLoverNickname());
        
        // TODO: 2016-05-27 반지 호수 text 정책 정하기
        ringSize.setText("");
        
        userGender.setText(data.getUserSex());
        userPhoneNumber.setText(data.getUserPhoneNumber());
        
        // TODO: 2016-05-27 유저 정보 text 정책 정하기
        userInfo.setText("");
        // TODO: 2016-05-27 비밀번호 바꾸기에 대한 정책 정하기
    }


    private void changeUserName() {
        userName.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String changedUserName = userName.getText().toString();
                NetworkManager.getInstance().getResult(MyMenuProtocol.makeChangeNameRequest(this, changedUserName), ChangeNameResult.class, (request, result) -> {
                    userName.setText(result.getUserNickName());
                    Toast.makeText(this, "이름이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }, (request, integer, throwable) -> {
                    Toast.makeText(this, "이름이 같습니다.", Toast.LENGTH_SHORT).show();
                });
            }
            return true;
        });
    }

    // TODO: 2016-05-27 성별 제한 두기!!.. WOMAN 과 MAN 밖에 칠 수 없도록...
    private void changeUserGender(){
        userGender.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String changedUserSex = userGender.getText().toString();
                NetworkManager.getInstance().getResult(MyMenuProtocol.makeChangeUserSexRequest(this, UserSexConstant.valueOf(changedUserSex)), ChangeUserSexResult.class, (request, result) -> {
                    userGender.setText(result.getUserSex());
                    Toast.makeText(this, "성별이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }, (request, integer, throwable) -> {
                    Toast.makeText(this, "성별이 같습니다.", Toast.LENGTH_SHORT).show();
                });
            }
            return true;
        });
    }
}
