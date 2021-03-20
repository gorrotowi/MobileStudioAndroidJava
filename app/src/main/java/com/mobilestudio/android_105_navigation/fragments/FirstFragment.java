package com.mobilestudio.android_105_navigation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.mobilestudio.android_105_navigation.R;


public class FirstFragment extends BaseFragment {


    public FirstFragment() {
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
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnSecond = requireActivity().findViewById(R.id.btnGoSecond);
        Button btnThird = requireActivity().findViewById(R.id.btnGoThird);

        btnSecond.setOnClickListener(v -> {
            NavDirections action = FirstFragmentDirections.actionFirstFragmentToSecondFragment();
            Navigation.findNavController(requireActivity(), R.id.navHostFragment).navigate(action);
        });

        btnThird.setOnClickListener(v -> {
            NavDirections action = FirstFragmentDirections.actionFirstFragmentToThirdFragment();
            Navigation.findNavController(requireActivity(), R.id.navHostFragment).navigate(action);
        });

    }

    @Override
    public String getName() {
        return "First";
    }
}