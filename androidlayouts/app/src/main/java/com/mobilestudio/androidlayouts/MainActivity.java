package com.mobilestudio.androidlayouts;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.TransitionManager;

public class MainActivity extends AppCompatActivity {

    boolean isConstraintSet = false;

    private ImageView imgLog;
    private TextView txtAppName;
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
        txtAppName = findViewById(R.id.txtAppName);
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

            Pair<View, String> pairImage = Pair.create(imgLog, imgLog.getTransitionName());
            Pair<View, String> pairText = Pair.create(txtAppName, txtAppName.getTransitionName());

            ActivityOptions optionsActivity = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    pairImage,
                    pairText
            );

            startActivity(intentToHome, optionsActivity.toBundle());
        });

    }
}