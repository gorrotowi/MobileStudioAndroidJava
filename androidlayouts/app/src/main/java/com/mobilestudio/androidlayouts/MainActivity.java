package com.mobilestudio.androidlayouts;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLog;

    private ConstraintSet contraintStart = new ConstraintSet();
    private ConstraintSet contraintEnd = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_start);

        imgLog = findViewById(R.id.imgLogoLogin);

        imgLog.setOnClickListener(v -> { });

    }
}