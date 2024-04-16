package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizG3AdvReadQ8 extends AppCompatActivity {

    Button btnSiblings, btnNeighbors, btnCousins;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3AdvReadQ9.class);
    dialog_correct correctDialog = new dialog_correct(QuizG3AdvReadQ9.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_adv_read_q8);

        init();
        buttons();

    }

    public void init(){
        btnCousins = findViewById(R.id.btn_cous);
        btnNeighbors = findViewById(R.id.btn_neigh);
        btnSiblings = findViewById(R.id.btn_sibs);
    }

    public void buttons(){
        btnCousins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked cousins", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnNeighbors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked neighbors", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnSiblings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked brother and sister", Toast.LENGTH_SHORT).show();
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