package olab.ringring.main.mymenu.myaccount;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
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
import olab.ringring.gcm.KeywordSuccessReceiver;
import olab.ringring.gcm.RingRingGcmListenerService;
import olab.ringring.join.JoinActivity;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.main.mymenu.myaccount.picutreupload.CameraPictureUploadStrategy;
import olab.ringring.main.mymenu.myaccount.picutreupload.GalleryPictureUploadStrategy;
import olab.ringring.main.mymenu.myaccount.picutreupload.PictureUploadStrategy;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.ImageFileFormData;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.request.users.UsersProtocol;
import olab.ringring.network.response.mymenu.accountinfo.SuccessAccountInfo;
import olab.ringring.network.response.mymenu.changename.SuccessChangeName;
import olab.ringring.network.response.mymenu.changeprofile.SuccessChangeProfileImage;
import olab.ringring.network.response.mymenu.changeusersex.SuccessChangeUserSex;
import olab.ringring.network.response.users.SuccessLogout;
import olab.ringring.util.dialog.confirm.ConfirmDialogFragment;
import olab.ringring.util.dialog.confirm.ConfirmDialogInfoPool;
import olab.ringring.util.normalvisitor.element.NormalActivityElement;
import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;
import olab.ringring.util.normalvisitor.visitor.concretevisitor.SetActionBarIconVisitor;
import olab.ringring.util.dialog.select.SelectDialogBuilder;
import olab.ringring.util.dialog.select.SelectDialogFragment;
import olab.ringring.util.dialog.select.SelectDialogItemData;
import olab.ringring.util.preperance.PropertyManager;
import olab.ringring.util.string.StringHandler;

public class MyAccountActivity extends AppCompatActivity implements NormalActivityElement {

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

    private PictureUploadStrategy uploadStrategy;
    private KeywordSuccessReceiver keywordSuccessReceiver;
    private IntentFilter keywordSuccessDialogFilter = new IntentFilter(RingRingGcmListenerService.KEYWORD_SUCCESS_DIALOG);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        initKeywordSuccessReceiver();
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this, R.drawable.actionbar_home_as_up_image)));
        setOnClickListenerInLogoutBtn();
    }

    private void initKeywordSuccessReceiver(){
        keywordSuccessReceiver = new KeywordSuccessReceiver(this);
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, MyMenuActivity.class);
        pageMover.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pageMover);
        finish();
    }

    @Override
    public void accept(NormalActivityVisitor visitor) {
        visitor.visit(this);
    }


    private void setOnClickListenerInLogoutBtn(){
        logoutBtn.setOnClickListener(view -> {
            NetworkManager.getInstance().sendRequest(UsersProtocol.makeLogoutRequest(this), SuccessLogout.class, (request, result) -> {
                Log.e("logout success", result.toString());
                PropertyManager.getInstance().setUserEmail(PropertyManager.DEFAULT_USER_EMAIL);
                PropertyManager.getInstance().setUserPassword(PropertyManager.DEFAULT_USER_PASSWORD);
                Intent intent = new Intent(this, JoinActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finishAffinity();
            }, (request, networkResponseCode, throwable) -> {
                Log.e("logout fail", networkResponseCode.getMessage());
            });
        });
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
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        initView();
        getResponseData();
        changeUserName();
        changeUserGender();
        setOnProfileImageClickListener();
        buildSexChoiceDialog();
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(keywordSuccessReceiver,keywordSuccessDialogFilter );
    }

    private void initView(){
        loverName.setEnabled(false);
        userPhoneNumber.setEnabled(false);
        ringSize.setEnabled(false);
        userPasswordChange.setEnabled(false);
    }

    private void getResponseData(){
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeMyAccountRequest(this), SuccessAccountInfo.class, (request, result) -> {
            SuccessAccountInfo data = result;
            attachResultDataInView(data);
        }, ((request, integer, throwable) -> {
            Toast.makeText(this, "알수 없는 에러" + integer, Toast.LENGTH_SHORT).show();
        }));
    }

    private void attachResultDataInView(SuccessAccountInfo data){
        if (data.getUserProfile() != null || !TextUtils.isEmpty(data.getUserProfile())) {
            Glide.with(this).load(data.getUserProfile()).into(userProfileImage);
        } else {
            userProfileImage.setImageResource(R.drawable.my_account_default_image);
        }
        userName.setText(data.getUserName());
        loverName.setText(data.getLoverNickname());

        // TODO: 2016-05-27 반지 호수 text 정책 정하기
        ringSize.setText("");

        if(data.getUserSex()  == null){
            userSex.setText("성별");
        } else {
            userSex.setText(UserSexConstant.valueOf(data.getUserSex()).getSexText());
        }
        userPhoneNumber.setText(data.getUserPhoneNumber());
        
        // TODO: 2016-05-27 유저 정보 text 정책 정하기
        userInfo.setText("");
        // TODO: 2016-05-27 비밀번호 바꾸기에 대한 정책 정하기
    }


    private void changeUserName() {
        userName.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String changedUserName = userName.getText().toString();
                if(changedUserName.length() > 0){
                    NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeChangeNameRequest(this, changedUserName), SuccessChangeName.class, (request, result) -> {
                        userName.setText(result.getUserNickName());
                        Toast.makeText(this, "이름이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                    }, (request, integer, throwable) -> {
                        Toast.makeText(this, "이름이 같습니다.", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    ConfirmDialogFragment emptyNameDialog = ConfirmDialogInfoPool.EMPTY_NAME_ERROR.makeConfirmDialog();
                    emptyNameDialog.show(getSupportFragmentManager(), "empty name dialog");
                }
            }
            return true;
        });
    }

    private void changeUserGender(){
        userSex.setOnEditorActionListener((textView, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String changedUserSex = userSex.getText().toString();
                NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeChangeUserSexRequest(this, UserSexConstant.valueOf(changedUserSex)), SuccessChangeUserSex.class, (request, result) -> {
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
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeChangeUserSexRequest(this, userSexConstant), SuccessChangeUserSex.class, (request, result) -> {
            userSex.setText(UserSexConstant.valueOf(result.getUserSex()).getSexText());
            Toast.makeText(this, "성별이 변경 되었습니다", Toast.LENGTH_SHORT);
        }, (request, errorCode, throwable) -> {
            Toast.makeText(this, errorCode.getMessage(), Toast.LENGTH_SHORT);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            uploadImage(requestCode, resultCode, data);
        } catch (Exception e) {
            Log.e("upload error", e.toString());
        }
    }

    private void uploadImage(int requestCode, int resultCode, Intent data){
        File uploadFile = uploadStrategy.getImageFile(userProfileImage, requestCode, resultCode, data);
        Log.e("upload",uploadFile.getAbsolutePath() );
        ImageFileFormData imageData = new ImageFileFormData();
        imageData.setBodyName("profile");
        imageData.setFileName(uploadFile.getName());
        imageData.setImageFile(uploadFile);
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeSetProfileImageRequest(this, imageData), SuccessChangeProfileImage.class, (request, result)->{
            PropertyManager.getInstance().setUserProfileImageUrl(result.getThumnailPicturePath());
            if (StringHandler.isCorrectImageUrl(result.getOriginalPicturePath())) {
                Glide.with(this).load(result.getOriginalPicturePath()).into(userProfileImage);
            } else {
                userProfileImage.setImageResource(R.drawable.my_account_default_image);
            }
        }, (request, errorCode, throwable) -> {
            Log.e("picture upload", errorCode.getMessage());
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(keywordSuccessReceiver);
    }
}
