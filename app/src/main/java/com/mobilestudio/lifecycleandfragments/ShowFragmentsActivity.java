package com.mobilestudio.lifecycleandfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilestudio.lifecycleandfragments.fragments.DetailMailFragment;
import com.mobilestudio.lifecycleandfragments.fragments.ListMailFragment;

public class ShowFragmentsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnShow1;
    Button btnShow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fragments);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, new ListMailFragment())
                .commit();

        btnShow1 = findViewById(R.id.btnFrag1);
        btnShow2 = findViewById(R.id.btnFrag2);

        btnShow1.setOnClickListener(this);
        btnShow2.setOnClickListener(this);

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
}