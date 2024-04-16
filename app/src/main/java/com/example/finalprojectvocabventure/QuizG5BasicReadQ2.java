package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5BasicReadQ2 extends AppCompatActivity {
    Button btnMount, btnPond, btnVolcano;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5BasicReadQ3.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5BasicReadQ3.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_read_q2);

        init();
        buttons();
    }

    public void init(){
        btnPond = findViewById(R.id.btn_pond);
        btnVolcano = findViewById(R.id.btn_volcano);
        btnMount = findViewById(R.id.btn_mountain);
    }

    public void buttons(){
        btnPond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked pond", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnVolcano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked volcano", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnMount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked mountain", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }

    public void MethodForQuizBasicReading() {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(getApplicationContext());
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getBasicReadingScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 2;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setBasicReadingScore(newScore);
    }
}