package olab.ringring.main.mymenu.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 재화 on 2016-06-05.
 */
public class ProfileImageAdapter extends FragmentPagerAdapter {
    private final List<ProfileImageFragment> fragments = new ArrayList<>();

    public ProfileImageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(ProfileImageFragment fragment, String imageUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(ProfileImageFragment.IMAGE_URL_KEY, imageUrl);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public ProfileImageFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
