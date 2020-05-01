package com.example.malopus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.malopus.R;
import com.squareup.picasso.Picasso;

public class ImageAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    private Context mContext;
    public  String[] mPlaceList;
    String type;

    public ImageAdapter(Context mContext, String[] mPlaceList, String type) {
        this.mContext = mContext;
        this.mPlaceList = mPlaceList;
        this.type = type;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_custom_layout,
                parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlaceViewHolder holder, int position) {
//        holder.mPlace.setImageResource(mPlaceList[position]);
        Picasso.get()
                .load(mPlaceList[position])
                .fit()
                .centerInside()
                .into(holder.mPlace);
        holder.mPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("Image", mPlaceList[holder.getAdapterPosition()]);
                mIntent.putExtra("Type", type);
                mIntent.putExtra("Position", holder.getAdapterPosition());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlaceList.length;
    }
}

class PlaceViewHolder extends RecyclerView.ViewHolder {

    ImageView mPlace;

    public PlaceViewHolder(View itemView) {
        super(itemView);

        mPlace = itemView.findViewById(R.id.ivPlace);
    }
}
