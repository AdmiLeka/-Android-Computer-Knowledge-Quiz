package com.thyevolution.computerknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button startGameBtn, settingsBtn, reviewBtn, exitBtn;
    private static final int TIME_INTERVAL = 2000;
    private long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadPrefLanguage();
        loadPrefMode();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startGameBtn = findViewById(R.id.startGameBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        reviewBtn = findViewById(R.id.reviewBtn);
        exitBtn = findViewById(R.id.exitBtn);

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), quizSelectionActivity.class);
                startActivity(i);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), settingsActivity.class);
                startActivity(i);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    //Method that closes the app with double back press
    @Override
    public void onBackPressed() {
        if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }
        else {
            Toast.makeText(getBaseContext(), getString(R.string.toastDoubleBack), Toast.LENGTH_SHORT).show();
        }

        backPressed = System.currentTimeMillis();
    }

    //Loading the language from the user's preferences and then calling setLocale();
    public void loadPrefLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences(loaderActivity.SharedPreferences, MODE_PRIVATE);
        String language = sharedPreferences.getString(loaderActivity.Language, "en");
        setLocale(language);
    }

    //Setting the language after getting it from the user's preferences
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Singleton.getInstance().setLanguage(lang);
    }

    //A method to load the user's theme choice and apply it
    private void loadPrefMode() {
        SharedPreferences sharedPreferences = getSharedPreferences(loaderActivity.SharedPreferences, MODE_PRIVATE);
        int mode = sharedPreferences.getInt(loaderActivity.Mode, 1);
        if (mode == AppCompatDelegate.MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}