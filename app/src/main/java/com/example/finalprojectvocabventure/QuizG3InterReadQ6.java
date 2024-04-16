package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizG3InterReadQ6 extends AppCompatActivity {

    Button tiny, white, round;
    dialog_wrong wrongDialog = new dialog_wrong(g3InterRead2.class);

    dialog_correct correctDialog = new dialog_correct(g3InterRead2.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_inter_read_q6);

        init();
        buttons();
    }

    public void init(){
        tiny = findViewById(R.id.btn_ItTiny);
        white = findViewById(R.id.btn_ItWhite);
        round = findViewById(R.id.btn_ItRound);
    }

    public void buttons(){
        tiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                Log.d("READ2", "CHECKK IF WORKINGGG");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked It is tiny", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                Log.d("READ2", "CHECKK IF WORKINGGG");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked It is white", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                Log.d("READ2", "CHECKK IF WORKINGGG");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked It is rubber", Toast.LENGTH_SHORT).show();
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