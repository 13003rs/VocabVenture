package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class QuizG5BasicReadQ5 extends AppCompatActivity {
    Button btnChalk, btnWalk, btnTalk, sound1;
    TextToSpeech txtToSpeech;
    dialog_wrong wrongDialog = new dialog_wrong(HomeActivity.class);
    dialog_correct correctDialog = new dialog_correct(HomeActivity.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_read_q5);

        init();
        initializeTextToSpeech();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);
    }

    public void init(){
        btnChalk = findViewById(R.id.btn_chalk);
        btnTalk = findViewById(R.id.btn_talk);
        btnWalk = findViewById(R.id.btn_walk);
        sound1 = findViewById(R.id.btn_sound2);
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
        btnChalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadBasicReadingScoreToFirebase();
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked chalk", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadBasicReadingScoreToFirebase();
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked walk", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                uploadBasicReadingScoreToFirebase();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked talk", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        sound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = "talks. After a long day, we talk about what happened in our day. talk";
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

    private void uploadBasicReadingScoreToFirebase() {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("User Accounts");
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    DatabaseReference basicReadingScoreRef = userSnapshot.getRef().child("BasicReadingScore");

                    int score = preferencesManager.getBasicReadingScore();

                    basicReadingScoreRef.setValue(score)
                            .addOnSuccessListener(aVoid -> {
                                // Handle successful upload
                                // You can add logging or additional logic here
                                Toast.makeText(getApplicationContext(), "Score saved!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle failure
                                // You can add logging or additional logic here
                                Toast.makeText(getApplicationContext(), "Failed to save score", Toast.LENGTH_SHORT).show();
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}