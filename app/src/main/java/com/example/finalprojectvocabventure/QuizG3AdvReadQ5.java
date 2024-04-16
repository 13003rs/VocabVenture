package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizG3AdvReadQ5 extends AppCompatActivity {

    Button helpful, greedy, giving;
    dialog_wrong wrongDialog = new dialog_wrong(g3AdvRead2.class);
    dialog_correct correctDialog = new dialog_correct(g3AdvRead2.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_adv_read_q5);

        init();
        buttons();
    }

    public void init(){
        helpful = findViewById(R.id.btn_helpful);
        greedy = findViewById(R.id.btn_greedy);
        giving = findViewById(R.id.btn_giving);
    }

    public void buttons(){
        greedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Greedy", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        giving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Giving", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        helpful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Helpful", Toast.LENGTH_SHORT).show();
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