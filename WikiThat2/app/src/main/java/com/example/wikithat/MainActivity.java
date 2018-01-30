package com.example.wikithat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView title, description;
    ImageView image;
    AutoCompleteTextView search;
    Button goButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title       = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        image       = (ImageView) findViewById(R.id.image);

        search      = (AutoCompleteTextView) findViewById(R.id.search);
        goButton    = (Button) findViewById(R.id.go);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        final WikiArticle wikiArticle = new WikiArticle(search.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                title.setText(wikiArticle.getTitle());
                                description.setText((wikiArticle.getDescription()));
                            }
                        });

                        try {

                            final Bitmap bitmap =
                                    BitmapFactory.decodeStream(
                                            (InputStream) new URL(wikiArticle.getImageUrl())
                                            .getContent()
                                    );

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    image.setImageBitmap(bitmap);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                search.clearFocus(); //hides the keyboard
                thread.start();
            }
        });

    }
}
