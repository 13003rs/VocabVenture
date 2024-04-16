package com.example.finalprojectvocabventure;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
public class g3AdvSpellingQuiz5 extends AppCompatActivity {
    TextToSpeech txtToSpeech;
    SharedPreferencesManager preferencesManager;
    EditText ansBox;
    ImageButton soundBtn, clearBtn;
    Button checkBtn;
    dialog_wrong wrongDialog = new dialog_wrong(HomeActivity.class);
    dialog_correct correctDialog = new dialog_correct(HomeActivity.class);

    private String correctAns = "DISHONEST"; //--Initializing the correct answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_adv_spelling_quiz5);
        init();
        playSound();
        setClearBtn();
        checkAnswer(); // Call checkAnswer method in onCreate
    }

    public void init() {
        soundBtn = findViewById(R.id.btnG3AdvanceSpellSound);
        checkBtn = findViewById(R.id.btnCheckG3AdvSpellQuiz);
        clearBtn = findViewById(R.id.btnClearG3AdvSpellQuiz);
        ansBox = findViewById(R.id.etTxtAnsBox);

        preferencesManager = new SharedPreferencesManager(this);
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

        soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSpeak = "Dishonest";
                txtToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public void setClearBtn() {
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ansBox.setText("");
            }
        });
    }

    public void checkAnswer() {
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the EditText field is empty before proceeding
                if (isEditTextEmpty()) {
                    // Show a prompt to the user (you can use Toast or a custom dialog)
                    // For example, using Toast:
                    Toast.makeText(g3AdvSpellingQuiz5.this, "Hey there, please fill in the answer.", Toast.LENGTH_SHORT).show();
                } else {
                    // User has entered an answer, proceed to check
                    getUserAnswer();
                }
            }
        });
    }
    public void getUserAnswer() {
        String userAnswer = ansBox.getText().toString();
        if (!userAnswer.isEmpty()) {
            if (userAnswer.equalsIgnoreCase(correctAns)) {
                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MethodForQuizAdvanceSpelling();
                        uploadAdvSpellingScoreToFirebase();
                    }
                }, 2000); // Delay in milliseconds (e.g., 2000 milliseconds = 2 seconds)
            } else {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        uploadAdvSpellingScoreToFirebase();
                    }
                }, 2000); // Delay in milliseconds (e.g., 2000 milliseconds = 2 seconds)
            }
        }
    }

    public void MethodForQuizAdvanceSpelling() {
        int currentScore = preferencesManager.getAdvSpellingScore();
        int newScore = currentScore + 2;
        preferencesManager.setAdvSpellingScore(newScore);
    }
    private boolean isEditTextEmpty() {
        // Check if the EditText field is empty
        return ansBox.getText().toString().trim().isEmpty();
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
                                Toast.makeText(g3AdvSpellingQuiz5.this, "Score uploaded to Firebase!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Handle failure
                                // You can add logging or additional logic here
                                Toast.makeText(g3AdvSpellingQuiz5.this, "Failed to upload score to Firebase", Toast.LENGTH_SHORT).show();
                            });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
                Toast.makeText(g3AdvSpellingQuiz5.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

