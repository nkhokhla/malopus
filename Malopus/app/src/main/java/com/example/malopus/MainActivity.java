package com.example.malopus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
//    BottomNavigationView bottomNavigationView;
//    private int mMenuId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
//        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
//        bottomNavigationView.setOnNavigationItemSelectedListener(
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        mMenuId = item.getItemId();
//                        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
//                            MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
//                            boolean isChecked = menuItem.getItemId() == item.getItemId();
//                            menuItem.setChecked(isChecked);
//                        }
//                        switch (item.getItemId()) {
//                            case R.id.home:
//
//                            case R.id.library:
//                                Intent intent = new Intent(MainActivity.this, Library.class);
//                                startActivity(intent);
//                            case R.id.news:
//                                Intent intent1 = new Intent(MainActivity.this, News.class);
//                                startActivity(intent1);
//                            case R.id.guides:
//                                Intent intent2 = new Intent(MainActivity.this, Guides.class);
//                                startActivity(intent2);
//                        }
//                        return true;
//                    }
//                });

    }

}
