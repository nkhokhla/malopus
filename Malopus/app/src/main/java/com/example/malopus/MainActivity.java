package com.example.malopus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    final Fragment news_fragment = new NewsFragment();
    final Fragment library_fragment = new LibraryFragment();
    final Fragment guides_fragment = new GuidesFragment();
    final Fragment main_fragment = new MainFragment();
    Fragment active = main_fragment;
    final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);
        fm.beginTransaction().replace(R.id.main_layout, main_fragment).commit();
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ImageView icon = findViewById(R.id.icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.home:
                    fm.beginTransaction().replace(R.id.main_layout, main_fragment).commit();
                    //  active = main_fragment;
                    bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                    return true;

                case R.id.library:
                    fm.beginTransaction().replace(R.id.main_layout, library_fragment).commit();
                    bottomNavigationView.getMenu().findItem(R.id.library).setChecked(true);
                    return true;

                case R.id.news:
                   fm.beginTransaction().replace(R.id.main_layout, news_fragment).commit();
                    bottomNavigationView.getMenu().findItem(R.id.news).setChecked(true);
                    return true;
                case R.id.guides:
                    fm.beginTransaction().replace(R.id.main_layout, guides_fragment).commit();
                    bottomNavigationView.getMenu().findItem(R.id.guides).setChecked(true);
                    return true;

            }
            return false;
        }
    };



}
