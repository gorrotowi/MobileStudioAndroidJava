package com.mobilestudio.android_105_navigation.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mobilestudio.android_105_navigation.fragments.BaseFragment;

import java.util.List;

public class ViewPagerMainAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragmentList;

    public ViewPagerMainAdapter(@NonNull FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getName();
    }
}
