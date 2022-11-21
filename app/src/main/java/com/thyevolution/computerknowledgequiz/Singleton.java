package com.thyevolution.computerknowledgequiz;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Singleton extends AppCompatActivity{
    private static Singleton instance;
    private String language;
    private int mode;
    private boolean created;

    public void setPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(loaderActivity.SharedPreferences, MODE_PRIVATE);
        language = sharedPreferences.getString(loaderActivity.Language, "en");
        mode = sharedPreferences.getInt(loaderActivity.Mode, 1);
        created = sharedPreferences.getBoolean(loaderActivity.dbCreated, false);
    }

    private Singleton () {

    }
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }
}
