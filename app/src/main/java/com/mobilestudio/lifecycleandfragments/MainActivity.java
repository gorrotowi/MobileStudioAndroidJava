package com.mobilestudio.lifecycleandfragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();

    TextView txtHello;

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "OnCreate");
        txtHello = findViewById(R.id.txtHello);
        txtHello.setOnClickListener(v -> {
//            Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "OnStart");
//        txtHello.setOnClickListener(v -> {
//            Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show();
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "OnDestroy");
    }
}