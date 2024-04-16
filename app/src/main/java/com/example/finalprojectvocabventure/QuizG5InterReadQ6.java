package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG5InterReadQ6 extends AppCompatActivity {

    Button birdFood, turkey, hotdog;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5InterReadQ7.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5InterReadQ7.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_inter_read_q6);

        init();
        buttons();

    }

    public void init(){
        birdFood = findViewById(R.id.btn_birdfood);
        turkey = findViewById(R.id.btn_turkey);
        hotdog = findViewById(R.id.btn_hotdog);
    }

    public void buttons(){
        birdFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked bird food", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        hotdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked hot dogs", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        turkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked a turkey", Toast.LENGTH_SHORT).show();
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