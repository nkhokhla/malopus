package com.example.malopus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.chip.Chip;
import com.google.android.material.tabs.TabLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Tab2Fragment extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static String urlString = "https://uncomics.com/category/marvel-comics/feed/";
    private ProgressBar progressBar;
    protected View mView;
    Chip comicsChip;
    Chip charactersChip;
    DownloadXmlTask downloadXmlTask;
    DownloadXmlTask downloadXmlTask1;
    DownloadXmlTask downloadXmlTask2;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guides_fragment_marvel, container, false);
        comicsChip = view.findViewById(R.id.comicsChip);
        charactersChip = view.findViewById(R.id.charactersChip);
        listView = view.findViewById(R.id.listViewMarvel);
        progressBar =view.findViewById(R.id.progressBarMarvel);
        progressBar.setVisibility(View.VISIBLE);
        downloadXmlTask = new DownloadXmlTask();
        comicsChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                charactersChip.setChecked(false);
                comicsChip.setChecked(true);
                progressBar.setVisibility(View.VISIBLE);
                downloadXmlTask1 = new DownloadXmlTask();
                downloadXmlTask1.execute("https://uncomics.com/category/marvel-comics/feed/");
            }
        });
        charactersChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                charactersChip.setChecked(true);
                comicsChip.setChecked(false);
                progressBar.setVisibility(View.VISIBLE);
                downloadXmlTask2 = new DownloadXmlTask();
                downloadXmlTask2.execute("https://uncomics.com/category/marvel-characters/feed");

            }
        });


        downloadXmlTask.execute(urlString);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        // I have to inflate article_row to access its Views
        LayoutInflater inflater1 = this.getLayoutInflater();
        View articleRow = inflater1.inflate(R.layout.article_row, null);
        ImageView imageView = (ImageView) articleRow.findViewById(R.id.imageView);


        // Sample image to download and place in the imageView, not working right now
        new DownloadImageTask(imageView).execute("https://www.google.co.il/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");

        return view;
    }
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
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


            if (entries == null)
                return;

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
            if(xmlParser != null) Log.d("parser", "new parser");

            // what is a StringBuilder and why do I have to return it?
            StringBuilder htmlString = new StringBuilder();

            try {
                stream = downloadUrl(urlString);
                if(stream != null)
                    Log.d("stream", stream.toString() + "");

                // XmlParser returns a List (called "entries") of Item objects.
                // Each Entry object represents a single post in the XML feed.
                entries = xmlParser.parse(stream);
                if(entries != null)
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
}

