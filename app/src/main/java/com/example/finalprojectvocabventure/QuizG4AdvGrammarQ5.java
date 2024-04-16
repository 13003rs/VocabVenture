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

public class QuizG4AdvGrammarQ5 extends AppCompatActivity {

    Button btnFirst, btnSecond, btnThird;

    dialog_wrong wrongDialog = new dialog_wrong(HomeActivity.class);

    dialog_correct correctDialog = new dialog_correct(HomeActivity.class);
    SharedPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g4_adv_grammar_q5);

        init();
        buttons();

        preferencesManager = new SharedPreferencesManager(this);
    }

    public void init(){
        btnFirst = findViewById(R.id.btnFirst);
        btnSecond = findViewById(R.id.btnSecond);
        btnThird = findViewById(R.id.btnThird);
    }

    public void buttons() {
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAdvGrammarScoreToFirebase();
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked last vacation", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAdvGrammarScoreToFirebase();
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked at six o’clock am", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });

        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodForQuizAdvGrammar();
                // Call the method to upload the score to Firebase
                uploadAdvGrammarScoreToFirebase();
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "You clicked near a glass window", Toast.LENGTH_SHORT).show();
                    }
                }, 200); // Adjust the delay duration as needed
            }
        });
    }

    public void MethodForQuizAdvGrammar() {
        // Get the score for Quiz Advanced Grammar
        int currentScore = preferencesManager.getAdvGrammarScore();

        // Modify the score for Quiz Advanced Grammar
        int newScore = currentScore + 2;

        // Update the score for Quiz Advanced Grammar in SharedPreferences
        preferencesManager.setAdvGrammarScore(newScore);
    }

    private void uploadAdvGrammarScoreToFirebase() {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("User Accounts");
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    DatabaseReference advGrammarScoreRef = userSnapshot.getRef().child("AdvGrammarScore");

                    int score = preferencesManager.getAdvGrammarScore();

                    advGrammarScoreRef.setValue(score)
                            .addOnSuccessListener(aVoid -> {
                                // Handle successful upload
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG4AdvGrammarQ5.this, "Score uploaded to Firebase!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle failure
                                // You can add logging or additional logic here
                                Toast.makeText(QuizG4AdvGrammarQ5.this, "Failed to upload score to Firebase", Toast.LENGTH_SHORT).show();
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
                Toast.makeText(QuizG4AdvGrammarQ5.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}