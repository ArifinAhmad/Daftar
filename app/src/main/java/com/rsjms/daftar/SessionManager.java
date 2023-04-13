package com.rsjms.daftar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;


public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "user_login";
    public static final String LOGIN = "is_user_login";
    public static final String USERNAME = "username";
    public static final String NAME = "name";
    public static final String ALAMAT = "alamat";



    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String username, String name, String alamat){
        editor.putBoolean(LOGIN, true);
        editor.putString(USERNAME, username);
        editor.putString(NAME, name);
        editor.putString(ALAMAT, alamat);
        editor.apply();


    }
    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public  void checkLogin(){
        if(!this.isLoggin()){
            Intent intent = new Intent(context, login.class);
            context.startActivity(intent);
            ((MainActivity) context).finish();


        }
    }
    public HashMap<String, String> userDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, login.class);
        context.startActivity(i);
        ((MainActivity) context).finish();
    }


}
