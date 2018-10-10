package com.example.v_fanlulin.majiademo2.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.v_fanlulin.majiademo2.javabean.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

            Random random = new Random();
            boolean b = random.nextBoolean();
            Info info;
            if (b) {
                 info= new Info(id,title,salary,persons,time,"长期",detail,address,0);
            }else {
                 info= new Info(id,title,salary,persons,time,"短期",detail,address,0);
            }

            infos.add(info);
        }

        return infos;
    }

    /**
     * 获取前5条
     * @return
     */
    public List<Info> getFirstFiveDatas(){

        List<Info> infos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME +" limit 5", null);
        while (c.moveToNext()) {

            int id = c.getInt(c.getColumnIndex("id"));
            String title= c.getString(c.getColumnIndex("title"));
            String salary = c.getString(c.getColumnIndex("salary"));
            String persons = c.getString(c.getColumnIndex("persons"));
            String time= c.getString(c.getColumnIndex("time"));
            String address= c.getString(c.getColumnIndex("address"));
            String detail = c.getString(c.getColumnIndex("detail"));

            Random random = new Random();
            boolean b = random.nextBoolean();
            Info info;
            if (b) {
                info= new Info(id,title,salary,persons,time,"长期",detail,address,0);
            }else {
                info= new Info(id,title,salary,persons,time,"短期",detail,address,0);
            }

            infos.add(info);
        }

        return infos;
    }

    /**
     * 根据开始id取得后面两条数据
     * @param startId
     * @return
     */
    public List<Info> getMoreTwoDatas(int startId){
        int index = 0;
        List<Info> infos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME, null);
        while (c.moveToNext()) {
            index++;
            if(index>startId+1){

            int id = c.getInt(c.getColumnIndex("id"));
            String title= c.getString(c.getColumnIndex("title"));
            String salary = c.getString(c.getColumnIndex("salary"));
            String persons = c.getString(c.getColumnIndex("persons"));
            String time= c.getString(c.getColumnIndex("time"));
            String address= c.getString(c.getColumnIndex("address"));
            String detail = c.getString(c.getColumnIndex("detail"));

            Random random = new Random();
            boolean b = random.nextBoolean();
            Info info;
            if (b) {
                info= new Info(id,title,salary,persons,time,"长期",detail,address,0);
            }else {
                info= new Info(id,title,salary,persons,time,"短期",detail,address,0);
            }

            infos.add(info);
            }
            //每次最多两条
            if (infos.size() == 2) {
                break;
            }
        }

        return infos;
    }

}
