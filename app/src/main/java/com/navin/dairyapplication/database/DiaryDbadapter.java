package com.navin.dairyapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.navin.dairyapplication.models.Task;

import java.util.ArrayList;
import java.util.List;

public class DiaryDbadapter extends DiaryDatabase {
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DATE = "taskdate";
    public static final String KEY_TIME = "tasktimer";
    public static final String KEY_TABLE = "tbl_dairy";


    public DiaryDbadapter(Context context) {
        super(context);
    }
    public long insertTask(Task task){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put(KEY_TITLE,task.getTitle());
        cv.put(KEY_DESCRIPTION,task.getDesc());
        cv.put(KEY_TIME,task.getTime());
        cv.put(KEY_DATE,task.getDate());

        return db.insert(KEY_TABLE,null,cv);



    }
    public long updateTask(Task task){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TITLE,task.getTitle());
        cv.put(KEY_DESCRIPTION,task.getDesc());
        cv.put(KEY_TIME,task.getTime());
        cv.put(KEY_DATE,task.getDate());

       return db.update(KEY_TABLE,cv,KEY_ID+ task.getId(),null);

    }
    public long deletTask(int id){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(KEY_TABLE,KEY_ID+ id,null);

    }
    public long deleteAllTask(){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(KEY_TABLE ,null,null);

    }

    public List<Task> showAll(){
        SQLiteDatabase db = getReadableDatabase();

        List<Task> taskList = new ArrayList<>();
        String sql = " select * from tbl_dairy";
        Cursor cursor = db.rawQuery(" select * from tbl_dairy", null);
        while ( cursor.moveToNext()) {
            Task task = new Task();
            task.setTitle(cursor.getString(1));
            task.setDesc(cursor.getString(2));
            task.setTime(cursor.getString(3));
            task.setDate(cursor.getString(4));
            taskList.add(task);
        }
        return taskList;


        }





}
