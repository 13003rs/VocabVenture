package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizG4BasicReadQ1 extends AppCompatActivity {
    Button btnTown, btnCrown, btnDown;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG4BasicReadQ2.class);

    dialog_correct correctDialog = new dialog_correct(QuizG4BasicReadQ2.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g4_basic_read_q1);

        init();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);
        // Reset the score for Quiz Advanced Grammar to a default value
        preferencesManager.setBasicReadingScore(0);
    }

    public void init(){
        btnTown = findViewById(R.id.btnTown);
        btnCrown = findViewById(R.id.btnCrown);
        btnDown = findViewById(R.id.btnDown);
    }

    public void buttons() {
        btnCrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Crown", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Town", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Down", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });
    }
    public void MethodForQuizBasicReading() {
        // Get the score for Quiz Basic Reading
        int currentScore = preferencesManager.getBasicReadingScore();

        // Modify the score for Quiz Basic Reading
        int newScore = currentScore + 2;

        // Update the score for Quiz Basic Reading in SharedPreferences
        preferencesManager.setBasicReadingScore(newScore);
    }
}
