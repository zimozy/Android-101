package com.example.datastorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        dbHelper.insertStudent("Tim", "1234567");
        dbHelper.insertStudent("Sarah", "9876543");
        dbHelper.insertStudent("David", "1928374");

        dbHelper.getAllStudents();
    }
}
