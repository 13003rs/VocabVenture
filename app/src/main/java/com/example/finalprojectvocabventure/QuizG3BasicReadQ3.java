package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class QuizG3BasicReadQ3 extends AppCompatActivity {

    TextView btnIt, btnThe, btnCan;
    ImageView nxtBtn, bckBtn, canIcon;
    TextToSpeech txtToSpeech;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3BasicReadQ4.class);
    dialog_correct correctDialog = new dialog_correct(QuizG3BasicReadQ4.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_basic_read_q3);

        init();
        initializeTextToSpeech();
        buttons();

    }

    public void init(){
        btnCan = findViewById(R.id.btn_can);
        btnIt = findViewById(R.id.btn_it);
        btnThe = findViewById(R.id.btn_the);
        canIcon = findViewById(R.id.has_sound);
        nxtBtn = findViewById(R.id.nextBtn);
        bckBtn = findViewById(R.id.backBtn);
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
        btnIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked it", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked the", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked can", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        canIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = "Birds can fly well.";
                speakText(textToSpeak);
            }
        });

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q3basicg3read = new Intent(getBaseContext(), QuizG3BasicReadQ1.class);
                startActivity(q3basicg3read);
            }
        });

        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent basicread17 = new Intent(getBaseContext(), g3BasicRead17.class);
                startActivity(basicread17);
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