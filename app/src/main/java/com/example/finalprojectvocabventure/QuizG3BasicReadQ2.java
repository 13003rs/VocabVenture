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

public class QuizG3BasicReadQ2 extends AppCompatActivity {

    TextView btnClaw, btnLaw, btnDraw;
    ImageView nxtBtn, bckBtn, draw_icon;
    TextToSpeech txtToSpeech;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG3BasicReadQ3.class);
    dialog_correct correctDialog = new dialog_correct(QuizG3BasicReadQ3.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_basic_read_q2);

        init();
        initializetextToSpeech();
        buttons();
    }

    public void init(){
        btnClaw = findViewById(R.id.btn_claw);
        btnLaw = findViewById(R.id.btn_law);
        btnDraw = findViewById(R.id.btn_draw);
        draw_icon = findViewById(R.id.icon_drawing);
        nxtBtn = findViewById(R.id.nextBtn);
        bckBtn = findViewById(R.id.backBtn);
    }

    public void initializetextToSpeech(){
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
        btnClaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked claw", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked law", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked draw", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        draw_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = "She likes to draw pictures of her family and pets. ";
                speakText(textToSpeak);
            }
        });

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q3basicg3read = new Intent(getBaseContext(), QuizG3BasicReadQ3.class);
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