package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5AdvReadQ4 extends AppCompatActivity {

    Button slab, worTail, chLab;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5AdvReadQ5.class);
    dialog_correct correctDialog = new dialog_correct(QuizG5AdvReadQ5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_adv_read_q4);

        init();
        buttons();
    }

    public void init(){
        slab = findViewById(R.id.btn_slabs);
        worTail = findViewById(R.id.btn_wTail);
        chLab = findViewById(R.id.btn_cLabs);
    }

    public void buttons(){
        slab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked They spelled the word \"lobster\" incorrectly", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        chLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked They charged too much for a lobster tail", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        worTail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked The word \"tail\" should be \"tale\"", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }

    public void MethodForQuizAdvReading() {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(getApplicationContext());
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getAdvReadingScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 1;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setAdvReadingScore(newScore);
    }
}