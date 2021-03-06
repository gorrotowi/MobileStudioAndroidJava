package com.mobilestudio.lifecycleandfragments;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MailDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_detail);

        Bundle extras = getIntent().getExtras();

        String title = extras.getString("title");
        String subject = extras.getString("subject");

        Log.d("Title", title);
        Log.d("subject", subject);
    }
}