package com.example.malopus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

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
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        fm.beginTransaction().add(R.id.main_layout, guides_fragment, "4").hide(guides_fragment).commit();
//        fm.beginTransaction().add(R.id.main_layout, news_fragment, "3").hide(news_fragment).commit();
//        fm.beginTransaction().add(R.id.main_layout, library_fragment, "2").hide(library_fragment).commit();
//        fm.beginTransaction().add(R.id.main_layout,main_fragment, "1").commit();
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////                        mMenuId = item.getItemId();
//                        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
//                            MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
//                            boolean isChecked = menuItem.getItemId() == item.getItemId();
//                            menuItem.setChecked(isChecked);
//                        }
//                        switch (item.getItemId()) {
//                            case R.id.home:
//
//                            case R.id.library:
//                                fm.beginTransaction().replace(R.id.main_layout,library_fragment).commit();
//                            case R.id.news:
//                                fm.beginTransaction().replace(R.id.main_layout,news_fragment).commit();
//                            case R.id.guides:
//                                fm.beginTransaction().replace(R.id.main_layout,guides_fragment).commit();
//                        }
//                        return true;
//                    }
//                });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            mMenuId = item.getItemId();

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
