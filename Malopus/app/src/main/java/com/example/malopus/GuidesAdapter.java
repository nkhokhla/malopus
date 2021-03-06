package com.example.malopus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GuidesAdapter extends BaseAdapter {

    List<Item> items;
    Context context;

    public GuidesAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = null;
        Item current = (Item) getItem(position);
        Holder holder;

        if (convertView == null) {
            holder = new Holder();
            row = inflater.inflate(R.layout.article_row, null);
            holder.titleTextView = (TextView) row.findViewById(R.id.titleTextView);
            holder.descriptionTextView = (TextView) row.findViewById(R.id.descriptionTextView);
            holder.featuredImage = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);
        } else {
            row = convertView;
            holder = (Holder) row.getTag();
        }

        holder.titleTextView.setText(current.title);
        holder.descriptionTextView.setText(current.summary);
//        Picasso.get().load(current.imageLink).into(holder.featuredImage);

        return row;
    }

    private class Holder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView featuredImage;
    }
}
