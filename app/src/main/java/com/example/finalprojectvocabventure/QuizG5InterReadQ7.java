package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5InterReadQ7 extends AppCompatActivity {

    Button BtnFlaps, btnFlies, BtnYells;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5InterReadQ8.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5InterReadQ8.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_inter_read_q7);

        init();
        buttons();
    }

    public void init(){
        BtnFlaps = findViewById(R.id.btn_flaps);
        btnFlies = findViewById(R.id.btn_flies);
        BtnYells = findViewById(R.id.btn_yells);
    }


    public void buttons(){
        BtnFlaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked The parrot flaps its wings.", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnFlies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked The parrot flies in circle around Mia.", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        BtnYells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked The parrot yells back.", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });


    }

    public void MethodForQuizInterReading() {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(getApplicationContext());
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getInterReadingScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 1;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setInterReadingScore(newScore);
    }
}