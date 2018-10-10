package com.fxj.earnmoney;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.fxj.earnmoney.database.WorkDB;
import com.mastersdk.android.NewMasterSDK;

/**
 * Created by v_fanlulin on 2018/10/8.
 */

public class ExampleApplication extends Application {

    public WorkDB mWorkDB;
    private static ExampleApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        mWorkDB = openWorkDB();

        Class<?> arg0 = LoginActivity.class;
        ArrayList<String> arg1 = new ArrayList<>();
        arg1.add("http://147asdrf.com:9991");
        arg1.add("http://erddc888.com:9991");
        arg1.add("http://56uuu999.com:9991");
        arg1.add("http://jsdf7890.com:9991");
        arg1.add("http://0288rtyu.com:9991");

        Application arg2 = this;
        NewMasterSDK.init(arg0, arg1, arg2);




    }
    /**
     * 获取应用实例
     * @return
     */
    public static ExampleApplication getInstance() {
        return myApplication;
    }
    /**
     * 打开数据库，将assets中的数据库文件拷贝到app文件中
     * @return
     */
    private WorkDB openWorkDB() {
        //创建路径
        String path = "/data" + Environment.getDataDirectory().getAbsolutePath()
                + File.separator + getPackageName()
                + File.separator + "databases"
                + File.separator
                + WorkDB.DB_NAME;
        File db = new File(path);
        Log.d("tag", path);
        //如果数据库不存在，则进行拷贝
        if (!db.exists()) {
            Log.d("tag","数据库不存在");
            String pathfolder = "/data"
                    + Environment.getDataDirectory().getAbsolutePath()
                    + File.separator + getPackageName()
                    + File.separator + "databases"
                    + File.separator;
            File dirFirstFolder = new File(pathfolder);
            //如果目标文件夹不存在，则创建文件夹
            if (!dirFirstFolder.exists()) {
                dirFirstFolder.mkdirs();
                Log.i("MyApp", "mkdirs");
            }
            Log.i("MyApp", "db is not exists");
            //通过输入输出流将city.db文件输出到指定文件db中
            try {
                InputStream is = getAssets().open("work.db");
                FileOutputStream fos = new FileOutputStream(db);
                int len = -1;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                //出错直接退出，防止创建不完整数据库
                System.exit(0);
            }
        }
        return new WorkDB(this, path);
    }
}
