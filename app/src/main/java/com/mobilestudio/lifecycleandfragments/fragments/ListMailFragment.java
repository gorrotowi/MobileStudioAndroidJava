package com.mobilestudio.lifecycleandfragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilestudio.lifecycleandfragments.R;


public class ListMailFragment extends Fragment {

    public ListMailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_list_mail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = getActivity().findViewById(R.id.btnHello);

        btn.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Hello from Fragment", Toast.LENGTH_SHORT).show();
        });
    }
}