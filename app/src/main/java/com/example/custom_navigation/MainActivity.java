package com.example.custom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationView;
    HomeFragment   homeFragment = new HomeFragment();
    SettingFragment settingFragment = new SettingFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id._navHome);
getSupportActionBar().hide();



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id._navHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();

                return true;

            case R.id.setting:

                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, settingFragment).commit();

                return true;

            case R.id.Notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, notificationFragment).commit();
                return true;
        }
        return false;
    }
}