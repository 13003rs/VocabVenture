package com.example.finalprojectvocabventure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizG4InterReadingQ5 extends AppCompatActivity {
    Button btnA, btnB, btnC;
    dialog_wrong wrongDialog = new dialog_wrong(g4InterRead2.class);

    dialog_correct correctDialog = new dialog_correct(g4InterRead2.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g4_inter_reading_q5);

        preferencesManager = new SharedPreferencesManager(this);

        init();
        buttons();
    }

    public void init(){
        btnA = findViewById(R.id.btn_a);
        btnB = findViewById(R.id.btn_b);
        btnC = findViewById(R.id.btn_c);
    }

    public void buttons(){
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to upload the score to Firebase
                uploadInterReadingScoreToFirebase();
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked We say it sadly", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }

        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to upload the score to Firebase
                uploadInterReadingScoreToFirebase();
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked We say it with fear", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizInterReading();
                // Call the method to upload the score to Firebase
                uploadInterReadingScoreToFirebase();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked We say it happily", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });


    }

    public void MethodForQuizInterReading() {
        // Get the score for Quiz Intermediate Reading
        int currentScore = preferencesManager.getInterReadingScore();

        // Modify the score for Quiz Intermediate Reading
        int newScore = currentScore + 1;

        // Update the score for Quiz Intermediate Reading in SharedPreferences
        preferencesManager.setInterReadingScore(newScore);

        // Display the updated score in a toast
        Toast.makeText(this, "Your score is : " + newScore, Toast.LENGTH_SHORT).show();

    }
    public void uploadInterReadingScoreToFirebase() {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("User Accounts");
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    DatabaseReference interReadingScoreRef = userSnapshot.getRef().child("InterReadingScore");

                    int score = preferencesManager.getInterSpellingScore();

                    interReadingScoreRef.setValue(score)
                            .addOnSuccessListener(aVoid -> {
                                // Handle successful upload
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG4InterReadingQ5.this, "Score uploaded to Firebase!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle failure
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG4InterReadingQ5.this, "Failed to upload score to Firebase", Toast.LENGTH_SHORT).show();
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
                Toast.makeText(QuizG4InterReadingQ5.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}