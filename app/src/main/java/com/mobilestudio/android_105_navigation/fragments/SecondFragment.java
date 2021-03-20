package com.mobilestudio.android_105_navigation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobilestudio.android_105_navigation.R;

public class SecondFragment extends BaseFragment {

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String username = SecondFragmentArgs.fromBundle(getArguments()).getUsername();
        String email = SecondFragmentArgs.fromBundle(getArguments()).getEmail();

        Log.e("SECONDFRAGMENT", "" + username);
        Log.e("SECONDFRAGMENT", "" + email);
    }

    @Override
    public String getName() {
        return "Second";
    }
}