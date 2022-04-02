package com.geektech.newsapp41;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context) {
      preferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }

    public void saveBoardState(){
        preferences.edit().putBoolean("isBoardShown",true).apply();
    }

    public boolean isBoardShown(){
        return preferences.getBoolean("isBoardShown",false);
    }
    public  void saveUserName(String name){
        preferences.edit().putString("Name",name).apply();
    }
    public String getUserName(){
        return preferences.getString("Name","");
    }
    public  void saveImage(String image){
        preferences.edit().putString("Image",image).apply();
    }
    public String getImage(){
        return preferences.getString("Image","");
    }
    public void clearPrefs(Context context){
        preferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }
}
