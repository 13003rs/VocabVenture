package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5InterReadQ9 extends AppCompatActivity {

    Button btnRes, btnGift, btnPet;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5InterReadQ10.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5InterReadQ10.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_inter_read_q9);

        init();
        buttons();
    }

    public void init(){
        btnRes = findViewById(R.id.btn_resc);
        btnGift = findViewById(R.id.btn_gift);
        btnPet = findViewById(R.id.btn_bought);
    }

    public void buttons(){
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked She rescued it.", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked She bought it at the pet store.", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked She got it as a birthday gift.", Toast.LENGTH_SHORT).show();
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