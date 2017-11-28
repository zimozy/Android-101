package com.example.okhttp;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tim on 11/27/17.
 */

public class JSONAsyncTask extends AsyncTask {

    OkHttpClient okHttpClient;
    MainActivity callingClass;

    public JSONAsyncTask(MainActivity callingClass) {
        this.callingClass = callingClass;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        okHttpClient = new OkHttpClient();
        try {
            return run((String) params[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
//        Log.d("TIMO", (String) o);
        if (o != null) { // check string is not null
            try {
                callingClass.setValues((String) o);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    String run(String url) throws IOException, JSONException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String result = response.body().string();
        response.close();

        // Returns a string with JSON code. Parsed in MainActivity class
        return result;
    }

}
