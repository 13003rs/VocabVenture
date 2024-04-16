package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG3InterGrammarQ1 extends AppCompatActivity {

    Button btnPositive, btnComparative, btnSuperlative;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG3InterGrammarQ2.class);

    dialog_correct correctDialog = new dialog_correct(QuizG3InterGrammarQ2.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_inter_grammar_q1);

        init();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);
        // Reset the score for Quiz Intermediate Grammar to a default value
        preferencesManager.setInterGrammarScore(0);
    }

    public void init(){
        btnPositive = findViewById(R.id.btnPositive);
        btnComparative = findViewById(R.id.btnComparative);
        btnSuperlative = findViewById(R.id.btnSuperlative);
    }

    public void buttons() {
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Positive Degree", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnComparative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Comparative Degree", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnSuperlative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterGrammar();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Superlative Degree", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });
    }

    public void MethodForQuizInterGrammar() {
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getInterGrammarScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 2;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setInterGrammarScore(newScore);
    }

}