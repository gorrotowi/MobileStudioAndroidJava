package com.mobilestudio.androidjava101;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MasterActivity extends AppCompatActivity {

    static String KEY_EMAIL = "EMAIL";
    static String KEY_PASSWORD = "PASSWORD";

    String user1 = "mail1@mail.com";
    String user2 = "mail2@mail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        Bundle extras = getIntent().getExtras();

        String mail = extras.getString(KEY_EMAIL, "mail1@mail.com");
        String password = extras.getString(KEY_PASSWORD);

        Log.d("MASTER", "" + mail);
        Log.d("MASTER", "" + password);

        ImageView imgMaster = findViewById(R.id.imgAvatarMaster);

        int imageResource;

        switch (mail) {
            case "mail1@mail.com":
                imageResource = R.drawable.avatar_one;
                break;
            case "mail2@mail.com":
                imageResource = R.drawable.avatar_two;
                break;
            default:
                imageResource = R.drawable.ic_login_header;
        }

        imgMaster.setImageResource(imageResource);

//        if (mail != null) {
//            if (mail.equals(user1)) {
//                imgMaster.setImageResource(R.drawable.avatar_one);
//            } else if (mail.equals(user2)) {
//                imgMaster.setImageResource(R.drawable.avatar_two);
//            } else {
//                imgMaster.setImageResource(R.drawable.ic_login_header);
//            }
//        }

    }
}