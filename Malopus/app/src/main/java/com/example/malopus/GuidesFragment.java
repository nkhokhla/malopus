package com.example.malopus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.chip.Chip;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GuidesFragment extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static String urlString = "https://uncomics.com/category/dc-comics/the-batman/feed/";
    private ProgressBar progressBar;
    protected View mView;
    Chip comicsChip;
    Chip charactersChip;
    TextInputLayout textInputLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guides_layout, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#6200EA"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#6200EA"));
        this.mView = view;
        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "DC");
        adapter.addFragment(new Tab2Fragment(), "Marvel");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}





