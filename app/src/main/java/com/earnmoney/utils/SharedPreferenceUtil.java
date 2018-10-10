package com.earnmoney.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/4/13.
 */

public class SharedPreferenceUtil {
public static final String SPF_USER = "user";
public static final String KEY_NAME = "name";
public static final String KEY_PASSWORD = "password";
public static final String IS_AUTO_LOGIN = "autoLogin";
public static final String IS_LOGIN_KEY = "islogin";



//个人资料
public static final String kEY_REAL_NAME = "realName";
public static final String kEY_AGE = "age";
public static final String kEY_ADDRESS= "address";
public static final String kEY_PHONE= "phone";
public static final String kEY_GRADUATE= "graduate";


    /**
     * 存储键值对
     * @param context
     * @param key
     * @param data
     */
    public static void saveData(Context context, String key,String data){
        SharedPreferences spf = context.getSharedPreferences(SPF_USER, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString(key, data);
        editor.commit();

    }

    /**
     * 根据键，获取值
     * @param context
     * @param key
     * @return
     */
    public static String getData(Context context, String key){
        SharedPreferences spf = context.getSharedPreferences(SPF_USER, context.MODE_PRIVATE);
        String value = spf.getString(key, null);
        return value;
    }
}
