package com.mobilestudio.lifecycleandfragments.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobilestudio.lifecycleandfragments.databinding.FragmentDetailMailBinding;

import java.util.Calendar;


public class DetailMailFragment extends Fragment {

    public static String EXTRA_TITLE = "title";
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
            String title = extras.getString(EXTRA_TITLE, "");
            String subject = extras.getString("subject", "");
            String content = extras.getString("content", "");

            binding.txtTitleMail.setText(title);
            binding.txtContentMail.setText(content);
        }

        binding.btnWebMail.setOnClickListener(v -> {

            Intent intentWeb = new Intent(Intent.ACTION_VIEW);
            intentWeb.setData(Uri.parse("http://www.google.com"));
            startActivity(intentWeb);

        });

        binding.btnCallMail.setOnClickListener(v -> {

            Intent intentCall = new Intent(Intent.ACTION_CALL);
            intentCall.setData(Uri.parse("tel:5530303010"));
            startActivity(intentCall);

        });

        binding.btnSendNumberMail.setOnClickListener(v -> {

            Intent intentCall = new Intent(Intent.ACTION_DIAL);
            intentCall.setData(Uri.parse("tel:5530303010"));
            startActivity(intentCall);

        });

        binding.btnCalendarMail.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            Intent intentSMS = new Intent(Intent.ACTION_INSERT);
            intentSMS.setData(CalendarContract.Events.CONTENT_URI);
            intentSMS.putExtra(CalendarContract.Events.TITLE, "Mi super evento");
            intentSMS.putExtra(CalendarContract.Events.EVENT_LOCATION, "En cocoyoc");
            intentSMS.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
            intentSMS.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendar.getTimeInMillis());
            startActivity(intentSMS);

        });

        binding.btnOpenMaps.setOnClickListener(v -> {
            Intent intentOpenLocation = new Intent(Intent.ACTION_VIEW);
            intentOpenLocation.setData(Uri.parse("geo:19.4173683,-99.1655279"));
            if (intentOpenLocation.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intentOpenLocation);
            } else {
                Toast.makeText(requireActivity(), "No hay una aplicación para abrir la geoposición", Toast.LENGTH_SHORT).show();
            }

        });

        binding.btnShareEvent.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "http://eventofiest.com");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, "Compartir Evento");
            startActivity(shareIntent);
        });
    }
}