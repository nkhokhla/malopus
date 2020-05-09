package com.example.malopus;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import me.relex.circleindicator.CircleIndicator;

public class MainFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private MyPagerAdapter myPager;
    LinearLayout gallery;
    private File root;
    private ArrayList fileList = new ArrayList<>();
    GridView simpleList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fileList.clear();
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        viewPager = view.findViewById(R.id.vpHome);
        circleIndicator = view.findViewById(R.id.circle);
        gallery = view.findViewById(R.id.gallery);
        gallery.setVisibility(View.GONE);
        requestPermissions(new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        root = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath());
        try {
            getfile(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        simpleList = (GridView) view.findViewById(R.id.gridviewReader);
        Adapter myAdapter=new Adapter(getContext(),R.layout.grid_view_items,fileList);
        simpleList.setAdapter(myAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComicItem comic = (ComicItem) fileList.get(position);

                Intent intent = new Intent(getActivity(), Open_Comics.class);
                intent.putExtra("dir",comic.getentries() );
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication myAppClass =((MyApplication)getActivity().getApplicationContext());
        if (myAppClass.isEmpty()){
            gallery.setVisibility(View.GONE);
        } else{
            gallery.setVisibility(View.VISIBLE);
        }
        myPager = new MyPagerAdapter(getContext(), myAppClass.getLastViewed(), myAppClass.getOnlineLinks("free"));
        viewPager.setAdapter(myPager);

        circleIndicator.setViewPager(viewPager);
    }
    public ArrayList<File> getfile(File dir) throws IOException {
        File[] listFile = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {

                    getfile(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(".cbz"))

                    {
                        ZipFile zipFile = new ZipFile(listFile[i].getAbsolutePath());
                        Enumeration<? extends ZipEntry> entries = zipFile.entries();
                        Bitmap photo = null;
                        ArrayList<ZipEntry> entryList = new ArrayList<ZipEntry>();
                        ArrayList<String> shortish = new ArrayList<String>();
                        while(entries.hasMoreElements()) {

                            ZipEntry entry = entries.nextElement();
                            if(entry.getName().endsWith(".png")||entry.getName().endsWith(".jpg")){

                                entryList.add(entry);
                                shortish.add(entry.getName());
                            }

                        }
                        int smallestIndex = 0;
                        for (int y = 1; y < shortish.size(); y++) {
                            int compare = shortish.get(y).compareTo(shortish.get(smallestIndex));
                            if (compare < 0)
                                smallestIndex = y;
                        }
                        photo = BitmapFactory.decodeStream( zipFile.getInputStream(entryList.get(smallestIndex)));


                        fileList.add(new ComicItem(listFile[i].getName(),photo,listFile[i].getAbsolutePath()));
                        zipFile.close();
                    }
                }

            }
        }
        return fileList;
    }

}
