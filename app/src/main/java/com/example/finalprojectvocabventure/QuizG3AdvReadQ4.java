package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizG3AdvReadQ4 extends AppCompatActivity {

    Button bHat, bToys, bFood;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3AdvReadQ5.class);
    dialog_correct correctDialog = new dialog_correct(QuizG3AdvReadQ5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_adv_read_q4);

        init();
        buttons();
    }

    public void init(){
        bToys = findViewById(R.id.btn_bToys);
        bHat = findViewById(R.id.btn_bHat);
        bFood = findViewById(R.id.btn_bFood);
    }

    public void buttons(){
        bHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Ben’s store sells hat", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        bFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Ben’s store sells food", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        bToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Ben’s store sells toys", Toast.LENGTH_SHORT).show();
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