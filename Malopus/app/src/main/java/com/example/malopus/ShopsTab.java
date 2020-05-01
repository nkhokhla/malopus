package com.example.malopus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ShopsTab extends Fragment {
    String[] mPlaceList;
    ImageAdapter adapter;
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState){
        return inflater.inflate(R.layout.shops_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 3,  GridLayoutManager.VERTICAL,   false);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        MyApplication myAppClass = ((MyApplication)getActivity().getApplicationContext());
        mPlaceList = new String[]{"https://www.onthebus.com.ua/wa-data/public/shop/products/00/webp/57/48/4857/images/13535/13535.0x970.webp", "https://www.onthebus.com.ua/wa-data/public/shop/products/00/webp/17/82/8217/images/32771/32771.0x970.webp","https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg","https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg","https://geekach.com.ua/content/images/37/83838857265837.jpg", "https://geekach.com.ua/content/images/37/83838857265837.jpg"};
        ImageAdapter imageAdapter = new ImageAdapter(getContext(), myAppClass.getShopsLinks(),"shops");
        mRecyclerView.setAdapter(imageAdapter);
    }
} 
