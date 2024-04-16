package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class QuizG4BasicReadQ3 extends AppCompatActivity {
    Button btnSea, btnFrizzy, btnBusy;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG4BasicReadQ4.class);

    dialog_correct correctDialog = new dialog_correct(QuizG4BasicReadQ4.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g4_basic_read_q3);

        init();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);

    }

    public void init(){
        btnSea = findViewById(R.id.btnSea);
        btnFrizzy = findViewById(R.id.btnFrizzy);
        btnBusy = findViewById(R.id.btnBusy);
    }

    public void buttons() {
        btnSea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Sea", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Busy", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnFrizzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Frizzy", Toast.LENGTH_SHORT).show();
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
