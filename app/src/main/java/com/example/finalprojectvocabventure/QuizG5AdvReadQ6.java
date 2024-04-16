package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5AdvReadQ6 extends AppCompatActivity {

    Button archie, leo, david;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5AdvReadQ7.class);
    dialog_correct correctDialog = new dialog_correct(QuizG5AdvReadQ7.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_adv_read_q6);

        init();
        buttons();
    }

    public void init(){
        archie = findViewById(R.id.btnD);
        leo = findViewById(R.id.btnE);
        david = findViewById(R.id.btnF);
    }

    public void buttons(){
        archie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Archie", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        leo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Leo", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        david.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked David", Toast.LENGTH_SHORT).show();
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