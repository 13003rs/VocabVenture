package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizG3AdvReadQ1 extends AppCompatActivity {

    Button btnLola, btnBen, btnMel;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3AdvReadQ2.class);

    dialog_correct correctDialog = new dialog_correct(QuizG3AdvReadQ2.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_adv_read_q1);

        init();
        buttons();
        preferencesManager = new SharedPreferencesManager(this);
        // Reset the score for Quiz Intermediate Grammar to a default value
        preferencesManager.setAdvReadingScore(0);
    }

    public void init(){
        btnLola = findViewById(R.id.btn_lola);
        btnMel = findViewById(R.id.btn_mel);
        btnBen = findViewById(R.id.btn_ben);
    }

    public void buttons(){
        btnLola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Lala", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnMel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Mel", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnBen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Ben", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }

    public void MethodForQuizAdvReading() {
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getAdvReadingScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 1;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setInterReadingScore(newScore);
    }

}