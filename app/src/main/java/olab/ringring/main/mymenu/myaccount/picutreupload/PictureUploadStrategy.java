package olab.ringring.main.mymenu.myaccount.picutreupload;

import android.content.Intent;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by 재화 on 2016-05-31.
 */
public interface PictureUploadStrategy {
    public void getImage();
    public int getStrategyCode();
    public File getImageFile(ImageView attachImageView, int requestCode, int resultCode, Intent data);
}
