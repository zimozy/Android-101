package com.example.jsonandrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems =  new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            String string = "Lorem";
            for (int j = 0 ; j < i; j++) {
                string.concat(" lorem");
            }

            ListItem listItem = new ListItem(
                    "Heading " + (i+1),
                    string,
                    "example.com/image.jpg"
            );

            listItems.add(listItem);
        }

        adapter = new MyAdapter(listItems);

        recyclerView.setAdapter(adapter);
    }
}
