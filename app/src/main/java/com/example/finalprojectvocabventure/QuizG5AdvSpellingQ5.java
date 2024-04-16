package com.example.finalprojectvocabventure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class QuizG5AdvSpellingQ5 extends AppCompatActivity {
    SharedPreferencesManager preferencesManager;
    Button sound,check,syno,anto;
    EditText answer;
    TextToSpeech txtToSpeech;
    dialog_wrong wrongDialog = new dialog_wrong(HomeActivity.class);
    dialog_correct correctDialog = new dialog_correct(HomeActivity.class);
    boolean synonymClicked = false;
    boolean antonymClicked = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_adv_spelling_q5);

        preferencesManager = new SharedPreferencesManager(this);

        answer = findViewById(R.id.etAnswer);
        sound = findViewById(R.id.btnSound);
        check = findViewById(R.id.btnCheck);
        syno = findViewById(R.id.btnSyno);
        anto = findViewById(R.id.btnAnto);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAnswer = answer.getText().toString().trim();
                String correctAnswer = "Small"; // correct answer

                if (!userAnswer.isEmpty()) {
                    boolean isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);

                    if (synonymClicked && isCorrect) {
                        MethodForQuizAdvSpelling();
                        // Call the method to upload the score to Firebase
                        uploadAdvSpellingScoreToFirebase();
                        showCorrectDialog();
                    } else {
                        // Call the method to upload the score to Firebase
                        uploadAdvSpellingScoreToFirebase();
                        showWrongDialog();
                    }
                } else {
                    Toast.makeText(QuizG5AdvSpellingQ5.this, "Please enter an answer.", Toast.LENGTH_SHORT).show();
                }

                // Reset flags after processing the check button
                synonymClicked = false;
                antonymClicked = false;
            }
        });

        syno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) v.getParent()).findViewById(R.id.btnSyno).setBackgroundResource(R.drawable.border_red);
                ((ViewGroup) v.getParent()).findViewById(R.id.btnAnto).setBackground(null); // Reset the other button
                synonymClicked = true;
                antonymClicked = false;
            }
        });

        anto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) v.getParent()).findViewById(R.id.btnAnto).setBackgroundResource(R.drawable.border_red);
                ((ViewGroup) v.getParent()).findViewById(R.id.btnSyno).setBackground(null); // Reset the other button
                antonymClicked = true;
                synonymClicked = false;
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
                String textToSpeak = "Small";
                txtToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void MethodForQuizAdvSpelling() {
        // Get the score for Quiz Advanced Spelling
        int currentScore = preferencesManager.getAdvSpellingScore();

        // Modify the score for Quiz Advanced Spelling
        int newScore = currentScore + 2;

        // Update the score for Quiz Advanced Spelling in SharedPreferences
        preferencesManager.setAdvSpellingScore(newScore);

        // Display the updated score in a toast
        Toast.makeText(this, "Your score is : " + newScore, Toast.LENGTH_SHORT).show();
    }
    public void uploadAdvSpellingScoreToFirebase() {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("User Accounts");
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    DatabaseReference advSpellingScoreRef = userSnapshot.getRef().child("AdvSpellingScore");

                    int score = preferencesManager.getAdvSpellingScore();

                    advSpellingScoreRef.setValue(score)
                            .addOnSuccessListener(aVoid -> {
                                // Handle successful upload
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG5AdvSpellingQ5.this, "Score uploaded to Firebase!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle failure
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG5AdvSpellingQ5.this, "Failed to upload score to Firebase", Toast.LENGTH_SHORT).show();
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
                Toast.makeText(QuizG5AdvSpellingQ5.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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