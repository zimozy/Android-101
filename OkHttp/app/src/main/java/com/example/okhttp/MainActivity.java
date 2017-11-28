package com.example.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://api.github.com/users/zimozy/repos";

        new JSONAsyncTask(this).execute(url);

    }

    public void setValues(String dataString) throws JSONException {
        JSONArray everything = new JSONArray(dataString);
        JSONObject firstItem = everything.getJSONObject(0);
        JSONObject secondItem = everything.getJSONObject(1);
        JSONObject thirdItem = everything.getJSONObject(2);

        // This is a crude implementation (instaed of using a listview + adapter...
        TextView dataTextView = (TextView) findViewById(R.id.nameTV1);
        TextView fullNameTextView = (TextView) findViewById(R.id.fullNameTV1);
        TextView idTextView = (TextView) findViewById(R.id.idTV1);

        TextView dataTextView2 = (TextView) findViewById(R.id.nameTV2);
        TextView fullNameTextView2 = (TextView) findViewById(R.id.fullNameTV2);
        TextView idTextView2 = (TextView) findViewById(R.id.idTV2);

        TextView dataTextView3 = (TextView) findViewById(R.id.nameTV3);
        TextView fullNameTextView3 = (TextView) findViewById(R.id.fullNameTV3);
        TextView idTextView3 = (TextView) findViewById(R.id.idTV3);

        dataTextView.setText(firstItem.getString("name"));
        fullNameTextView.setText(firstItem.getString("full_name"));
        idTextView.setText("ID: " + firstItem.getString("id"));

        dataTextView2.setText(secondItem.getString("name"));
        fullNameTextView2.setText(secondItem.getString("full_name"));
        idTextView2.setText("ID: " + secondItem.getString("id"));

        dataTextView3.setText(thirdItem.getString("name"));
        fullNameTextView3.setText(thirdItem.getString("full_name"));
        idTextView3.setText("ID: " + thirdItem.getString("id"));
    }
}