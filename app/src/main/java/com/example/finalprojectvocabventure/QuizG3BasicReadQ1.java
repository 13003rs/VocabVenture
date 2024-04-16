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

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class QuizG3BasicReadQ1 extends AppCompatActivity {

    TextView btnBrother, btnSister, btnFamily;
    ImageView nxtBtn,bckBtn, famIcon;
    TextToSpeech txtToSpeech;

    dialog_wrong wrongDialog = new dialog_wrong(QuizG3BasicReadQ2.class);

    dialog_correct correctDialog = new dialog_correct(QuizG3BasicReadQ2.class);
    SharedPreferencesManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_basic_read_q1);

        init();
        initializeTextToSpeech();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);
        // Reset the score for Quiz Basic Grammar to a default value
        preferencesManager.setBasicReadingScore(0);
    }

    public void init(){
        btnBrother = findViewById(R.id.btn_brother);
        btnSister = findViewById(R.id.btn_sister);
        btnFamily = findViewById(R.id.btn_family);
        famIcon = findViewById(R.id.icon_family);
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

    public void buttons() {
        btnBrother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked brother", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnSister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked sister", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked family", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        famIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = "The show is fun for the whole family. ";
                speakText(textToSpeak);
            }
        });

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q2basicg3read = new Intent(getBaseContext(), QuizG3BasicReadQ2.class);
                startActivity(q2basicg3read);
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

        // Get the score for Quiz 1
        int currentScore = preferencesManager.getBasicReadingScore();

        // Modify the score for Quiz 1
        int newScore = currentScore + 2; // times 2 yung score each

        // Update the score for Quiz 1 in SharedPreferences
        preferencesManager.setBasicReadingScore(newScore);
    }

}