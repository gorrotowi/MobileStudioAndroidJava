package com.mobilestudio.androidlayouts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityOptionsCompat;
import androidx.transition.TransitionManager;

public class MainActivity extends AppCompatActivity {

    boolean isConstraintSet = false;

    private ImageView imgLog;
    private Button btnLogin;
    private ConstraintLayout rootContainer;

    private ConstraintSet contraintStart = new ConstraintSet();
    private ConstraintSet contraintEnd = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_start);

        rootContainer = findViewById(R.id.rootContainer);
        imgLog = findViewById(R.id.imgLogoLogin);
        btnLogin = findViewById(R.id.btnLogin);

        contraintStart.clone(rootContainer);
        contraintEnd.clone(this, R.layout.login_end);

        imgLog.setOnClickListener(v -> {
            Log.d("Hello Constraints", "Click IMG LOGO");
            TransitionManager.beginDelayedTransition(rootContainer);
            ConstraintSet constraintSetToAssign = isConstraintSet ? contraintStart : contraintEnd;
            constraintSetToAssign.applyTo(rootContainer);
            isConstraintSet = !isConstraintSet;
        });

        btnLogin.setOnClickListener(v -> {
            Intent intentToHome = new Intent(this, HomeActivity.class);

            ActivityOptionsCompat optionsActivity = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    imgLog,
                    getString(R.string.transition_image_logo)
            );

            startActivity(intentToHome, optionsActivity.toBundle());
        });

    }
}