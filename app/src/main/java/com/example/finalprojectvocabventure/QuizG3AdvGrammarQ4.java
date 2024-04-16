package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG3AdvGrammarQ4 extends AppCompatActivity {
    Button btnQuickly, btnPoorly, btnHardly;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG3AdvGrammarQ5.class);

    dialog_correct correctDialog = new dialog_correct(QuizG3AdvGrammarQ5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_adv_grammar_q4);

        init();
        buttons();
    }

    public void init(){
        btnQuickly = findViewById(R.id.btnQuickly);
        btnPoorly = findViewById(R.id.btnPoorly);
        btnHardly = findViewById(R.id.btnHardly);
    }

    public void buttons() {
        btnQuickly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvGrammar();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Quickly", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnPoorly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Poorly", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnHardly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Hardly", Toast.LENGTH_SHORT).show();
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