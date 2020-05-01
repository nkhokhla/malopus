package com.example.malopus;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class LibraryFragment extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_layout, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerLibrary);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutLibrary);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#6200EA"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#6200EA"));
        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new ShopsTab(), getString(R.string.shops));
        adapter.addFragment(new OnlineTab(), getString(R.string.online));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
