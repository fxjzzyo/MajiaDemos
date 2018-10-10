package com.fxj.earnmoney.utils;

import android.content.Context;
import android.content.res.Resources;

import com.fxj.earnmoney.R;

/**
 * Created by v_fanlulin on 2018/10/10.
 */

public class ADFilterTool {
    public static boolean hasAd(Context context, String url){
        Resources res= context.getResources();
        String[]adUrls =res.getStringArray(R.array.adBlockUrl);
        for(String adUrl :adUrls){
            if(url.contains(adUrl)){
                return true;
            }
        }
        return false;
    }


}
