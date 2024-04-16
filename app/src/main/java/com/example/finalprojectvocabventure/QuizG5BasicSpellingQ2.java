package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class QuizG5BasicSpellingQ2 extends AppCompatActivity {
    SharedPreferencesManager preferencesManager;
    Button sound,check,sen;
    EditText answer;
    TextToSpeech txtToSpeech;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5BasicSpellingQ3.class);
    dialog_correct correctDialog = new dialog_correct(QuizG5BasicSpellingQ3.class);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_spelling_q2);

        preferencesManager = new SharedPreferencesManager(this);

        answer = findViewById(R.id.etAnswer);
        sound = findViewById(R.id.btnSound);
        sen = findViewById(R.id.btnSen);
        check = findViewById(R.id.btnCheck);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAnswer = answer.getText().toString().trim();
                String correctAnswer = "Applaud"; //correct answer

                if (!userAnswer.isEmpty()) {
                    if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                        MethodForQuizBasicSpelling();
                        showCorrectDialog();
                    } else {
                        showWrongDialog();
                    }
                } else {
                    // Handle case when the user hasn't entered any answer
                    Toast.makeText(QuizG5BasicSpellingQ2.this, "Please enter an answer.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        playSound();
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
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak = "Applaud";
                txtToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        sen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak = "The audience began to applaud after the performance.";
                txtToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void MethodForQuizBasicSpelling() {
        // Get the score for Quiz Basic Spelling
        int currentScore = preferencesManager.getBasicSpellingScore();

        // Modify the score for Quiz Basic Spelling
        int newScore = currentScore + 2;

        // Update the score for Quiz Basic Spelling in SharedPreferences
        preferencesManager.setBasicSpellingScore(newScore);
    }
    public void showCorrectDialog() {
        correctDialog.show(getSupportFragmentManager(), "correct_dialog");
    }

    public void showWrongDialog() {
        wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
    }
}