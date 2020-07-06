package com.example.apple.wyymusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.apple.wyymusic.fragment.FindFragment;
import com.example.apple.wyymusic.fragment.FriendFragment;
import com.example.apple.wyymusic.fragment.MeFragment;
import com.example.apple.wyymusic.fragment.VideoFragment;

/**
 * Created by apple on 2019/6/17.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new MeFragment();
                break;
            case 1:
                fragment = new FindFragment();
                break;
            case 2:
                fragment = new FriendFragment();
                break;
            case 3:
                fragment = new VideoFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

}