package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5InterReadQ1 extends AppCompatActivity {

    Button btnFresh, btnBad, btnDark;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5InterReadQ2.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5InterReadQ2.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_inter_read_q1);

        init();
        buttons();
        preferencesManager = new SharedPreferencesManager(this);
        // Reset the score for Quiz Intermediate Grammar to a default value
        preferencesManager.setInterReadingScore(0);
    }

    public void init(){
        btnFresh = findViewById(R.id.btn_fresh);
        btnBad = findViewById(R.id.btn_badcon);
        btnDark = findViewById(R.id.btn_dark);
    }

    public void buttons(){
        btnFresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Fresh", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Dark", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked In bad condition", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });


    }

    public void MethodForQuizInterReading() {
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getInterReadingScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 1;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setInterReadingScore(newScore);
    }
}