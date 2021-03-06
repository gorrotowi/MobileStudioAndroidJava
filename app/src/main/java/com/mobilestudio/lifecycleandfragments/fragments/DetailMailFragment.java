package com.mobilestudio.lifecycleandfragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilestudio.lifecycleandfragments.databinding.FragmentDetailMailBinding;


public class DetailMailFragment extends Fragment {

    FragmentDetailMailBinding binding;

    public DetailMailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailMailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extras = getArguments();

        if (extras != null) {
            String title = extras.getString("title", "");
            String subject = extras.getString("subject", "");
            String content = extras.getString("content", "");

            binding.txtTitleMail.setText(title);
            binding.txtContentMail.setText(content);
        }
    }
}