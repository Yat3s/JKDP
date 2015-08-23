package com.drivingevaluate.config;

import android.content.Context;

import com.drivingevaluate.util.SharedPreferencesUtils;

/**
 * Created by Yat3s on 15/8/6.
 * Email:hawkoyates@gmail.com
 */
public class AppConf {
    /**
     * save token as a global value
     */
    public static String TOKEN = "empty_token";

    public static int USER_ID;

    public static String DEFAULT_TOKEN = "IUAjX2ppYWthb2RpYW5waW5nXzEyMyNfJF9A";

    public static int getUserId(Context context){
        if (SharedPreferencesUtils.contains(context,"userId")){
            return (int) SharedPreferencesUtils.get(context,"userId",-1);
        }
        return -1;
    }

    public static String getToken(Context context){
        if (SharedPreferencesUtils.contains(context, "token")){
                return SharedPreferencesUtils.get(context,"token","").toString();
        }
        else
            return "empty_token";
    }
}
