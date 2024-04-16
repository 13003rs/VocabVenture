package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizG3AdvReadQ9 extends AppCompatActivity {

    Button btnFlower, btnGuava, btnSwim;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3AdvReadQ10.class);
    dialog_correct correctDialog = new dialog_correct(QuizG3AdvReadQ10.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_adv_read_q9);

        init();
        buttons();
    }

    public void init(){
        btnFlower = findViewById(R.id.btn_flowers);
        btnGuava = findViewById(R.id.btn_guavas);
        btnSwim = findViewById(R.id.btn_swim);
    }

    public void buttons(){
        btnFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked pick flowers", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnSwim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked go swimming", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnGuava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked pick guavas", Toast.LENGTH_SHORT).show();
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
        preferencesManager.setInterReadingScore(newScore);
    }


}