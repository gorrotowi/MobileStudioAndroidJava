package com.mobilestudio.recyclerviewandstyles;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcMain;
    Button btnAddItem;
    AdapterMain adapterMain;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcMain = findViewById(R.id.rcMain);
        btnAddItem = findViewById(R.id.btnAddItem);



        adapterMain = new AdapterMain(getDataSource());

//        rcMain.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rcMain.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));

        rcMain.setAdapter(adapterMain);

        btnAddItem.setOnClickListener(v -> {
            adapterMain.addItem(new Contact(R.drawable.ic_tent, "New Item"));
        });

        adapterMain.addOnItemTap((position, data) -> {
            adapterMain.removeItem(position);
            Intent selfIntent = new Intent(this, MainActivity.class);
            startActivity(selfIntent);
//            Toast.makeText(
//                    this,
//                    "Item removed position " + position + "\n" + data,
//                    Toast.LENGTH_SHORT)
//                    .show();
        });

    }

    private List<Contact> getDataSource() {
        List<Contact> fakeData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0) {
                fakeData.add(new Contact(R.drawable.ic_alien, "Item " + i));
            } else {
                fakeData.add(new Contact(R.drawable.ic_globe, "Item " + i));
            }
        }
        return fakeData;
    }
}