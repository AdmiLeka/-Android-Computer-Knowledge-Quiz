package com.thyevolution.computerknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.thyevolution.computerknowledgequiz.R;

public class quizSelectionActivity extends AppCompatActivity {

    private RadioGroup rgChoices;
    private RadioButton rbBeginner, rbIntermediate, rbAdvanced;
    private Button choiceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rgChoices = findViewById(R.id.rgChoices);
        rbBeginner = findViewById(R.id.rbBeginner);
        rbIntermediate = findViewById(R.id.rbBeginner);
        rbAdvanced = findViewById(R.id.rbAdvanced);
        choiceButton = findViewById(R.id.choiceButton);
        choiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedBtn = rgChoices.getCheckedRadioButtonId();
                switch (checkedBtn) {
                    case R.id.rbBeginner:
                        Intent i = new Intent(getApplicationContext(), quizGameActivity.class);
                        i.putExtra("Difficulty", 1);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.rbIntermediate:
                        Intent k = new Intent(getApplicationContext(), quizGameActivity.class);
                        k.putExtra("Difficulty", 2);
                        startActivity(k);
                        finish();
                        break;
                    case R.id.rbAdvanced:
                        Intent j = new Intent(getApplicationContext(), quizGameActivity.class);
                        j.putExtra("Difficulty", 3);
                        startActivity(j);
                        finish();
                        break;
                    default:
                        Toast.makeText(quizSelectionActivity.this, getString(R.string.toastSelectLevel), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

}