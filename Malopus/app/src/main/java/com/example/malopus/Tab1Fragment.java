package com.example.malopus;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Tab1Fragment extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView imageView;

    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static String urlString = "https://uncomics.com/category/dc-comics/feed/";
    private ProgressBar progressBar;

    Chip comicsChip;
    Chip charactersChip;
    ListView listView;
    AutoCompleteTextView editTextFilledExposedDropdown;
    DownloadXmlTask downloadXmlTask;
    DownloadXmlTask downloadXmlTask1;
    DownloadXmlTask downloadXmlTask2;
    Chip batmanChip;
    Chip supermanChip;
    ChipGroup subChips;
    DownloadXmlTask downloadXmlTask3;
    DownloadXmlTask downloadXmlTask4;
        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState){
            return inflater.inflate(R.layout.guides_fragment_dc, container, false);
        }

        private Context mContext;
        @Override
        public void onAttach (Context context){
            super.onAttach(context);
            mContext = context;
        }

        @Override
        public void onDetach () {
            super.onDetach();
            mContext = null;
        }

        private class DownloadXmlTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... urls) {
                try {
                    return loadXmlFromNetwork(urls[0]);
                } catch (IOException e) {
                    return getResources().getString(R.string.connection_error);
                } catch (XmlPullParserException e) {
                    return getResources().getString(R.string.xml_error);
                }
            }
            @Override
            protected void onPostExecute(String result) {


                if (entries == null) {
                    return;
                }
//            ListView listView = mView.findViewById(R.id.listView);
                GuidesAdapter guidesAdapter = new GuidesAdapter(getActivity(), entries);
                listView.setAdapter(guidesAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(NetworkActivity.this,ArticleActivity.class);
//                    intent.putExtra("link",entries.get(position).link);
//                    startActivity(intent);
                        Uri address = Uri.parse(entries.get(position).link);
                        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                        startActivity(openlinkIntent);
                    }
                });
                progressBar.setVisibility(View.GONE);

            }

            List<Item> entries;

            // Uploads XML from kolhazman.co.il/feed, parses it, and combines it with
            // HTML markup. Returns HTML string.
            private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
                InputStream stream = null;
                // Instantiate the parser
                GuidesXmlParser xmlParser = new GuidesXmlParser();
                if (xmlParser != null) Log.d("parser", "new parser");

                // what is a StringBuilder and why do I have to return it?
                StringBuilder htmlString = new StringBuilder();

                try {
                    stream = downloadUrl(urlString);
                    if (stream != null)
                        Log.d("stream", stream.toString() + "");

                    // XmlParser returns a List (called "entries") of Item objects.
                    // Each Entry object represents a single post in the XML feed.
                    entries = xmlParser.parse(stream);
                    if (entries != null)
                        Log.d("entries", "size: " + entries.size() + "");

                    // Makes sure that the InputStream is closed after the app is
                    // finished using it.
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    if (stream != null)
                        stream.close();
                }

                return htmlString.toString();
            }

            // Given a string representation of a URL, sets up a connection and gets an input stream.

            private InputStream downloadUrl(String urlString) throws IOException {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000 /* milliseconds */);
                connection.setConnectTimeout(15000 /* milliseconds */);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                // Starts the query
                connection.connect();
                return connection.getInputStream();
            }
        }
        private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
            ImageView imageView;

            public DownloadImageTask(ImageView imageView) {
                this.imageView = imageView;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap bitmap = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    bitmap = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return bitmap;
            }

            protected void onPostExecute(Bitmap result) {
                imageView.setImageBitmap(result);
            }

        }

        @Override
        public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            listView = view.findViewById(R.id.listViewDC);
            comicsChip = view.findViewById(R.id.comicsChipDC);
            charactersChip = view.findViewById(R.id.charactersChipDC);
//            textInputLayout = view.findViewById(R.id.text_input_layout);
//            editTextFilledExposedDropdown = view.findViewById(R.id.filled_exposed_dropdown);
            progressBar = view.findViewById(R.id.progressBarDC);
            comicsChip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    charactersChip.setChecked(false);
                    comicsChip.setChecked(true);
                    progressBar.setVisibility(View.VISIBLE);
                    downloadXmlTask1 = new DownloadXmlTask();
                    downloadXmlTask1.execute("https://uncomics.com/category/dc-comics/feed/");
                }
            });
            charactersChip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    charactersChip.setChecked(true);
                    comicsChip.setChecked(false);
                    progressBar.setVisibility(View.VISIBLE);
                    downloadXmlTask2 = new DownloadXmlTask();
                    downloadXmlTask2.execute("https://uncomics.com/category/dc-characters/feed/");

                }
            });


//            editTextFilledExposedDropdown.setKeyListener(null);
//            String[] COUNTRIES = new String[]{getString(R.string.batman), getString(R.string.superman)};
//            if (mContext != null) {
//                KArrayAdapter<String> adapter1 =
//                        new KArrayAdapter<>(
//                                mContext,
//                                R.layout.dropdown_menu_popup_item,
//                                Arrays.asList(COUNTRIES));
//                    editTextFilledExposedDropdown.setAdapter(adapter1);
//            }
//            editTextFilledExposedDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if(parent.getItemAtPosition(position).equals(getString(R.string.batman))){
//                            progressBar.setVisibility(View.VISIBLE);
//                            downloadXmlTask3 = new DownloadXmlTask();
//                            downloadXmlTask3.execute("https://uncomics.com/category/dc-comics/the-batman/feed/");
//                        }
//                    if(parent.getItemAtPosition(position).equals(getString(R.string.superman))){
//                        progressBar.setVisibility(View.VISIBLE);
//                        downloadXmlTask4 = new DownloadXmlTask();
//                        downloadXmlTask4.execute("https://uncomics.com/category/dc-comics/superman/feed/");
//                    }
//
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });
            progressBar.setVisibility(View.VISIBLE);
            downloadXmlTask = new DownloadXmlTask();
            downloadXmlTask.execute(urlString);
            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

            // I have to inflate article_row to access its Views
//        LayoutInflater inflater1 = this.getLayoutInflater();
//        View articleRow = inflater1.inflate(R.layout.article_row, null);
//        imageView = (ImageView) articleRow.findViewById(R.id.imageView);


            // Sample image to download and place in the imageView, not working right now
            // new DownloadImageTask(imageView).execute("https://www.google.co.il/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        }
}

