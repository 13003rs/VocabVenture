package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5AdvReadQ1 extends AppCompatActivity {
    Button btnA, btnFish, btnTale;
    dialog_wrong wrongDialog = new dialog_wrong(g5AdvRead3Next.class);
    dialog_correct correctDialog = new dialog_correct(g5AdvRead3Next.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_adv_read_q1);

        init();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);

    }

    public void init(){
        btnA = findViewById(R.id.btn_a);
        btnFish = findViewById(R.id.btn_fishy);
        btnTale = findViewById(R.id.btn_tale);
    }

    public void buttons(){
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked A", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnTale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Tale", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Fishy", Toast.LENGTH_SHORT).show();
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
        preferencesManager.setAdvReadingScore(newScore);
    }
}