package com.exam.foodit.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.exam.foodit.Activity.LoginPage;
import com.exam.foodit.Model.Category;
import com.exam.foodit.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CommonSharedPreference {
    private static String PREF_KEY = "preference" ;
    static SharedPreferences.Editor editor;

    public ArrayList<Category> getCategoryPref(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        String json=sharedPref.getString("CATEGORIES",null);
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Category>>(){}.getType();

        ArrayList<Category> beans = null ;
        try {
            beans = gson.fromJson(json, type);
        } catch(Exception e) {
            return null ;
        }
        return beans;
    }

    public void setCategoryPref(Context context, ArrayList<Category> beans) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Gson gson=new Gson();
        String json=gson.toJson(beans);
        editor.putString("CATEGORIES",json);
        editor.commit();
    }


    public void setUserLoginSharedPref(Context context, User beans) {
        SharedPreferences sharedPref= PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        Gson gson=new Gson();
        String json=gson.toJson(beans);
        editor.putString("USER_LOGIN",json);
        editor.commit();
    }

    public User getUserLoginSharedPref(Context context) {
        SharedPreferences sharedPref= PreferenceManager.getDefaultSharedPreferences(context);
        String json=sharedPref.getString("USER_LOGIN",null);
        Gson gson=new Gson();
        Type type=new TypeToken<User>(){}.getType();

        User beans = null ;
        try {
            beans = gson.fromJson(json, type);
        } catch(Exception e) {
            return null ;
        }
        return beans;
    }

}
