package com.example.finalprojectvocabventure;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Locale;
public class g3AdvSpellingQuiz2 extends AppCompatActivity {
    TextToSpeech txtToSpeech;
    SharedPreferencesManager preferencesManager;
    EditText ansBox;
    ImageButton soundBtn, clearBtn;
    Button checkBtn;
    dialog_wrong wrongDialog = new dialog_wrong(g3AdvSpellingQuiz3.class);
    dialog_correct correctDialog = new dialog_correct(g3AdvSpellingQuiz3.class);

    private String correctAns = "DISAGREE"; //--Initializing the correct answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_adv_spelling_quiz2);
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
                String textToSpeak = "Mistreat";
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
                    Toast.makeText(g3AdvSpellingQuiz2.this, "Hey there, please fill in the answer.", Toast.LENGTH_SHORT).show();
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
                    }
                }, 2000); // Delay in milliseconds (e.g., 2000 milliseconds = 2 seconds)
            } else {
                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Handle the wrong answer case
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
}
