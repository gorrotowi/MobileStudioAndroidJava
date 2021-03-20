package com.mobilestudio.android_105_navigation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobilestudio.android_105_navigation.adapters.AdapterMail;
import com.mobilestudio.android_105_navigation.adapters.AdapterMailKotlin;
import com.mobilestudio.android_105_navigation.databinding.FragmentListMailBinding;
import com.mobilestudio.android_105_navigation.models.Mail;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class ListMailFragment extends BaseFragment {

    private FragmentListMailBinding binding;
    private AdapterMail adapter;
    private AdapterMailKotlin adapterMailKotlin;

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
        adapterMailKotlin = new AdapterMailKotlin(getFakeMails());

        adapter.setOnItemClickListener(mail -> {
            Log.i("Mail", mail.getTitle());
            Log.i("Mail", mail.getSubject());
            if (listener != null) {
                listener.getMail(mail);
            }
        });

        adapterMailKotlin.setOnItemClickListener(new Function1<Mail, Unit>() {
            @Override
            public Unit invoke(Mail mail) {
                return null;
            }
        });

        adapterMailKotlin.setOnItemClickListener(mailKt -> {
            Log.e("KT", mailKt.getTitle());
            if (listener != null) {
                listener.getMail(mailKt);
            }
            return Unit.INSTANCE;
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
        binding.rcMails.setAdapter(adapterMailKotlin);
    }

    @Override
    public String getName() {
        return "Mails";
    }

    public interface OnMail {
        void getMail(Mail mail);
    }
}