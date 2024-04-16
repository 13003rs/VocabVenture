package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5BasicGrammarQ4 extends AppCompatActivity {

    Button btnGym, btnTogether, btnGroup;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG5BasicGrammarQ5.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5BasicGrammarQ5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_grammar_q4);

        init();
        buttons();
    }

    public void init(){
        btnGym = findViewById(R.id.btnGym);
        btnTogether = findViewById(R.id.btnTogether);
        btnGroup = findViewById(R.id.btnGroup);
    }

    public void buttons() {
        btnGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked gym", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnTogether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked together", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicGrammar();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked group", Toast.LENGTH_SHORT).show();
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