package com.mobilestudio.recyclerviewandstyles;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcMain;
    AdapterMain adapterMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapterMain = new AdapterMain();
        rcMain = findViewById(R.id.rcMain);

//        rcMain.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rcMain.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));

        rcMain.setAdapter(adapterMain);

    }
}