package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class QuizG5BasicReadQ4 extends AppCompatActivity {
    Button btnBase, btnTaste, btnPaste, sound1;
    TextToSpeech txtToSpeech;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5BasicReadQ5.class);

    dialog_correct correctDialog = new dialog_correct(QuizG5BasicReadQ5.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_read_q4);

        init();
        initializeTextToSpeech();
        buttons();
    }

    public void init(){
        btnBase = findViewById(R.id.btn_base);
        btnTaste = findViewById(R.id.btn_taste);
        btnPaste = findViewById(R.id.btn_paste);
        sound1 = findViewById(R.id.btn_sound);
    }

    public void initializeTextToSpeech(){
        txtToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int langResult = txtToSpeech.setLanguage(Locale.UK);

                    if (langResult == TextToSpeech.LANG_MISSING_DATA ||
                            langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle language not supported or missing data
                    }
                } else {
                    Log.e("TextToSpeech", "Initialization failed with status: " + status);
                }
            }
        });
    }

    public void buttons(){
        btnPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked paste", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked base", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnTaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked taste", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        sound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = "taste. I can still remember the taste of my favorite food. taste";
                speakText(textToSpeak);
            }
        });
    }

    private void speakText(String text) {
        float speechRate = 0.7f;
        txtToSpeech.setSpeechRate(speechRate);
        txtToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        if (txtToSpeech != null) {
            txtToSpeech.stop();
            txtToSpeech.shutdown();
        }
        super.onDestroy();
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