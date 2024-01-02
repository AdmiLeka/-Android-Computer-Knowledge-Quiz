package com.thyevolution.computerknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class changeLanguage extends AppCompatActivity {
    private RadioGroup rgLanguageChoices;
    private RadioButton rbEng, rbGer;
    private Button saveLangBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rgLanguageChoices = findViewById(R.id.rgLanguageChoices);
        rbEng = findViewById(R.id.rbEng);
        rbGer = findViewById(R.id.rbGer);
        saveLangBtn = findViewById(R.id.saveLangBtn);

        saveLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int choice = rgLanguageChoices.getCheckedRadioButtonId();
                switch (choice) {
                    case R.id.rbEng:
                        setLocale("en");
                        break;
                    case R.id.rbGer:
                        setLocale("de");
                        break;
                    default:
                        Toast.makeText(changeLanguage.this, getString(R.string.toastChooseLanguage), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //The method that changes the language of all application aspects (questions/answers, toast messages, buttons etc.)
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
        invalidateOptionsMenu();
        SharedPreferences sharedPreferences = getSharedPreferences(loaderActivity.SharedPreferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Singleton.getInstance().setLanguage(lang);
        editor.putString(loaderActivity.Language, lang);
        editor.apply();
        //Exiting all activities as to load the new language after saving it to Shared Preferences
        finishAffinity();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}