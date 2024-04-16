package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizG3AdvReadQ7 extends AppCompatActivity {

    Button todo, have, wear;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3AdvReadQ8.class);
    dialog_correct correctDialog = new dialog_correct(QuizG3AdvReadQ8.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_adv_read_q7);

        init();
        buttons();

    }

    public void init(){
        wear = findViewById(R.id.btn_wear);
        todo = findViewById(R.id.btn_todo);
        have = findViewById(R.id.btn_have);
    }

    public void buttons(){
        wear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked what to wear during the summer", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        have.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked what to have during the summer", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked what to do during the summer", Toast.LENGTH_SHORT).show();
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