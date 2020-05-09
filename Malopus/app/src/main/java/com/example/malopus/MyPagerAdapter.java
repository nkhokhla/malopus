package com.example.malopus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;


public class MyPagerAdapter extends PagerAdapter  {
    ArrayList<Integer> arrlist = new ArrayList<Integer>();
    String[] links;

    private Context context;
    public MyPagerAdapter(Context context, ArrayList<Integer> arrlist, String[] links) {
        this.context = context;
        this.arrlist = arrlist;
        this.links = links;
    }
    /*
    This callback is responsible for creating a page. We inflate the layout and set the drawable
    to the ImageView based on the position. In the end we add the inflated layout to the parent
    container .This method returns an object key to identify the page view, but in this example page view
    itself acts as the object key
    */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, null);
        ImageView imageView = view.findViewById(R.id.image);
        LinearLayout gallery = (LinearLayout) view.findViewById(R.id.gallery);

        Picasso.get()
                .load(links[arrlist.get(position)])
                .fit()
                .centerInside()
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, DetailActivity.class);
                mIntent.putExtra("Image", links[arrlist.get(position)]);
                mIntent.putExtra("Type", "free");
                mIntent.putExtra("Position", arrlist.get(position));
                context.startActivity(mIntent);
            }
        });
        container.addView(view);
        return view;
    }
    /*
    This callback is responsible for destroying a page. Since we are using view only as the
    object key we just directly remove the view from parent container
    */
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }
    /*
    Returns the count of the total pages
    */
    @Override
    public int getCount() {
        return arrlist.size();
    }
    /*
    Used to determine whether the page view is associated with object key returned by instantiateItem.
    Since here view only is the key we return view==object
    */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }
}
