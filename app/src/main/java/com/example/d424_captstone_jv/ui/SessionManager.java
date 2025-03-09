package com.example.d424_captstone_jv.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_EMAIL = "userEmail";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void saveUserSession(int userId, String email) {
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
    }


    public int getUserId() {
        return sharedPreferences.getInt(KEY_USER_ID, -1);
    }


//    public String getUserEmail() {
//        return sharedPreferences.getString(KEY_USER_EMAIL, null);
//    }


    public boolean isLoggedIn() {
        return getUserId() != -1;
    }


//    public void logout() {
//        editor.clear();
//        editor.apply();
//    }
}
