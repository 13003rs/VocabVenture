package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5AdvGrammarQ4 extends AppCompatActivity {
    Button btnOnly, btnReally, btnUsually, btnToo;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG5AdvGrammarQ5.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5AdvGrammarQ5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_adv_grammar_q4);

        init();
        buttons();
    }

    public void init(){
        btnOnly = findViewById(R.id.btnOnly);
        btnReally = findViewById(R.id.btnReally);
        btnUsually = findViewById(R.id.btnUsually);
        btnToo = findViewById(R.id.btnToo);
    }

    public void buttons() {
        btnOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked only", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnReally.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvGrammar();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked really", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnUsually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked usually", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });
        btnToo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked too", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

    }

    public void MethodForQuizAdvGrammar() {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(getApplicationContext());

        // Get the score for Quiz Advanced Grammar
        int currentScore = preferencesManager.getAdvGrammarScore();

        // Modify the score for Quiz Advanced Grammar
        int newScore = currentScore + 2;

        // Update the score for Quiz Advanced Grammar in SharedPreferences
        preferencesManager.setAdvGrammarScore(newScore);
    }
}