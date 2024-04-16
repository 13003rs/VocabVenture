package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5BasicGrammarQ1 extends AppCompatActivity {

    Button btnFirstChoice, btnSecondChoice, btnThirdChoice;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG5BasicGrammarQ2.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5BasicGrammarQ2.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_grammar_q1);

        init();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);
        // Reset the score for Quiz Basic Grammar to a default value
        preferencesManager.setBasicGrammarScore(0);
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
                        Toast.makeText(getApplicationContext(), "You clicked Cora\'s dog", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "You clicked Coras dog", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "You clicked Coras\' dog", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });
    }

    public void MethodForQuizBasicGrammar() {
        // Get the score for Quiz Basic Grammar
        int currentScore = preferencesManager.getBasicGrammarScore();

        // Modify the score for Quiz Basic Grammar
        int newScore = currentScore + 2;

        // Update the score for Quiz Basic Grammar in SharedPreferences
        preferencesManager.setBasicGrammarScore(newScore);
    }

}