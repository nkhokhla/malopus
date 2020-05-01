package com.example.malopus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;

public class MainFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private MyPagerAdapter myPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        myPager = new MyPagerAdapter(getContext());
        viewPager = view.findViewById(R.id.vpHome);
        viewPager.setAdapter(myPager);
        circleIndicator = view.findViewById(R.id.circle);
        circleIndicator.setViewPager(viewPager);
        return view;
    }
}
