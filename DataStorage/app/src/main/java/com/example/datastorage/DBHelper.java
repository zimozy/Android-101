package com.example.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tim on 12/4/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "STUDENT";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "STUDENT_TABLE";
    private static final String ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_PHONE = "PHONE";

    SQLiteDatabase mSQLiteDatabase;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " + COL_NAME +" TEXT, " + COL_PHONE + " TEXT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertStudent(String name, String phone) {
        mSQLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PHONE, phone);

        mSQLiteDatabase.insert(TABLE_NAME, null, contentValues);
        mSQLiteDatabase.close();
    }

    public void getAllStudents() {
        mSQLiteDatabase = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = mSQLiteDatabase.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);

                Log.e("ID", String.valueOf(id));
                Log.e("NAME", name);
                Log.e("PHONE_NUMBER", phone);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mSQLiteDatabase.close();
    }
}
