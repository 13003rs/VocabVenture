package com.example.finalprojectvocabventure;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesManager {

    private Users userObj = Users.getInstance();
    private DatabaseReference userDb = FirebaseDatabase.getInstance().getReference("User Accounts");
    private static final String PREF_NAME = "MyPrefs";
    private static final String BASICREADINGQUIZ = "basicreadingscore";
    private static final String BASICSPELLINGQUIZ = "basicspelling";
    private static final String BASICGRAMMARQUIZ = "basicgrammar";
    private static final String INTERREADINGQUIZ = "interreadingscore";
    private static final String INTERSPELLINGQUIZ = "interspelling";
    private static final String INTERGRAMMARQUIZ = "intergrammar";
    private static final String ADVREADINGQUIZ = "adverreadingscore";
    private static final String ADVSPELLINGQUIZ = "advspelling";
    private static final String ADVGRAMMARQUIZ = "advgrammar";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // Constructor
    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //------------BASIC
    public int getBasicReadingScore() {
        // Get the score for Quiz 1 from SharedPreferences
        return sharedPreferences.getInt(BASICREADINGQUIZ, 0);
    }

    public void setBasicReadingScore(int score) {
        // Set the score for Quiz 1 in SharedPreferences
        editor.putInt(BASICREADINGQUIZ, score);
        editor.apply();
    }

    public int getBasicSpellingScore() {
        // Get the score for Quiz 2 from SharedPreferences
        return sharedPreferences.getInt(BASICSPELLINGQUIZ, 0);
    }

    public void setBasicSpellingScore(int score) {
        // Set the score for Quiz 2 in SharedPreferences
        editor.putInt(BASICSPELLINGQUIZ, score);
        editor.apply();
    }

    public int getBasicGrammarScore() {
        // Get the score for Quiz 2 from SharedPreferences
        return sharedPreferences.getInt(BASICGRAMMARQUIZ, 0);
    }

    public void setBasicGrammarScore(int score) {
        // Set the score for Quiz 2 in SharedPreferences
        editor.putInt(BASICGRAMMARQUIZ, score);
        editor.apply();
    }

    //------------------Intermediate
    public int getInterReadingScore() {
        // Get the score for Quiz 1 from SharedPreferences
        return sharedPreferences.getInt(INTERREADINGQUIZ, 0);
    }

    public void setInterReadingScore(int score) {
        // Set the score for Quiz 1 in SharedPreferences
        editor.putInt(INTERREADINGQUIZ, score);
        editor.apply();
    }

    public int getInterSpellingScore() {
        // Get the score for Quiz 2 from SharedPreferences
        return sharedPreferences.getInt(INTERSPELLINGQUIZ, 0);
    }

    public void setInterSpellingScore(int score) {
        // Set the score for Quiz 2 in SharedPreferences
        editor.putInt(INTERSPELLINGQUIZ, score);
        editor.apply();
    }

    public int getInterGrammarScore() {
        // Get the score for Quiz 2 from SharedPreferences
        return sharedPreferences.getInt(INTERGRAMMARQUIZ, 0);
    }

    public void setInterGrammarScore(int score) {
        // Set the score for Quiz 2 in SharedPreferences
        editor.putInt(INTERGRAMMARQUIZ, score);
        editor.apply();
    }

    //--------------Advanced
    public int getAdvReadingScore() {
        // Get the score for Quiz 1 from SharedPreferences
        return sharedPreferences.getInt(ADVREADINGQUIZ, 0);
    }

    public void setAdvReadingScore(int score) {
        // Set the score for Quiz 1 in SharedPreferences
        editor.putInt(ADVREADINGQUIZ, score);
        editor.apply();
    }

    public int getAdvSpellingScore() {
        // Get the score for Quiz 2 from SharedPreferences
        return sharedPreferences.getInt(ADVSPELLINGQUIZ, 0);
    }

    public void setAdvSpellingScore(int score) {
        // Set the score for Quiz 2 in SharedPreferences
        editor.putInt(ADVSPELLINGQUIZ, score);
        editor.apply();
    }

    public int getAdvGrammarScore() {
        // Get the score for Quiz 2 from SharedPreferences
        return sharedPreferences.getInt(ADVGRAMMARQUIZ, 0);
    }

    public void setAdvGrammarScore(int score) {
        // Set the score for Quiz 2 in SharedPreferences
        editor.putInt(ADVGRAMMARQUIZ, score);
        editor.apply();
    }

}
