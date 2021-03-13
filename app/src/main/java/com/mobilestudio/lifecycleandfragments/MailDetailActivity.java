package com.mobilestudio.lifecycleandfragments;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilestudio.lifecycleandfragments.fragments.DetailMailFragment;

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

        DetailMailFragment fragment = new DetailMailFragment();
        Bundle argsFragment = new Bundle();
        argsFragment.putString(DetailMailFragment.EXTRA_TITLE, title);
        argsFragment.putString("subject", subject);
        argsFragment.putString("content", "asdkjfasdlkfjadslfknwjefinvasdjkvnaldskfjqoi sdf qdosfqwjdf qowdf adf");

        fragment.setArguments(argsFragment);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mailDetailContainer, fragment)
                .commit();
    }
}