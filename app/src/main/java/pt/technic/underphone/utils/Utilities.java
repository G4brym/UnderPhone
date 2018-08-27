package pt.technic.underphone.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Utilities {
    public static void saveSetting(Activity refer, String key, String value){
        SharedPreferences sharedPref = refer.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getSetting(Activity refer, String key, String defaultValue){
        SharedPreferences sharedPref = refer.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(key, defaultValue);

    }
}
