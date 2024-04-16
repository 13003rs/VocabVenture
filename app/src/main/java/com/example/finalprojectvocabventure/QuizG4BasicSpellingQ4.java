package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuizG4BasicSpellingQ4 extends AppCompatActivity {
     int presCounter = 0;
     int maxPresCounter = 7;
     String[] keys = {"G", "F", "R", "I", "L","A","E"};
     ArrayList<TextView> clickedKeys = new ArrayList<>();
     String textAnswer = "FRAGILE";
     SharedPreferencesManager preferencesManager;
    TextView  textTitle;
    Animation smallbigforth;
    Button check;
    ImageButton cancel;
    dialog_wrong wrongDialog = new dialog_wrong(QuizG4BasicSpellingQ5.class);
    dialog_correct correctDialog = new dialog_correct(QuizG4BasicSpellingQ5.class);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_g4_basic_spelling_q4);

        preferencesManager = new SharedPreferencesManager(this);

        //remove all user input
        cancel = findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presCounter = 0;
                EditText editText = findViewById(R.id.editText);
                editText.setText("");

                LinearLayout linearLayout1 = findViewById(R.id.firstLineLayout);
                LinearLayout linearLayout2 = findViewById(R.id.secondLineLayout);

                linearLayout1.removeAllViews();
                linearLayout2.removeAllViews();

                String[] firstLineKeys = Arrays.copyOfRange(keys, 0, 3);
                String[] secondLineKeys = Arrays.copyOfRange(keys, 3, 7);


                for (String key : firstLineKeys) {
                    addView(linearLayout1, key, editText);
                }

                for (String key : secondLineKeys) {
                    addView(linearLayout2, key, editText);
                }
            }
        });
        //for validations
        check = findViewById(R.id.btnCheck);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presCounter == maxPresCounter) {
                    doValidate();
                } else {
                    Toast.makeText(QuizG4BasicSpellingQ4.this, "Please complete entering the word.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //animation
        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);

        keys = shuffleArray(keys);

        String[] firstLineKeys = Arrays.copyOfRange(keys, 0, 3);
        String[] secondLineKeys = Arrays.copyOfRange(keys, 3, 7);


        for (String key : firstLineKeys) {
            addView(((LinearLayout) findViewById(R.id.firstLineLayout)), key, ((EditText) findViewById(R.id.editText)));
        }

        for (String key : secondLineKeys) {
            addView(((LinearLayout) findViewById(R.id.secondLineLayout)), key, ((EditText) findViewById(R.id.editText)));
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) findViewById(R.id.secondLineLayout).getLayoutParams();
            layoutParams.topMargin = 20;
            findViewById(R.id.secondLineLayout).setLayoutParams(layoutParams);
        }
    }

    public String[] shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    //keys layout
    public void addView(ViewGroup viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.rightMargin = 30;
        layoutParams.leftMargin = 30;

        final TextView textView = new TextView(this);
        textView.setLayoutParams(layoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bglet));
        textView.setTextColor(this.getResources().getColor(R.color.cream));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(20);

        Typeface typeface = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            typeface = getResources().getFont(R.font.andika);
        }

        textTitle = findViewById(R.id.textTitle);
        textTitle.setTypeface(typeface);

        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (presCounter < maxPresCounter && !clickedKeys.contains(textView)) {
                    if (presCounter == 0)
                        editText.setText("");

                    editText.setText(editText.getText().toString() + text);
                    textView.startAnimation(smallbigforth);
                    textView.animate().alpha(0).setDuration(300);
                    presCounter++;

                    clickedKeys.add(textView); //for the keys to be not clicked again
                }
            }
        });

        viewParent.addView(textView);

        // Adjust top margin for the second layout
        if (viewParent.getId() == R.id.secondLineLayout) {
            layoutParams.topMargin = 20; // Set your desired top margin value here
            textView.setLayoutParams(layoutParams);
        }
    }

    public void doValidate() {
        EditText editText = findViewById(R.id.editText);
        String userInput = editText.getText().toString().trim();

        if (!userInput.isEmpty()) {
            if (userInput.equalsIgnoreCase(textAnswer)) {
                MethodForQuizBasicSpelling();
                showCorrectDialog();
            } else {
                showWrongDialog();
            }
        } else {
            Toast.makeText(this, "Please enter a word.", Toast.LENGTH_SHORT).show();
        }

        String[] shuffledKeys1 = shuffleArray(Arrays.copyOfRange(keys, 0, 3));
        String[] shuffledKeys2 = shuffleArray(Arrays.copyOfRange(keys, 3, 7));

        LinearLayout linearLayout1 = findViewById(R.id.firstLineLayout);
        LinearLayout linearLayout2 = findViewById(R.id.secondLineLayout);

        linearLayout1.removeAllViews();
        linearLayout2.removeAllViews();

        for (String key : shuffledKeys1) {
            addView(linearLayout1, key, editText);
        }

        for (String key : shuffledKeys2) {
            addView(linearLayout2, key, editText);
        }
    }

    public void MethodForQuizBasicSpelling() {
        int currentScore = preferencesManager.getBasicSpellingScore();
        int newScore = currentScore + 2;
        preferencesManager.setBasicSpellingScore(newScore);
    }

    public void showCorrectDialog() {
        correctDialog.show(getSupportFragmentManager(), "correct_dialog");
    }

    public void showWrongDialog() {
        wrongDialog.show(getSupportFragmentManager(), "wrong_dialog");
    }
}
