package com.example.finalprojectvocabventure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class g5BasicSpelling7 extends AppCompatActivity {

    ImageButton nextBtn, backBtn,homeBtn,soundBtn;
    TextToSpeech txtToSpeech;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_basic_spelling7);

        init();
        buttons();
        playSound();
    }
    public void init(){
        nextBtn=findViewById(R.id.btnNext);
        backBtn=findViewById(R.id.btnBack);
        homeBtn=findViewById(R.id.btnHome);
        soundBtn = findViewById(R.id.btnSound);
    }
    public void buttons(){
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextAct = new Intent(g5BasicSpelling7.this, g5BasicSpelling8.class);
                startActivity(nextAct);
                finish();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backAct = new Intent(g5BasicSpelling7.this, g5BasicSpelling6.class);
                startActivity(backAct);
                finish();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeAct = new Intent(g5BasicSpelling7.this, HomeActivity.class);
                startActivity(homeAct);
                finish();
            }
        });
    }
    public void playSound() { //-- method for the function of the sound button
        txtToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    int language= txtToSpeech.setLanguage(Locale.ENGLISH);
                    if (language == TextToSpeech.LANG_MISSING_DATA ||
                            language == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Language data is missing or not supported
                    }
                } else {
                    // Initialization failed
                }
            }
        });
        soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak = "Saucepan";
                txtToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
}