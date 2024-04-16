package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5BasicReadQ1 extends AppCompatActivity {

    Button btnMonth, btnYear, btnDay;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5BasicReadQ2.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5BasicReadQ2.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_read_q1);

        init();
        buttons();
        preferencesManager = new SharedPreferencesManager(this);
        // Reset the score for Quiz Basic Grammar to a default value
        preferencesManager.setBasicReadingScore(0);
    }

    public void init(){
        btnDay = findViewById(R.id.btn_day);
        btnMonth = findViewById(R.id.btn_month);
        btnYear = findViewById(R.id.btn_year);
    }

    public void buttons(){
        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked day", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked year", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked month", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }

    public void MethodForQuizBasicReading() {
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getBasicReadingScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 2;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setBasicReadingScore(newScore);
    }



}