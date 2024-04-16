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

public class g5AdvSpelling28 extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech textToSpeech;
    private ImageButton nextBtn, backBtn, homeBtn;
    private Button readBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_adv_spelling28);
        nextBtn = findViewById(R.id.btnNext);
        backBtn = findViewById(R.id.btnBack);
        readBtn = findViewById(R.id.readBtn);
        homeBtn=findViewById(R.id.btnHome);

        textToSpeech = new TextToSpeech(this, this);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextAct = new Intent(g5AdvSpelling28.this, g5AdvSpelling29.class);
                startActivity(nextAct);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent backAct = new Intent(g5AdvSpelling28.this, g5AdvSpelling27.class);
                startActivity(backAct);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeAct = new Intent(g5AdvSpelling28.this, HomeActivity.class);
                startActivity(homeAct);
            }
        });


        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak = "After the children finished their art project, the room was messy, with paint splatters and scattered supplies everywhere.";

                textToSpeech.speak(textToSpeak,TextToSpeech.QUEUE_FLUSH, null, null);
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
    public void onInit(int status){
        if(status == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or not supported
            }
        } else {
            // Initialization failed
        }
    }

    @Override
    protected void onDestroy(){
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

}