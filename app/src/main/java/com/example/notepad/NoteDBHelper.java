package com.example.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NoteDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyNoteDatabase.db";

    public NoteDBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + AddNote.TABLE_NAME
                        + "(" + AddNote.COLUMN_ID + " INTEGER PRIMARY KEY, "
                        +  AddNote.COLUMN_NOTE + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AddNote.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact(AddNote addnote){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AddNote.COLUMN_NOTE, addnote.getNote());
        db.insert(AddNote.TABLE_NAME, null, values);
        return true;
    }

    public ArrayList<AddNote> getAllNote(){
        ArrayList<AddNote> noteArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + AddNote.TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()){
            AddNote addnote = new AddNote();
            addnote.setId(res.getInt(res.getColumnIndex(AddNote.COLUMN_ID)));
            addnote.setNote(res.getString(res.getColumnIndex(AddNote.COLUMN_NOTE)));
            noteArrayList.add(addnote);
            res.moveToNext();
        }
        res.close();
        return noteArrayList;
    }
}
