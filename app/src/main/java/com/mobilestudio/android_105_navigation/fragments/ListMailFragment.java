package com.mobilestudio.android_105_navigation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobilestudio.android_105_navigation.adapters.AdapterMail;
import com.mobilestudio.android_105_navigation.databinding.FragmentListMailBinding;
import com.mobilestudio.android_105_navigation.models.Mail;

import java.util.ArrayList;
import java.util.List;


public class ListMailFragment extends BaseFragment {

    private FragmentListMailBinding binding;
    private AdapterMail adapter;

    private OnMail listener;

    public ListMailFragment() {
        // Required empty public constructor
    }

    public void setOnMailListener(OnMail mailListener) {
        listener = mailListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AdapterMail(getFakeMails());

        adapter.setOnItemClickListener(mail -> {
            Log.i("Mail", mail.getTitle());
            Log.i("Mail", mail.getSubject());
            if (listener != null) {
                listener.getMail(mail);
            }
        });
    }

    private List<Mail> getFakeMails() {
        List<Mail> fakeData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            fakeData.add(new Mail("Title " + i, "Subject " + i));
        }
        return fakeData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListMailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rcMails.setAdapter(adapter);
    }

    @Override
    public String getName() {
        return "Mails";
    }

    public interface OnMail {
        void getMail(Mail mail);
    }
}