package olab.ringring.main.mymenu.myaccount.picutreupload;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import olab.ringring.R;

/**
 * Created by 재화 on 2016-05-31.
 */
public class GalleryPictureUploadStrategy implements PictureUploadStrategy {

    AppCompatActivity activity;
    private final static int GALLERY_PICTURE_UPLOAD_CODE = 2;

    public GalleryPictureUploadStrategy(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void getImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/jpeg");
        activity.startActivityForResult(intent, getStrategyCode());
    }

    @Override
    public int getStrategyCode() {
        return GALLERY_PICTURE_UPLOAD_CODE;
    }

    @Override
    public File getImageFile(ImageView attachImageView, int requestCode, int resultCode, Intent data) {
        File file = null;
        if (requestCode == this.getStrategyCode()) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor c = activity.getContentResolver().query(uri, projection, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    file = new File(path);
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    opts.inSampleSize = 2;
                }
            }
        }
        return file;
    }
}
