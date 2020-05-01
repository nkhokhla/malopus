package com.example.malopus;

import android.content.Context;
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
    ArrayList<String> types1 = new ArrayList<String>();;

    private Context context;
    public MyPagerAdapter(Context context) {
        this.context = context;

    }
    /*
    This callback is responsible for creating a page. We inflate the layout and set the drawable
    to the ImageView based on the position. In the end we add the inflated layout to the parent
    container .This method returns an object key to identify the page view, but in this example page view
    itself acts as the object key
    */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TinyDB tinydb = new TinyDB(context);
        arrlist = tinydb.getListInt("MyUsers");
        types1 = tinydb.getListString("Types");
        Collections.reverse(arrlist);
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, null);
        ImageView imageView = view.findViewById(R.id.image);
        LinearLayout gallery = (LinearLayout) view.findViewById(R.id.gallery);
        MyApplication myAppClass =(MyApplication) context;
        if (types1.get(position).equals("online")){
        Picasso.get()
                .load(myAppClass.getOnlineLink(arrlist.get(position)))
                .fit()
                .centerInside()
                .into(imageView);
        } else if (types1.get(position).equals("shops")){
            Picasso.get()
                    .load(myAppClass.getShopLink(arrlist.get(position)))
                    .fit()
                    .centerInside()
                    .into(imageView);
        } else{
            gallery.setVisibility(View.GONE);
        }
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
