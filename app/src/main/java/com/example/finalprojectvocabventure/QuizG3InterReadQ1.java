package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG3InterReadQ1 extends AppCompatActivity {

    Button btnNest, btnGarden, btnFarm;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3InterReadQ2.class);

    dialog_correct correctDialog = new dialog_correct(QuizG3InterReadQ2.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_inter_read1);

        init();
        buttons();
        preferencesManager = new SharedPreferencesManager(this);
        // Reset the score for Quiz Intermediate Grammar to a default value
        preferencesManager.setInterReadingScore(0);

    }

    public void init(){
        btnNest = findViewById(R.id.btn_Nest);
        btnGarden = findViewById(R.id.btn_Garden);
        btnFarm = findViewById(R.id.btn_farm);
    }

    public void buttons(){
        btnNest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "You clicked In the nest.", Toast.LENGTH_SHORT).show();
                }
            }, 200);
            }

        });

        btnFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked In the farmhouse.", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked In the gargen.", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });


    }

    public void MethodForQuizInterReading() {
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getInterReadingScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 1;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setInterReadingScore(newScore);
    }
}