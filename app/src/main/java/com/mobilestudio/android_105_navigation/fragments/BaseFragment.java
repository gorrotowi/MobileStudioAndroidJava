package com.mobilestudio.android_105_navigation.fragments;

import android.util.Log;

import androidx.fragment.app.Fragment;

interface NamedFragment {
    String getName();
}

public class BaseFragment extends Fragment implements NamedFragment {

    @Override
    public void onResume() {
        super.onResume();
        Log.d("RESUME", "adsfasdf");
    }

    @Override
    public String getName() {
        return "";
    }
}
