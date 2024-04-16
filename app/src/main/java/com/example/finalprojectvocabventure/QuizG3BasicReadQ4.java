package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class QuizG3BasicReadQ4 extends AppCompatActivity {
    TextView btnWhen, btnYou, btnCan;
    ImageView youIcon;
    TextToSpeech txtToSpeech;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG3BasicReadQ5.class);
    dialog_correct correctDialog = new dialog_correct(QuizG3BasicReadQ5.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_basic_read_q4);

        init();
        initializeTextToSpeech();
        button();
    }

    public void init(){
        btnWhen = findViewById(R.id.btn_when);
        btnYou = findViewById(R.id.btn_you);
        btnCan = findViewById(R.id.btn_kan);
        youIcon = findViewById(R.id.you_sound);
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

    public void button(){
        btnWhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked when", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked can", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked you", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        youIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = "You have two feet. ";
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

        // Get the score for Quiz 1
        int currentScore = preferencesManager.getBasicReadingScore();

        // Modify the score for Quiz 1
        int newScore = currentScore + 2;

        // Update the score for Quiz 1 in SharedPreferences
        preferencesManager.setBasicReadingScore(newScore);
    }

}