package com.mobilestudio.android_105_navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mobilestudio.android_105_navigation.adapters.ViewPagerMainAdapter;
import com.mobilestudio.android_105_navigation.fragments.BaseFragment;
import com.mobilestudio.android_105_navigation.fragments.FirstFragment;
import com.mobilestudio.android_105_navigation.fragments.ListMailFragment;
import com.mobilestudio.android_105_navigation.fragments.SecondFragment;
import com.mobilestudio.android_105_navigation.fragments.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<BaseFragment> fragmentList = new ArrayList<>();

        ListMailFragment listMailFragment = new ListMailFragment();

        fragmentList.add(new FirstFragment());
        fragmentList.add(listMailFragment);
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());


        ViewPagerMainAdapter vpAdapter = new ViewPagerMainAdapter(getSupportFragmentManager(), fragmentList);

        ViewPager vpMain = findViewById(R.id.vpMain);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        vpMain.setAdapter(vpAdapter);

        tabLayout.setupWithViewPager(vpMain);

        listMailFragment.setOnMailListener(mail -> {
            Log.d("Mail", "FROM MAIN");
        });

        for (int positionTab = 0; positionTab < tabLayout.getTabCount(); positionTab++) {
            TabLayout.Tab tab = tabLayout.getTabAt(positionTab);
            if (tab != null) {
                tab.setCustomView(R.layout.tab_custom);
                View tabView = tab.getCustomView();
                if (tabView != null) {
                    TextView txtTitle = tabView.findViewById(R.id.txtTabTitle);
                    ImageView imgTab = tabView.findViewById(R.id.imgTab);
                    txtTitle.setText(fragmentList.get(positionTab).getName());
                    if (positionTab % 2 == 0) {
                        imgTab.setImageResource(R.drawable.ic_tab_alien);
                    }
                }
            }
        }


    }
}