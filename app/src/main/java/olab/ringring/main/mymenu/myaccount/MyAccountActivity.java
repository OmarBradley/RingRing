package olab.ringring.main.mymenu.myaccount;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.main.mymenu.myaccount.picutreupload.CameraPictureUploadStrategy;
import olab.ringring.main.mymenu.myaccount.picutreupload.GalleryPictureUploadStrategy;
import olab.ringring.main.mymenu.myaccount.picutreupload.PictureUploadStrategy;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.ImageFileFormData;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.accountinfo.AccountInfoResult;
import olab.ringring.network.response.mymenu.changename.ChangeNameResult;
import olab.ringring.network.response.mymenu.changeprofile.ChangeProfileImageResult;
import olab.ringring.network.response.mymenu.changeusersex.ChangeUserSexResult;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;
import olab.ringring.util.dialog.select.SelectDialogBuilder;
import olab.ringring.util.dialog.select.SelectDialogFragment;
import olab.ringring.util.dialog.select.SelectDialogItemData;
import olab.ringring.util.preperance.PropertyManager;

public class MyAccountActivity extends AppCompatActivity implements ActionBarElement {

    @Bind(R.id.image_my_account_user_profile) ImageView userProfileImage;
    @Bind(R.id.edit_my_account_user_name) EditText userName;
    @Bind(R.id.edit_my_account_lover_name) EditText loverName;
    @Bind(R.id.edit_my_account_ring_size) EditText ringSize;
    @Bind(R.id.text_my_account_user_sex) TextView userSex;
    @Bind(R.id.edit_my_account_user_phone_number) EditText userPhoneNumber;
    @Bind(R.id.edit_my_account_user_info) EditText userInfo;
    @Bind(R.id.edit_my_account_user_password_change) EditText userPasswordChange;
    @Bind(R.id.edit_my_account_user_password_confirm) EditText userPasswordConfirm;
    @Bind(R.id.btn_my_account_logout) Button logoutBtn;
    @Bind(R.id.btn_my_account_secession) Button secessionBtn;
    @Bind(R.id.layout_my_account_user_sex) RelativeLayout userSexLayout;

    PictureUploadStrategy uploadStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.drawable.actionbar_home_as_up_image)));

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
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        init();
        getResponseData();
        changeUserName();
        changeUserGender();
        setOnProfileImageClickListener();
        buildSexChoiceDialog();
        super.onResume();
    }

    private void init(){
        loverName.setEnabled(false);
        userPhoneNumber.setEnabled(false);
    }

    private void getResponseData(){
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeMyAccountRequest(this), AccountInfoResult.class, (request, result) -> {
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
            userProfileImage.setImageResource(R.drawable.my_account_default_image);
        }
        userName.setText(data.getUserName());
        loverName.setText(data.getLoverNickname());

        // TODO: 2016-05-27 반지 호수 text 정책 정하기
        ringSize.setText("");
        userSex.setText(UserSexConstant.valueOf(data.getUserSex()).getSexText());

        userPhoneNumber.setText(data.getUserPhoneNumber());
        
        // TODO: 2016-05-27 유저 정보 text 정책 정하기
        userInfo.setText("");
        // TODO: 2016-05-27 비밀번호 바꾸기에 대한 정책 정하기
    }


    private void changeUserName() {
        userName.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String changedUserName = userName.getText().toString();
                NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeChangeNameRequest(this, changedUserName), ChangeNameResult.class, (request, result) -> {
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
        userSex.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String changedUserSex = userSex.getText().toString();
                NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeChangeUserSexRequest(this, UserSexConstant.valueOf(changedUserSex)), ChangeUserSexResult.class, (request, result) -> {
                    userSex.setText(result.getUserSex());
                    Toast.makeText(this, "성별이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }, (request, integer, throwable) -> {
                    Toast.makeText(this, "성별이 같습니다.", Toast.LENGTH_SHORT).show();
                });
            }
            return true;
        });
    }

    private void setOnProfileImageClickListener() {
        userProfileImage.setOnClickListener(view -> {
            SelectDialogFragment selectDialog = new SelectDialogBuilder()
                    .setDialogTitle("프로필 설정")
                    .setItems(Arrays.asList(new SelectDialogItemData((dialog, dialogItemIndex) -> {
                        dialog.dismiss();
                        uploadStrategy = new GalleryPictureUploadStrategy(MyAccountActivity.this);
                        uploadStrategy.getImage();
                    }, "사진 엘범에서 선택"), new SelectDialogItemData((dialog, dialogItemIndex) -> {
                        dialog.dismiss();
                        uploadStrategy = new CameraPictureUploadStrategy(MyAccountActivity.this);
                        uploadStrategy.getImage();
                    }, "사진 촬영")))
                    .setItemViewCenterAlign(false)
                    .build();
            selectDialog.show(getSupportFragmentManager(), "selectDialog");
        });
    }

    private void buildSexChoiceDialog() {
        userSexLayout.setOnClickListener(view -> {
            SelectDialogFragment selectDialog = new SelectDialogBuilder()
                    .setDialogTitle("성별")
                    .setItems(Arrays.asList(new SelectDialogItemData((dialog, dialogItemIndex) -> {
                        dialog.dismiss();
                        changeUserSex(UserSexConstant.MAN);
                    }, "남"), new SelectDialogItemData((dialog, dialogItemIndex) -> {
                        dialog.dismiss();
                        changeUserSex(UserSexConstant.WOMAN);
                    }, "여")))
                    .setItemViewCenterAlign(true)
                    .build();
            selectDialog.show(getSupportFragmentManager(), "selectDialog");
        });
    }

    private void changeUserSex(UserSexConstant userSexConstant){
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeChangeUserSexRequest(this, userSexConstant), ChangeUserSexResult.class, (request, result) -> {
            userSex.setText(UserSexConstant.valueOf(result.getUserSex()).getSexText());
            Toast.makeText(this, "성별이 변경 되었습니다", Toast.LENGTH_SHORT);
        }, (request, integer, throwable) -> {
            Toast.makeText(this, "오류", Toast.LENGTH_SHORT);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File uploadFile = uploadStrategy.getImageFile(userProfileImage, requestCode, resultCode, data);
        Log.e("file", uploadFile.getName());
        ImageFileFormData imageData = new ImageFileFormData();
        imageData.setBodyName("profile");
        imageData.setFileName(uploadFile.getName());
        imageData.setImageFile(uploadFile);
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeSetProfileImageRequest(this, imageData), ChangeProfileImageResult.class, (request, result)->{
            PropertyManager.getInstance().setUserProfileImageUrl(result.getThumnailPicturePath());
            if (result.getOriginalPicturePath() != null || !TextUtils.isEmpty(result.getOriginalPicturePath())) {
                Glide.with(this).load(result.getOriginalPicturePath()).into(userProfileImage);
            } else {
                userProfileImage.setImageResource(R.drawable.my_account_default_image);
            }
        }, (request, integer, throwable) -> {
            Log.e("eeee",request.toString() + integer);
        });


    }
}
