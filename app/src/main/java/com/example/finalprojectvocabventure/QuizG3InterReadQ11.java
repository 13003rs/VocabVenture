package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG3InterReadQ11 extends AppCompatActivity {

    Button btnBreak, btnTired, btnLost;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3InterReadQ12.class);

    dialog_correct correctDialog = new dialog_correct(QuizG3InterReadQ12.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_inter_read_q11);

        init();
        buttons();

    }

    public void init(){
        btnBreak = findViewById(R.id.btn_break);
        btnTired = findViewById(R.id.btn_tired);
        btnLost = findViewById(R.id.btn_lost);
    }

    public void buttons(){
        btnTired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Something might get tired", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Something might get lost", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Something might break", Toast.LENGTH_SHORT).show();
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