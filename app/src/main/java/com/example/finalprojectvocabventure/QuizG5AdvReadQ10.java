package com.example.finalprojectvocabventure;

import android.os.Bundle;
import android.os.Handler;
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

public class QuizG5AdvReadQ10 extends AppCompatActivity {

    Button btnLeo, btnArchie, btnDavid;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG5AdvReadQ9.class);
    dialog_correct correctDialog = new dialog_correct(QuizG5AdvReadQ9.class);
    SharedPreferencesManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g5_adv_read_q10);

        init();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);
    }

    public void init(){
        btnLeo = findViewById(R.id.leo);
        btnArchie = findViewById(R.id.archie);
        btnDavid = findViewById(R.id.david);
    }

    public void buttons(){
        btnLeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAdvReadingScoreToFirebase();
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Leo", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnDavid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAdvReadingScoreToFirebase();
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked David", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });

        btnArchie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvReading();
                uploadAdvReadingScoreToFirebase();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked Archie", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }

    public void MethodForQuizAdvReading() {
        SharedPreferencesManager preferencesManager = new SharedPreferencesManager(getApplicationContext());
        // Get the score for Quiz Intermediate Grammar
        int currentScore = preferencesManager.getAdvReadingScore();

        // Modify the score for Quiz Intermediate Grammar
        int newScore = currentScore + 1;

        // Update the score for Quiz Intermediate Grammar in SharedPreferences
        preferencesManager.setAdvReadingScore(newScore);
    }

    private void uploadAdvReadingScoreToFirebase() {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("User Accounts");
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    DatabaseReference advReadingScoreRef = userSnapshot.getRef().child("AdvReadingScore");

                    int score = preferencesManager.getAdvReadingScore();

                    advReadingScoreRef.setValue(score)
                            .addOnSuccessListener(aVoid -> {
                                // Handle successful upload
                                // You can add logging or additional logic here
                                Toast.makeText(getApplicationContext(), "Score uploaded to Firebase!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle failure
                                // You can add logging or additional logic here
                                Toast.makeText(getApplicationContext(), "Failed to upload score to Firebase", Toast.LENGTH_SHORT).show();
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