package com.mobilestudio.androidjava101;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button btnSayHello = (Button) findViewById(R.id.btnSayHello);
//        TextView txtHello = findViewById(R.id.txtHelloAndroid);
//
//        btnSayHello.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                txtHello.setText("Hola Androides Java en su clase 101");
////                txtHello.setText(R.string.otro_saludo);
//                txtHello.setText(R.string.saludo);
//            }
//        });


//        ImageView imgAvatar = findViewById(R.id.imgAvatar);

//        imgAvatar.setOnClickListener(v -> {
//            if (state) {
//                imgAvatar.setImageResource(R.drawable.avatar_one);
//                state = false;
//            } else {
//                imgAvatar.setImageResource(R.drawable.avatar_two);
//                state = true;
//            }
//        });

        EditText edtxtLoginMail = findViewById(R.id.edtxtLoginMail);
        EditText edtxtLoginPassword = findViewById(R.id.edtxtLoginPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String mail = edtxtLoginMail.getText().toString();
            String password = edtxtLoginPassword.getText().toString();

            if (mail.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ingresa tu mail o tu contraseña", Toast.LENGTH_SHORT).show();
            } else {
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
                boolean isValidMail = matcher.find();
                if (isValidMail) {
//                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MasterActivity.class);
                    intent.putExtra(MasterActivity.KEY_EMAIL, mail);
                    intent.putExtra(MasterActivity.KEY_PASSWORD, password);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Este no es un correo valido", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}