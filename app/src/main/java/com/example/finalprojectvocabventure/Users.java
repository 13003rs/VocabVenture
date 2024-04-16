package com.example.finalprojectvocabventure;

import android.content.Context;
import android.content.SharedPreferences;

public class Users {
    private static Users userObj = new Users();
    private String username, email, password, loggedUsername;
    private int gradeLevel;
    private SharedPreferences preferences;
    private int wrongDialogCount;


    private Users() {
    }

    public static Users getInstance() {
        return userObj;
    }

    public void initialize(Context context) {
        preferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        // Retrieve the grade level from shared preferences (defaulting to 0 if not found)
        gradeLevel = preferences.getInt("GradeLevel", 0);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getLoggedUsername() {
        return loggedUsername;
    }

    public void setLoggedUsername(String loggedUsername) {
        this.loggedUsername = loggedUsername;
    }

    public static void clearUserData() {
        userObj.setUsername(null);
        userObj.setEmail(null);
        userObj.setPassword(null);
        userObj.setGradeLevel(0);
        userObj.setWrongDialogCount(0); // Clear wrong dialog count
        userObj.setLoggedUsername(null);
    }

    public void setWrongDialogCount(int i) {
        this.wrongDialogCount = i;
        // Save the updated wrongDialogCount to SharedPreferences
        preferences.edit().putInt("WrongDialogCount", i).apply();
    }


    public int getCorrectDialogCount() {
        if (userObj != null) {
            return userObj.getCorrectDialogCount();
        } else {
            // Handle the case where userObj is null, return a default value or throw an exception
            return 0; // Assuming a default value of 0
        }
    }

}

