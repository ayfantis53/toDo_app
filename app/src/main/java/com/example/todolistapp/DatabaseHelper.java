package com.example.todolistapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotesDatabase";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE Note( id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)";
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQLQuery = "DROP TABLE IF EXISTS Note";
        db.execSQL(SQLQuery);
        onCreate(db);

    }
}
