package com.example.malopus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

public class OnlineTab extends Fragment {
//    String[] mPlaceList;
    ImageAdapter adapter;
    Chip paidChip;
    ImageAdapter imageAdapter;
    Chip freeChip;
    String type = "paid";
    String[] myValues;
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState){
        return inflater.inflate(R.layout.onlinelibrary_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview1);
        paidChip=view.findViewById(R.id.paidChip);
        freeChip=view.findViewById(R.id.freeChip);
        paidChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paidChip.setChecked(true);
                freeChip.setChecked(false);
                type = "paid";
                MyApplication myAppClass = ((MyApplication)getActivity().getApplicationContext());
                myValues = myAppClass.getOnlineLinks(type);
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(mGridLayoutManager);
//        mPlaceList = new String[]{"https://www.onthebus.com.ua/wa-data/public/shop/products/04/webp/76/78/7876/images/37938/37938.0x450@2x.webp", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg"};
                imageAdapter = new ImageAdapter(getContext(), myAppClass.getOnlineLinks(type), type);
                mRecyclerView.setAdapter(imageAdapter);
            }
        });
        freeChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paidChip.setChecked(false);
                freeChip.setChecked(true);
                type = "free";
                MyApplication myAppClass = ((MyApplication)getActivity().getApplicationContext());
                myValues = myAppClass.getOnlineLinks(type);
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(mGridLayoutManager);
//        mPlaceList = new String[]{"https://www.onthebus.com.ua/wa-data/public/shop/products/04/webp/76/78/7876/images/37938/37938.0x450@2x.webp", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg"};
                imageAdapter = new ImageAdapter(getContext(), myAppClass.getOnlineLinks(type), type);
                mRecyclerView.setAdapter(imageAdapter);
            }
        });
        MyApplication myAppClass = ((MyApplication)getActivity().getApplicationContext());
        myValues = myAppClass.getOnlineLinks(type);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
//        mPlaceList = new String[]{"https://www.onthebus.com.ua/wa-data/public/shop/products/04/webp/76/78/7876/images/37938/37938.0x450@2x.webp", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg","http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg", "http://bookri.com.ua/wp-content/uploads/2019/11/sarkofah-cover.jpg"};
         imageAdapter = new ImageAdapter(getContext(), myAppClass.getOnlineLinks(type), type);
        mRecyclerView.setAdapter(imageAdapter);
    }
}
