package com.example.finalprojectvocabventure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class g3BasicRead12 extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech textToSpeech;
    private ImageButton nextBtn, backBtn, soundBtn,homeBtn;
    private Button readBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_basic_read12);

        nextBtn = findViewById(R.id.btnNext);
        backBtn = findViewById(R.id.btnBack);
        soundBtn = findViewById(R.id.soundBtn);
        readBtn = findViewById(R.id.readBtn);
        homeBtn = findViewById(R.id.btnHome);

        textToSpeech = new TextToSpeech(this, this);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent basicread13 = new Intent(g3BasicRead12.this, g3BasicRead13.class);
                startActivity(basicread13);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent basicread11 = new Intent(g3BasicRead12.this, g3BasicRead11.class);
                startActivity(basicread11);
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent basicread16 = new Intent(g3BasicRead12.this, HomeActivity.class);
                startActivity(basicread16);
            }
        });
        soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak ="hill.";
                speak(textToSpeak);
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak = "Jack and Jill went up the hill.";
                speak(textToSpeak);
            }
        });

        // Set a listener to handle the completion of speech
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                // Speech started
            }

            @Override
            public void onDone(String utteranceId) {
                // Speech finished
            }

            @Override
            public void onError(String utteranceId) {
                // Speech error
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or not supported
            }
        } else {
            // Initialization failed
        }
    }

    // Function to convert text to speech
    private void speak(String text) {
        float speechRate = 0.7f;
        textToSpeech.setSpeechRate(speechRate);

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}