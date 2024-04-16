package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5InterReadQ4 extends AppCompatActivity {

    Button coats, paint, better;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5InterReadQ5.class);
    dialog_correct correctDialog = new dialog_correct(QuizG5InterReadQ5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_inter_read_q4);

        init();
        buttons();
    }

    public void init(){
        coats = findViewById(R.id.btn_coats);
        paint = findViewById(R.id.btn_paint);
        better = findViewById(R.id.btn_better);
    }

    public void buttons(){
        coats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Wear two coats while painting", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked For better results, paint in warm weather", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        better.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked For better results, do two coats of paint", Toast.LENGTH_SHORT).show();
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