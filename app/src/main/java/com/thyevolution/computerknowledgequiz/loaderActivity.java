package com.thyevolution.computerknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class loaderActivity extends AppCompatActivity {
    public static final String SharedPreferences = "sharedPrefs";
    public static final String Mode = "mode";
    public static final String Language = "language";
    public static final String dbCreated = "created";
    private ImageView blackBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        Singleton.getInstance().setPreferences(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        blackBackground = findViewById(R.id.blackBackground);
        createDataBase();
        fadeOut();
    }

    //Fade in animation, which is called after fade-out. Once finished, the main menu (MainActivity) pops up
    private void fadeIn() {
        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animationIn.reset();
        blackBackground.clearAnimation();
        blackBackground.startAnimation(animationIn);

        animationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //Fade out animation.
    private void fadeOut() {

        Animation animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        animationOut.reset();
        blackBackground.clearAnimation();
        blackBackground.startAnimation(animationOut);

        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fadeIn();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //A method to copy the pre-built SQLite database from the assets directory to the device's internal storage.
    private void copyDataBase(String dbname) throws IOException {
        // Open your local db as the input stream
        InputStream myInput = this.getAssets().open(dbname);
        // Path to the just created empty db
        File outFileName = this.getDatabasePath(dbname);
        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

        //This method will only run the first time a user opens the app
        private void createDataBase() {
            if (!Singleton.getInstance().isCreated()) {
                try {
                    copyDataBase("questionsAnswers.db");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferences, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(dbCreated, true);
                editor.apply();
            }
        }

}