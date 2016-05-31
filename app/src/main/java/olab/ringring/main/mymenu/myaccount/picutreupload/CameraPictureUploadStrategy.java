package olab.ringring.main.mymenu.myaccount.picutreupload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-31.
 */
public class CameraPictureUploadStrategy implements PictureUploadStrategy {
    AppCompatActivity activity;
    private final static int CAMERA_PICTURE_UPLOAD_CODE = 3;
    File uploadFile;


    public CameraPictureUploadStrategy(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void getImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getCameraCaptureFile());
        activity.startActivityForResult(intent, getStrategyCode());
    }

    private Uri getCameraCaptureFile() {
        File dir = activity.getExternalFilesDir("myphoto");
        uploadFile = new File(dir, "my_photo_" + System.currentTimeMillis() + ".jpg");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return Uri.fromFile(uploadFile);
    }

    @Override
    public int getStrategyCode() {
        return CAMERA_PICTURE_UPLOAD_CODE;
    }

    @Override
    public File getImageFile(ImageView attachImageView, int requestCode, int resultCode, Intent data) {
        if (requestCode == getStrategyCode()) {
            if (resultCode == Activity.RESULT_OK) {
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 2;
                Bitmap bm = BitmapFactory.decodeFile(uploadFile.getAbsolutePath(), opts);
                attachImageView.setImageBitmap(bm);
            }
        }
        return uploadFile;
    }
}
