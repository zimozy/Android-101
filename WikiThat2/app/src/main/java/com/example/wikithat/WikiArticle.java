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

    private static final String LOG_TAG = "WikiArticle";

    //properties
    private String title;
    private String imageUrl;
    private String description;

    //constructor
    WikiArticle(final String urlString) {
        try {
            URL url = new URL("https://en.wikipedia.org/wiki/" + urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0"); //pretend to be Firefox so it gives us the desktop version of the site
            connection.connect();

            InputStream stream = connection.getInputStream();

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);

            try {

                parser.nextTag();//get the first tag

                while (parser.next() != XmlPullParser.END_DOCUMENT) { // loop for title

                    String id = parser.getAttributeValue(null, "id");

                    if (id != null && id.equals("firstHeading")) {

                        parser.next();
                        title = parser.getText(); //set title

                        while (parser.next() != XmlPullParser.END_DOCUMENT) { // loop for img src

                            if (parser.getEventType() == XmlPullParser.START_TAG
                                    && parser.getName().equals("img")) {

                                imageUrl = "https://"
                                            + parser.getAttributeValue(null, "src")
                                            .substring(2); //remove the first two slashes
                                Log.d(LOG_TAG, imageUrl);

                                while (parser.next() != XmlPullParser.END_DOCUMENT) { // loop for description

                                    if (parser.getEventType() == XmlPullParser.START_TAG // found firs <p>, now find the next one
                                            && parser.getName().equals("p")) {

                                        while (parser.next() != XmlPullParser.END_DOCUMENT) { //loop to the SECOND <p> element

                                            if (parser.getEventType() == XmlPullParser.START_TAG
                                                    && parser.getName().equals("p")) {

                                                description = ""; // just so it doesn't say "null"

                                                while (parser.next() != XmlPullParser.END_DOCUMENT) {

                                                    if (parser.getEventType() == XmlPullParser.TEXT) { // if it is a TEXT event

                                                        String text = parser.getText();

                                                        if (!text.matches("\\[.+?\\]")) { // remove foot notes
                                                            Log.d(LOG_TAG, text);
                                                            description += text; // add it to the description string
                                                        }
                                                    }

                                                    if (parser.getEventType() == XmlPullParser.START_TAG // if we get to the second paragraph
                                                            && parser.getName().equals("p")) {
                                                        //end the whole thing
                                                        return;
                                                    }

                                                }

                                            }

                                        }

                                    }

                                }

                            }

                        }

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

    public String getImageUrl() {
        return imageUrl;
    }
}
