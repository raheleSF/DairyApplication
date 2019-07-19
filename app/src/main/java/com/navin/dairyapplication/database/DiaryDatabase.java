package com.navin.dairyapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDatabase extends SQLiteOpenHelper {

    public static final String DATABASE = "dairy.db";

    public DiaryDatabase(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table tbl_dairy (id Integer PRIMARY KEY AUTOINCREMENT ,title text " +
                ", description text,taskdate text, tasktimer text)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
