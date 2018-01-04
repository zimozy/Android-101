package com.example.wikithat;

/**
 * Created by tim on 1/4/18.
 */

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikiArticle {

    private static final String TAG = "WikiArticle";
    private String title;
    private String imageUrl;
    private String description;


    //constructor
    WikiArticle(final String urlString) {
        try {
            Log.d(TAG, "running");

            URL url = new URL("https://en.wikipedia.org/wiki/" + urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0"); //pretend to be Firefox so it gives us the desktop version of the site
            connection.connect();

            InputStream stream = connection.getInputStream();

//            java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
//            description = s.hasNext() ? s.next() : "";
////            Log.d(TAG, string);


            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

            Log.d(TAG, "ENCODING: " + parser.getInputEncoding());

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);

            parser.nextTag();//get the first tag

//                    int event;
//                    String text = null;

            try {

                while (parser.next() != XmlPullParser.END_DOCUMENT) {

                    Log.d(TAG, "POSITION: " + parser.getPositionDescription());

                    String id = parser.getAttributeValue(null, "id");

                    if (id == null) continue;

                    switch (id) {
                        case "firstHeading":
                            Log.d(TAG, "first heading: " + id);
                            parser.next();
                            title = parser.getText();
                            Log.d(TAG, title);
                            break;
                        default:
                            Log.d(TAG, "default switch: " + id);
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


}
