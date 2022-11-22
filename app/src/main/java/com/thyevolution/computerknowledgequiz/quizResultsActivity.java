package com.thyevolution.computerknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class quizResultsActivity extends AppCompatActivity {
    private Button playAgainButton, toMainMenuButton;
    private TextView score, impression;
    private Bundle extras;
    private int scoreNumber;
    private int index;
    private String goodEng[] = {"Impressive!", "Excellent!", "You're a pro!"};
    private String averageEng[] = {"Not bad!", "Well done!", "Nice!"};
    private String goodGer[] = {"Beeindruckend!", "Wunderbar!", "Du bist ein Pro!"};
    private String averageGer[] = {"Nicht schlect!", "Gut gemacht!", "Schön!"};

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Random random = new Random();
        index = random.nextInt(3);
        playAgainButton = findViewById(R.id.playAgainButton);
        toMainMenuButton = findViewById(R.id.toMainMenuButton);
        score = findViewById(R.id.score);
        impression = findViewById(R.id.impression);
        extras = getIntent().getExtras();
        scoreNumber = extras.getInt("Score");
        impression.setText(setImpression());
        score.setText(scoreNumber + "/8");


        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), quizSelectionActivity.class);
                startActivity(i);
                finish();
            }
        });

        toMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
    //Method to give feedback to the user after they finish the quiz. It depends on the score and the language.
    private String setImpression() {
        String language = Singleton.getInstance().getLanguage();
        switch (language) {
            case "en":
                if (scoreNumber >= 7) {
                    return goodEng[index];
                } else if (scoreNumber >= 3) {
                    return averageEng[index];
                } else {
                    return "Nice try!";
                }
            case "de":
                if (scoreNumber >= 7) {
                    return goodGer[index];
                } else if (scoreNumber >= 3) {
                    return averageGer[index];
                } else {
                    return "Guter Versuch!";
                }
            default:
                return "Woah!";
        }

    }
}