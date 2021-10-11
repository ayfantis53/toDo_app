package com.example.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NoteHandler extends DatabaseHelper {
    public NoteHandler(@Nullable Context context) {
        super(context);
    }
    //C R U D

    public boolean Create(Note note){
        ContentValues values = new ContentValues();

        values.put("title", note.getName());
        values.put("description", note.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccessful = db.insert("note", null, values) > 0;
        db.close();
        return isSuccessful;
    }

    public ArrayList<Note> readNotes(){
        ArrayList<Note> notes = new ArrayList<>();

        String SQLQuery = "SELECT * FROM Note ORDER BY id ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQLQuery, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                Note note = new Note(title, description);
                note.setId(id);
                notes.add(note);
            }while(cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return notes;
    }

    public Note readSingleNote(int id){
        Note note = null;
        String SQLQuery = "SELECT * FROM Note WHERE id =" +id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQLQuery, null);

        if(cursor.moveToFirst()){
            int idNote = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String name = cursor.getString(cursor.getColumnIndex("title"));
            String description = cursor.getString(cursor.getColumnIndex("description"));

            note = new Note(name, description);
            note.setId(idNote);
        }
        cursor.close();
        db.close();
        return note;
    }

    public boolean update(Note note){
        ContentValues values = new ContentValues();

        values.put("title", note.getName());
        values.put("description", note.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccessful = db.update("Note",values, "id =' " + note.getId() + "'" ,null) > 0;
        db.close();
        return isSuccessful;
    }

    public boolean delete(int id){
        boolean isDeleted;
        SQLiteDatabase db = this.getWritableDatabase();
        isDeleted = db.delete("Note","id =' " + id + "'" , null) > 0;
        db.close();
        return isDeleted;
    }
}

