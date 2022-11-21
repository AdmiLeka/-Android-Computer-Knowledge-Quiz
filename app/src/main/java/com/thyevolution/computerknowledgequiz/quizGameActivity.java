package com.thyevolution.computerknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.thyevolution.computerknowledgequiz.R;

import java.util.ArrayList;
import java.util.Collections;

public class quizGameActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup rgQuizChoices;
    private RadioButton a1Btn, a2Btn, a3Btn, a4Btn;
    private Button nextQuestionBtn;
    private ArrayList<Question> questionList;
    private int questionCounter, questionCountTotal;
    private Question currentQuestion;
    private boolean answered;
    private int score;
    private int difficulty;
    private Bundle extras;
    private ArrayList<Button> buttonList;
    private Button correctButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);
        questionText = findViewById(R.id.questionText);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rgQuizChoices = findViewById(R.id.rgQuizChoices);
        a1Btn = findViewById(R.id.a1Btn);
        a2Btn = findViewById(R.id.a2Btn);
        a3Btn = findViewById(R.id.a3Btn);
        a4Btn = findViewById(R.id.a4Btn);
        nextQuestionBtn = findViewById(R.id.nextQuestionBtn);
        buttonList = new ArrayList<>();
        buttonList.add(a1Btn);
        buttonList.add(a2Btn);
        buttonList.add(a3Btn);
        buttonList.add(a4Btn);
        extras = getIntent().getExtras();
        difficulty = extras.getInt("Difficulty");
        questionList = getRespectiveList(difficulty);
        score = 0;
        questionCountTotal = 8;
        Collections.shuffle(questionList);
        showNextQuestion();

        a1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableAllButtons();
                checkAnswer();
            }
        });

        a2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableAllButtons();
                checkAnswer();
            }
        });

        a3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableAllButtons();
                checkAnswer();
            }
        });

        a4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableAllButtons();
                checkAnswer();
            }
        });

        nextQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    Toast.makeText(quizGameActivity.this, getString(R.string.toastSelectAnswer), Toast.LENGTH_SHORT).show();
                } else {
                    enableAllButtons();
                    showNextQuestion();
                }
            }
        });

    }

    //This method disable all the buttons after the user has already selected an answer
    private void disableAllButtons() {
        a1Btn.setClickable(false);
        a2Btn.setClickable(false);
        a3Btn.setClickable(false);
        a4Btn.setClickable(false);
    }

    //This method enables all the buttons again after the user presses the "NEXT" button.
    private void enableAllButtons() {
        a1Btn.setClickable(true);
        a2Btn.setClickable(true);
        a3Btn.setClickable(true);
        a4Btn.setClickable(true);
    }

    //This method checks the answers and sets the background color of the pressed radio button to red initially.
    //It then checks if the answer was correct. If it was, the background is set to green.
    //If not, it stays red and the correct answer radio button is assigned a green background.
    public void checkAnswer() {
        answered = true;
        RadioButton btnSelected = findViewById(rgQuizChoices.getCheckedRadioButtonId());
        if (btnSelected == correctButton) {
            score++;
        }
        btnSelected.setBackgroundColor(Color.RED);
        correctButton.setBackgroundColor(Color.GREEN);

        if (questionCounter < questionCountTotal) {
            nextQuestionBtn.setText(getText(R.string.nextBtn));
        } else {
            nextQuestionBtn.setText(getText(R.string.finish));
        }
    }

    private void showNextQuestion() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            a1Btn.setBackgroundColor(Color.WHITE);
            a2Btn.setBackgroundColor(Color.WHITE);
            a3Btn.setBackgroundColor(Color.WHITE);
            a4Btn.setBackgroundColor(Color.WHITE);
        } else {
            a1Btn.setBackgroundColor(Color.BLACK);
            a2Btn.setBackgroundColor(Color.BLACK);
            a3Btn.setBackgroundColor(Color.BLACK);
            a4Btn.setBackgroundColor(Color.BLACK);
        }

        rgQuizChoices.clearCheck();
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            //Mechanic so that the radio buttons don't always have the same answer sequence.
            Collections.shuffle(buttonList);
            int answerId = currentQuestion.getCorrectAnswerID();
            switch (answerId) {
                case 1:
                    correctButton = buttonList.get(0);
                    break;
                case 2:
                    correctButton = buttonList.get(1);
                    break;
                case 3:
                    correctButton = buttonList.get(2);
                    break;
                default:
                    correctButton = buttonList.get(3);
                    break;

            }
            buttonList.get(0).setText(currentQuestion.getAnswer1());
            buttonList.get(1).setText(currentQuestion.getAnswer2());
            buttonList.get(2).setText(currentQuestion.getAnswer3());
            buttonList.get(3).setText(currentQuestion.getAnswer4());
            questionText.setText(currentQuestion.getQuestion());
            questionCounter++;
            answered = false;

        } else {
            Intent i = new Intent(getApplicationContext(), quizResultsActivity.class);
            i.putExtra("Score", score);
            startActivity(i);
            finish();
        }
    }

    //The method to get the respective ArrayList based on user difficulty and language selection
    private ArrayList<Question> getRespectiveList(int level) {
        QuestionsDatabase questionsDB = new QuestionsDatabase(getApplicationContext());
        return questionsDB.getAllQuestions(level);

    }


}