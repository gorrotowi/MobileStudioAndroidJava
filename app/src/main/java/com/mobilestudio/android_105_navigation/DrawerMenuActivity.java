package com.mobilestudio.android_105_navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.mobilestudio.android_105_navigation.databinding.ActivityDrawerMenuBinding;

public class DrawerMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityDrawerMenuBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrawerMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureNavigation();
        configureDrawer();

        binding.txtHeaderName.setText("My username");
        binding.txtHeaderId.setText("ID: #320439");

    }

    private void configureNavigation() {
        binding.toolbar.setTitle("My app");
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragmentDrawer);
        navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout);
        NavigationUI.setupWithNavController(binding.navigationDrawer, navController);
        binding.navigationDrawer.setNavigationItemSelectedListener(this);
    }

    private void configureDrawer() {
        DrawerLayout drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.drawe_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        SwitchCompat switchAction = item.getActionView().findViewById(R.id.swicthAction);

        if (item.getItemId() == R.id.homeFragment) {
            binding.drawerLayout.closeDrawers();
            navController.navigate(R.id.homeFragment);
        }

        if (item.getItemId() == R.id.searchFragment) {
            binding.drawerLayout.closeDrawers();
            navController.navigate(R.id.searchFragment);
        }

        if (item.getItemId() == R.id.configFragment) {
            binding.drawerLayout.closeDrawers();
            Intent intentToTermsAndConditions = new Intent(Intent.ACTION_VIEW, Uri.parse("https://es-la.facebook.com/"));
            startActivity(intentToTermsAndConditions);
        }

        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
//        return super.onSupportNavigateUp();
        return NavigationUI.navigateUp(navController, binding.drawerLayout);
    }
}