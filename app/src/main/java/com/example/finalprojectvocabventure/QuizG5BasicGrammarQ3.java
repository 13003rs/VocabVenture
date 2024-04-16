package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5BasicGrammarQ3 extends AppCompatActivity {

    Button btnFirstChoice, btnSecondChoice, btnThirdChoice;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG5BasicGrammarQ4.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5BasicGrammarQ4.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_grammar_q3);

        init();
        buttons();
    }

    public void init(){
        btnFirstChoice = findViewById(R.id.btnFirstChoice);
        btnSecondChoice = findViewById(R.id.btnSecondChoice);
        btnThirdChoice = findViewById(R.id.btnThirdChoice);
    }

    public void buttons() {
        btnFirstChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicGrammar();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked The beggar\'s bag", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnSecondChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked The beggars\' bag", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnThirdChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked The beggar\' bag\'s", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });
    }


    public void MethodForQuizBasicGrammar() {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(getApplicationContext());

        // Get the score for Quiz Basic Grammar
        int currentScore = preferencesManager.getBasicGrammarScore();

        // Modify the score for Quiz Basic Grammar
        int newScore = currentScore + 2;

        // Update the score for Quiz Basic Grammar in SharedPreferences
        preferencesManager.setBasicGrammarScore(newScore);
    }

}