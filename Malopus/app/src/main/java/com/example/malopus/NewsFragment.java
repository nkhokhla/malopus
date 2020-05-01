package com.example.malopus;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsFragment extends Fragment {
    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static String urlString = "https://uageek.space/feed/";
    private static String urlStringTg = "https://rsshub.app/telegram/channel/leftthemusicout/";
    private ProgressBar progressBar;
    DownloadXmlTask downloadXmlTask;
    DownloadXmlTask downloadXmlTask1;
    ListView listView;
    List<Item> toReturn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_layout, container, false);
//        Intent intent = new Intent(getActivity(), NetworkActivity.class);
//        startActivity(intent);
        progressBar =view.findViewById(R.id.progressBar);
        listView = view.findViewById(R.id.listView);
        progressBar.setVisibility(View.VISIBLE);
        toReturn = new ArrayList<Item>();
        downloadXmlTask = new DownloadXmlTask();
        downloadXmlTask1 = new DownloadXmlTask();
        downloadXmlTask.execute();
        downloadXmlTask1.execute();
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

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Network Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.studio08.kolhazmannewsapp/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }

//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Network Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.studio08.kolhazmannewsapp/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }

    private class DownloadXmlTask extends AsyncTask<String, Void, String> {
//        public DownloadXmlTask(List<Item> newList) {
//            toReturn.addAll(entries);
//            toReturn.addAll(newList);
//        }


        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadXmlFromNetwork(urls[0]);
            } catch (IOException e) {
                return getResources().getString(R.string.connection_error);
            } catch (XmlPullParserException e) {
                return getResources().getString(R.string.xml_error);
            } catch (ParseException e){
                return "parse exeption";
            }
        }

        @Override
        protected void onPostExecute(String result) {


            if(entries == null)
                return;
            Collections.sort(toReturn, new CustomComparator());
            NewsAdapter newsAdapter = new NewsAdapter(getActivity(), toReturn);
            listView.setAdapter(newsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(NetworkActivity.this,ArticleActivity.class);
//                    intent.putExtra("link",entries.get(position).link);
//                    startActivity(intent);
                    Uri address = Uri.parse(toReturn.get(position).link);
                    Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                    startActivity(openlinkIntent);
                }
            });
            progressBar.setVisibility(View.GONE);

        }

        List<Item> entries= new ArrayList<Item>();

        // Uploads XML from kolhazman.co.il/feed, parses it, and combines it with
        // HTML markup. Returns HTML string.
        private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException, ParseException {
            InputStream stream = null;
            InputStream streamTwo = null;
            // Instantiate the parser
            XmlParser xmlParser = new XmlParser();
            GuidesXmlParser secondParser = new GuidesXmlParser();
            if(xmlParser != null) Log.d("parser", "new parser");

            // what is a StringBuilder and why do I have to return it?
            StringBuilder htmlString = new StringBuilder();

            try {
                stream = downloadUrl(urlString);
                streamTwo = downloadUrl(urlStringTg);
                if(stream != null)
                    Log.d("stream", stream.toString() + "");

                // XmlParser returns a List (called "entries") of Item objects.
                // Each Entry object represents a single post in the XML feed.
                toReturn.addAll(xmlParser.parse(stream));
                toReturn.addAll(secondParser.parse(streamTwo));
                if(entries != null)
                    Log.d("entries", "size: " + entries.size() + "");

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
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
