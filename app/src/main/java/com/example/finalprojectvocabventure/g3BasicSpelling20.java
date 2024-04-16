package com.example.finalprojectvocabventure;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class g3BasicSpelling20 extends AppCompatActivity {

    ImageButton nextBtn, backBtn,homeBtn,soundBtn;
    TextToSpeech txtToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_basic_spelling20);
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
                showDialog();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backAct = new Intent(g3BasicSpelling20.this, g3BasicSpelling19.class);
                startActivity(backAct);
                finish();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeAct = new Intent(g3BasicSpelling20.this, HomeActivity.class);
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
                String textToSpeaklamb = "honest";
                float speechRatelamb= 0.7f;
                txtToSpeech.setSpeechRate(speechRatelamb);
                txtToSpeech.speak(textToSpeaklamb, TextToSpeech.QUEUE_FLUSH, null, null);

                // Add a delay or pause between the two speeches if needed
                try {
                    Thread.sleep(1000); // 1000 milliseconds (1 second) delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String textToSpeakSpell = "h. o. n. e. s. t";
                float speechRateSpell = 0.5f;
                txtToSpeech.setSpeechRate(speechRateSpell);
                txtToSpeech.speak(textToSpeakSpell, TextToSpeech.QUEUE_FLUSH, null, null);

                try {
                    Thread.sleep(4500); // 1000 milliseconds (1 second) delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                txtToSpeech.setSpeechRate(speechRatelamb);
                txtToSpeech.speak(textToSpeaklamb, TextToSpeech.QUEUE_FLUSH, null, null);
            }

        });
    }
    private void showDialog() {
        dialog_quiz dialog = new dialog_quiz(g3BasicSpellingQuiz1.class);
        dialog.show(getSupportFragmentManager(), "dialog_quiz");
    }
}
