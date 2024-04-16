package com.example.finalprojectvocabventure;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class QuizG3BasicReadQ5 extends AppCompatActivity {

    TextView btnBoy, btnHill, btnMother;
    ImageView motherIcon;
    TextToSpeech txtToSpeech;
    dialog_wrong wrongDialog = new dialog_wrong(HomeActivity.class);
    dialog_correct correctDialog = new dialog_correct(HomeActivity.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g3_basic_read_q5);

        init();
        initializeTextToSpeech();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);
    }

    public void init(){
        btnBoy = findViewById(R.id.btn_boy);
        btnHill = findViewById(R.id.btn_hill);
        btnMother = findViewById(R.id.btn_mother);
        motherIcon = findViewById(R.id.icon_mother);

    }

    public void initializeTextToSpeech(){
        txtToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int langResult = txtToSpeech.setLanguage(Locale.US);

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
        btnHill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked hill", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
                // Call the method to upload the score to Firebase
                uploadBasicReadingScoreToFirebase();
            }
        });

        btnBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked boy", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
                // Call the method to upload the score to Firebase
                uploadBasicReadingScoreToFirebase();
            }
        });

        btnMother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizBasicReading();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked mother", Toast.LENGTH_SHORT).show();
                    }
                }, 200);

                // Call the method to upload the score to Firebase
                uploadBasicReadingScoreToFirebase();
            }
        });

        motherIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = " My mother makes the best cookies for me.";
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
        // Get the score for Quiz 1
        int currentScore = preferencesManager.getBasicReadingScore();

        // Modify the score for Quiz 1
        int newScore = currentScore + 2;

        // Update the score for Quiz 1 in SharedPreferences
        preferencesManager.setBasicReadingScore(newScore);
    }

    // Example of how to upload the basic reading score to Firebase
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
                                Toast.makeText(QuizG3BasicReadQ5.this, "Score saved!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle failure
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG3BasicReadQ5.this, "Failed to save score", Toast.LENGTH_SHORT).show();
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
                Toast.makeText(QuizG3BasicReadQ5.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
