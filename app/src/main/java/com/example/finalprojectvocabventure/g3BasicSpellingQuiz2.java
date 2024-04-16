package com.example.finalprojectvocabventure;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class g3BasicSpellingQuiz2 extends AppCompatActivity {
    ImageButton clearBtn,soundBtn;
    Button L,M,H,A,B,checkBtn;
    TextToSpeech txtToSpeech;
    TextView ansBox1,ansBox2,ansBox3,ansBox4;
    SharedPreferencesManager preferencesManager;
    dialog_wrong wrongDialog = new dialog_wrong(g3BasicSpellingQuiz3.class);
    dialog_correct correctDialog = new dialog_correct(g3BasicSpellingQuiz3.class);
    private String correctAns="LAMB"; //--Initializing the correct answer
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_basic_spelling_quiz2);
        init();
        playSound();
        setClearBtn();
    }
    public void init(){
        L=findViewById(R.id.btnLG3BasicSpellQuiz2);
        M=findViewById(R.id.btnMG3BasicSpellQuiz2);
        H=findViewById(R.id.btnHG3BasicSpellQuiz2);
        A=findViewById(R.id.btnAG3BasicSpellQuiz2);
        B=findViewById(R.id.btnBG3BasicSpellQuiz2);

        clearBtn=findViewById(R.id.btnClearG3BasicSpell);
        checkBtn=findViewById(R.id.btnCheckG3BasicSpell);
        soundBtn=findViewById(R.id.btnLamb);

        ansBox1=findViewById(R.id.g3BasicSpellAB1);
        ansBox2=findViewById(R.id.g3BasicSpellAB2);
        ansBox3=findViewById(R.id.g3BasicSpellAB3);
        ansBox4=findViewById(R.id.g3BasicSpellAB4);

        //-- setting the onclick listener for each buttons using lambda
        L.setOnClickListener(v->clickedLetterButton(L));
        M.setOnClickListener(v->clickedLetterButton(M));
        H.setOnClickListener(v->clickedLetterButton(H));
        A.setOnClickListener(v->clickedLetterButton(A));
        B.setOnClickListener(V->clickedLetterButton(B));

        preferencesManager = new SharedPreferencesManager(this);
    }

    public void setClearBtn(){
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAnswerBoxes();
                currentIndex = 0; // Reset the index to 0 when the clear button is clicked
            }
        });
    }
    public void playSound() { //-- method for the function of the sound button
        txtToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    int language= txtToSpeech.setLanguage(Locale.ENGLISH);
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
                String textToSpeak = "Lamb";
                txtToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void clickedLetterButton(Button letterButton) {
        // Check if the current index is within the correct answer length
        if (currentIndex < correctAns.length()) {
            // Get the current answer box
            TextView answerBox = getAnswerBox(currentIndex);
            // Check if the answer box is empty before setting the letter
            if (answerBox.getText().toString().isEmpty()) {
                // Sets the inputted letter by the user and move to the next index
                answerBox.setText(letterButton.getText());
                currentIndex++;

                // Check if the user has completed the word
                checkBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Check if all answer boxes are filled
                        if (areAllAnswerBoxesFilled()) {
                            Toast.makeText(g3BasicSpellingQuiz2.this, "Please complete the word before checking.", Toast.LENGTH_SHORT).show();
                        }
                        if (currentIndex == correctAns.length()) {
                            String enteredLetters = getInputtedLetters();
                            if (enteredLetters.equals(correctAns)) {
                                correctDialog.show(getSupportFragmentManager(), "correct_dialog");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        MethodForQuizBasicSpelling();
                                        // Clear the answer boxes for the next round
                                        clearAnswerBoxes();
                                    }
                                }, 2000); // Delay in milliseconds (e.g., 2000 milliseconds = 2 seconds)
                            } else {
                                wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        clearAnswerBoxes();
                                    }
                                }, 2000); // Delay in milliseconds (e.g., 2000 milliseconds = 2 seconds)
                            }
                        }
                    }
                });
            }
        }
    }
    private TextView getAnswerBox(int index) {
        // Return the appropriate answer box based on the index
        switch (index) {
            case 0:
                return ansBox1;
            case 1:
                return ansBox2;
            case 2:
                return ansBox3;
            case 3:
                return ansBox4;
            default:
                return null;
        }
    }
    private boolean areAllAnswerBoxesFilled() {
        // Check if any of the answer boxes is empty
        return ansBox1.getText().toString().isEmpty() ||
                ansBox2.getText().toString().isEmpty() ||
                ansBox3.getText().toString().isEmpty() ||
                ansBox4.getText().toString().isEmpty();
    }
    private String getInputtedLetters() {
        StringBuilder enteredLetters = new StringBuilder();

        // Loop through the answer boxes and append the entered letters
        for (int i = 0; i < correctAns.length(); i++) {
            TextView answerBox = getAnswerBox(i);
            enteredLetters.append(answerBox.getText().toString());
        }

        return enteredLetters.toString();
    }
    private void clearAnswerBoxes() { //-- to clear the answerboxes
        ansBox1.setText("");
        ansBox2.setText("");
        ansBox3.setText("");
        ansBox4.setText("");
    }
    public void MethodForQuizBasicSpelling() {
        int currentScore = preferencesManager.getBasicSpellingScore();
        int newScore = currentScore + 2;
        preferencesManager.setBasicSpellingScore(newScore);

    }

}
