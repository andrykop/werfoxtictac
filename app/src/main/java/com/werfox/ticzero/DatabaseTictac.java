package com.werfox.ticzero;

import android.content.Context;
import android.content.SharedPreferences;

public class DatabaseTictac {
    private static String tictac = "tic";
    private static String tictacmain = "tictacmain";
    private SharedPreferences preferences;

    public DatabaseTictac(Context context){
        String NAME = "tic";
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setData(String data){
        preferences.edit().putString(DatabaseTictac.tictac, data).apply();
    }

    public String getData(){
        return preferences.getString(tictac, "");
    }
    public void setTictacmain(String data){
        preferences.edit().putString(DatabaseTictac.tictacmain, data).apply();
    }

    public String getTictacmain(){
        return preferences.getString(tictacmain, "");
    }
}
