package com.mobilestudio.lifecycleandfragments;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mobilestudio.lifecycleandfragments.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();

    ActivityMainBinding binding;

    String KEY_COUNTER = "KEY_COUNTER";

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(KEY_COUNTER);
            binding.txtHello.setText("Counter " + counter);
        }
        Log.e(TAG, "OnCreate");

        binding.txtHello.setOnClickListener(v -> {
            counter = counter + 1;
            binding.txtHello.setText("Counter " + counter);
        });

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_COUNTER, counter);
        super.onSaveInstanceState(outState);
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