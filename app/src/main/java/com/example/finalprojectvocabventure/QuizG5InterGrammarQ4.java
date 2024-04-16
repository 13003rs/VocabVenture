package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5InterGrammarQ4 extends AppCompatActivity {

    Button btnWent, btnRed, btnRug;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG5InterGrammarQ5.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5InterGrammarQ5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_inter_grammar_q4);

        init();
        buttons();
    }

    public void init(){
        btnWent = findViewById(R.id.btnWent);
        btnRed = findViewById(R.id.btnRed);
        btnRug = findViewById(R.id.btnRug);
    }

    public void buttons() {
        btnWent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterGrammar();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked went", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked red", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnRug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked rug", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });
    }

    public void MethodForQuizInterGrammar() {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(getApplicationContext());

        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getInterGrammarScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 2;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setInterGrammarScore(newScore);
    }

}