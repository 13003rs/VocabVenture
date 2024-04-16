package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class QuizG4BasicReadQ2 extends AppCompatActivity {
    Button btnNotice, btnJustice, btnLettuce,btnSound;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG4BasicReadQ3.class);

    dialog_correct correctDialog = new dialog_correct(QuizG4BasicReadQ3.class);
    SharedPreferencesManager preferencesManager;
    TextToSpeech txtToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g4_basic_read_q2);

        init();
        buttons();
        playSound();
        preferencesManager = new SharedPreferencesManager(this);

    }

    public void init(){
        btnSound = findViewById(R.id.btnSound);
        btnNotice = findViewById(R.id.btnNotice);
        btnJustice = findViewById(R.id.btnJustice);
        btnLettuce = findViewById(R.id.btnLettuce);
    }
    public void playSound() { //-- method for the function of the sound button
        txtToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int language = txtToSpeech.setLanguage(Locale.ENGLISH);
                    if (language == TextToSpeech.LANG_MISSING_DATA ||
                            language == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Language data is missing or not supported
                    }
                } else {
                    // Initialization failed
                }
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak = "Lettuce";
                txtToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void buttons() {

        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Notice", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnLettuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Lettuce", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnJustice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Justice", Toast.LENGTH_SHORT).show();
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
