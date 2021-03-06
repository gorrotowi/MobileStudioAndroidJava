package com.mobilestudio.lifecycleandfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilestudio.lifecycleandfragments.fragments.DetailMailFragment;
import com.mobilestudio.lifecycleandfragments.fragments.ListMailFragment;
import com.mobilestudio.lifecycleandfragments.models.Mail;

public class ShowFragmentsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnShow1;
    Button btnShow2;

    ListMailFragment fragmentMails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fragments);

        fragmentMails = new ListMailFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragmentMails)
                .commit();

        btnShow1 = findViewById(R.id.btnFrag1);
        btnShow2 = findViewById(R.id.btnFrag2);

        if (btnShow1 != null) {
            btnShow1.setOnClickListener(this);
            btnShow2.setOnClickListener(this);
        }

        fragmentMails.setOnMailListener(mail -> {

            if (findViewById(R.id.fragmentContainerDetail) != null) {
                // replace fragment
                Bundle arguments = new Bundle();
                arguments.putString("title", mail.getTitle());
                arguments.putString("subject", mail.getSubject());
                arguments.putString("content", getString(R.string.lorem_mail));
                DetailMailFragment fragmentMail = new DetailMailFragment();
                fragmentMail.setArguments(arguments);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainerDetail, fragmentMail)
                        .commit();
            } else {
                showMailDetail(mail);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public Mail getRandomMail() {
        return new Mail("RANDOM", "RANDOM");
    }

    @Override
    public void onClick(View viewClicked) {
        if (viewClicked.getId() == R.id.btnFrag1) {
            //replace fragment 1
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new ListMailFragment())
                    .commit();
        }
        if (viewClicked.getId() == R.id.btnFrag2) {
            // replace fragment 2
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new DetailMailFragment())
                    .commit();
        }
    }

    private void showMailDetail(Mail mail) {
        Intent intentShowDetail = new Intent(this, MailDetailActivity.class);
        intentShowDetail.putExtra("title", mail.getTitle());
        intentShowDetail.putExtra("subject", mail.getSubject());
        startActivity(intentShowDetail);
    }
}