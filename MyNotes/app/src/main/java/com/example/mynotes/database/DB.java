package com.example.mynotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB {
    private static final String DB_NAME = "piis";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "notes";
    private final Context mCtx;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context ctx) {
        mCtx = ctx;
    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }
    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }

    public Cursor getAll() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }
    public void add(String text) {
        ContentValues cv = new ContentValues();
        cv.put("text", text);
        mDB.insert(DB_TABLE, null, cv);
    }
    public void update(long id, String text) {
        ContentValues cv = new ContentValues();
        cv.put("text", text);
        int rowsAffected = mDB.update(DB_TABLE, cv, "id = " + id, null);
        if (rowsAffected == 0) throw new IllegalArgumentException("Note with this ID not found");
    }
    public void delete(long id) {
        int rowsAffected = mDB.delete(DB_TABLE, "id = " + id, null);
        if (rowsAffected == 0) throw new IllegalArgumentException("Note with this ID not found");
    }
    public void deleteAll() {
        mDB.delete(DB_TABLE, null, null);
    }
}
