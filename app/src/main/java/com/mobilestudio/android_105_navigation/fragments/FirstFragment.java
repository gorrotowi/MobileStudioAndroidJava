package com.mobilestudio.android_105_navigation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.mobilestudio.android_105_navigation.R;
import com.mobilestudio.android_105_navigation.databinding.FragmentFirstBinding;


public class FirstFragment extends BaseFragment {

    private FragmentFirstBinding binding;

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
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnGoSecond.setOnClickListener(v -> {
            NavDirections action = FirstFragmentDirections
                    .actionFirstFragmentToSecondFragment();

            FirstFragmentDirections.ActionFirstFragmentToSecondFragment actionWithArgs = FirstFragmentDirections.actionFirstFragmentToSecondFragment();
            actionWithArgs.setUsername("sebastianUser");
            actionWithArgs.setEmail("adsf@mymail.com");

            Navigation.findNavController(requireActivity(), R.id.navHostFragmentDrawer).navigate(actionWithArgs);
        });


        binding.btnGoThird.setOnClickListener(v -> {
            NavDirections action = FirstFragmentDirections.actionFirstFragmentToThirdFragment();
            Navigation.findNavController(requireActivity(), R.id.navHostFragmentDrawer).navigate(action);
        });

    }

    @Override
    public String getName() {
        return "First";
    }
}