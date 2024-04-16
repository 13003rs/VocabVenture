package com.example.finalprojectvocabventure;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class QuizG5BasicSpellingQ5 extends AppCompatActivity {
    SharedPreferencesManager preferencesManager;
    Button sound,check,sen;
    EditText answer;
    TextToSpeech txtToSpeech;
    dialog_wrong wrongDialog = new dialog_wrong(HomeActivity.class);
    dialog_correct correctDialog = new dialog_correct(HomeActivity.class);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_basic_spelling_q5);

        preferencesManager = new SharedPreferencesManager(this);

        answer = findViewById(R.id.etAnswer);
        sound = findViewById(R.id.btnSound);
        sen = findViewById(R.id.btnSen);
        check = findViewById(R.id.btnCheck);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAnswer = answer.getText().toString().trim();
                String correctAnswer = "Souvenir"; //correct answer

                if (!userAnswer.isEmpty()) {
                    if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                        MethodForQuizBasicSpelling();
                        // Call the method to upload the score to Firebase
                        uploadBasicSpellingScoreToFirebase();
                        showCorrectDialog();
                    } else {
                        // Call the method to upload the score to Firebase
                        uploadBasicSpellingScoreToFirebase();
                        showWrongDialog();
                    }
                } else {
                    // Handle case when the user hasn't entered any answer
                    Toast.makeText(QuizG5BasicSpellingQ5.this, "Please enter an answer.", Toast.LENGTH_SHORT).show();
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
                String textToSpeak = "Souvenir";
                txtToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        sen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak = "She bought a souvenir to remember her trip.";
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

        // Display the updated score in a toast
        Toast.makeText(this, "Your score is : " + newScore, Toast.LENGTH_SHORT).show();
    }
    public void uploadBasicSpellingScoreToFirebase() {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("User Accounts");
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    DatabaseReference basicSpellingScoreRef = userSnapshot.getRef().child("BasicSpellingScore");

                    int score = preferencesManager.getBasicSpellingScore();

                    basicSpellingScoreRef.setValue(score)
                            .addOnSuccessListener(aVoid -> {
                                // Handle successful upload
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG5BasicSpellingQ5.this, "Score uploaded to Firebase!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle failure
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG5BasicSpellingQ5.this, "Failed to upload score to Firebase", Toast.LENGTH_SHORT).show();
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
                Toast.makeText(QuizG5BasicSpellingQ5.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showCorrectDialog() {
        correctDialog.show(getSupportFragmentManager(), "correct_dialog");
    }

    public void showWrongDialog() {
        wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
    }
}