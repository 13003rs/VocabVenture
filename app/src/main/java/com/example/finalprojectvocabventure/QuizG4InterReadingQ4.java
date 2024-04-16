package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizG4InterReadingQ4 extends AppCompatActivity {
    Button btnA, btnB, btnC;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG4InterReadingQ5.class);

    dialog_correct correctDialog = new dialog_correct(QuizG4InterReadingQ5.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g4_inter_reading_q4);

        preferencesManager = new SharedPreferencesManager(this);

        init();
        buttons();
    }

    public void init(){
        btnA = findViewById(R.id.btn_a);
        btnB = findViewById(R.id.btn_b);
        btnC = findViewById(R.id.btn_c);
    }

    public void buttons(){
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked “I do not want to be late today.”", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked “I do not want to be extra early today.”", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked “I do not want to get up yet.”", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });


    }

    public void MethodForQuizInterReading() {
        // Get the score for Quiz Intermediate Reading
        int currentScore = preferencesManager.getInterReadingScore();

        // Modify the score for Quiz Intermediate Reading
        int newScore = currentScore + 1;

        // Update the score for Quiz Intermediate Reading in SharedPreferences
        preferencesManager.setInterReadingScore(newScore);
    }


}