package com.example.v_fanlulin.majiademo2.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.v_fanlulin.majiademo2.javabean.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_fanlulin on 2018/10/9.
 */

public class WorkDB {

    public static final String DB_NAME = "work.db";
    private static final String TABLE_NAME = "work";
    private SQLiteDatabase db;

    public WorkDB(Context context, String path){
        db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
    }

    public List<Info> getAllDatas(){

        Log.i("tag","getAllDatas--------------");
        List<Info> infos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME, null);
        while (c.moveToNext()) {



            int id = c.getInt(c.getColumnIndex("id"));
            String title= c.getString(c.getColumnIndex("title"));
            String salary = c.getString(c.getColumnIndex("salary"));
            String persons = c.getString(c.getColumnIndex("persons"));
            String time= c.getString(c.getColumnIndex("time"));
            String address= c.getString(c.getColumnIndex("address"));
            String detail = c.getString(c.getColumnIndex("detail"));


            Info info= new Info(id,title,salary,persons,time,"长期",detail,address,0);
            infos.add(info);
        }

        return infos;
    }

}
