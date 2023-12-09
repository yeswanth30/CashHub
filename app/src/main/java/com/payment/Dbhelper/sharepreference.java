package com.payment.Dbhelper;

import android.content.Context;
import android.content.SharedPreferences;

public class sharepreference {

        private static Context mcontext;
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        int PRIVATE_NODE=0;
        public static final String PREFRENCE="USER";
        private static final String IS_FIRST_TIME_LAUNCH ="IsFirstTimeLaunch";
        public void SharePref(Context mcontext){
        this.mcontext=mcontext;
        pref=mcontext.getSharedPreferences(PREFRENCE,PRIVATE_NODE);
        editor= pref.edit();
    }
        public void setIsFirstTimeLaunch(Boolean isFirstTimeLaunch){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,isFirstTimeLaunch);
        editor.commit();
    }
        public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
        public static void setSharedPrefrence(Context context,String name,String value){
        mcontext = context;
        SharedPreferences settings=context.getSharedPreferences(PREFRENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(name,value);
        editor.apply();
    }
        public static String getSharedPrefrences(Context context,String name,int defaultValue){
        SharedPreferences settings = context.getSharedPreferences(PREFRENCE,0);
        return settings.getString(name,"");
    }
        public static void removeprefrence(Context context,String name){
        SharedPreferences settings = context.getSharedPreferences(PREFRENCE,0);
        settings.edit().remove(name).apply();
    }
        public static void clearPreference(Context context){
        SharedPreferences settings=context.getSharedPreferences(PREFRENCE,0);
        settings.edit().clear().apply();
    }

    }


