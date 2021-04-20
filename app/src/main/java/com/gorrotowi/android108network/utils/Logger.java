package com.gorrotowi.android108network.utils;

import android.util.Log;

import com.gorrotowi.android108network.BuildConfig;

public class Logger {

    public static void loge(String message) {
        if (BuildConfig.DEBUG) {
            Log.e("TAG", message);
        }
    }
}
