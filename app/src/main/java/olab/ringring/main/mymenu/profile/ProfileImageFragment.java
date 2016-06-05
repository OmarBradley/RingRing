package olab.ringring.main.mymenu.profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.annimon.stream.function.Consumer;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import lombok.Setter;
import olab.ringring.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileImageFragment extends Fragment {

    @Bind(R.id.image_user_profile_picture) ImageView profilePicture;
    private String imageUrl;

    public static final String IMAGE_URL_KEY = "image_url";

    public ProfileImageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View profileImageView = inflater.inflate(R.layout.fragment_profile_image, container, false);
        ButterKnife.bind(this, profileImageView);
        initProfilePicture(imageUrl);
        return profileImageView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        imageUrl = bundle.getString(IMAGE_URL_KEY);
    }

    private void initProfilePicture(String imageUrl){
        if (imageUrl!= null || !TextUtils.isEmpty(imageUrl)) {
            Glide.with(this).load(imageUrl).into(profilePicture);
        }
    }
}
