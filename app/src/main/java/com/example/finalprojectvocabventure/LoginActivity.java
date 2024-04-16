package com.example.finalprojectvocabventure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    //Boolean isNewUser, setNewUserFlag;
    EditText username, pass;
    Users userObj = Users.getInstance(); //-- calling out this constructor to access its data
    TextView register, usernameError, passError;
    Button loginbtn;
    ToggleButton passVisi;
    DatabaseReference loginDb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://finalprojectvocabventure-eb81e-default-rtdb.firebaseio.com/");


    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstasteState) {
        super.onCreate(savedInstasteState);
        init();
        login();
        PassVisibility();
        register();
    }

    public void init() {
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.ptUsername);
        pass = findViewById(R.id.ptPassword);
        loginbtn = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister);
        passVisi = findViewById(R.id.togPassVisibility);
        usernameError = findViewById(R.id.tvLoginUNError);
        passError = findViewById(R.id.tvLoginPassError);
    }

    public void login() {
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernametxt = username.getText().toString();
                String passtxt = pass.getText().toString();

                if (usernametxt.isEmpty() || passtxt.isEmpty()) {
                    //usernameError.setText("Oops! Don't forget to enter your username or email.");
                    passError.setText("Oops! Don't forget to enter your password.");
                } else {
                    usernameError.setText("");
                    passError.setText("");
                    loginDb.child("User Accounts").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean userFound = false;

                            // loop in each user snapshot (data from firebase location)
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                // Retrieve the stored username and password from the user snapshot
                                String storedUsername = userSnapshot.child("Username").getValue(String.class);
                                String storedEmail = userSnapshot.child("Email").getValue(String.class);

                                // Check if the entered value matches either username or email
                                if (usernametxt.equals(storedUsername) || usernametxt.equals(storedEmail)) {
                                    userFound = true;

                                    // Check if the 'password' node exists for the user
                                    if (userSnapshot.hasChild("Password")) {
                                        String getPassword = userSnapshot.child("Password").getValue(String.class);

                                        // check if the password is not null and is the same to the entered password
                                        if (getPassword != null && getPassword.equals(passtxt)) {
                                            Toast.makeText(LoginActivity.this, "Successfully Logged In.", Toast.LENGTH_SHORT).show();

                                            //opens next screen/window after successfully logged in (TermsAndPolicy yung ni-cconnect ko here hehe)
                                            loginDb.child("User Accounts").child(userSnapshot.getKey()).child("LoggedIn").setValue(1);// sets the logged in user

                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                                //finish();

                                        } else {
                                            passError.setText("Looks like you've entered a wrong password");
                                        }
                                    } else {
                                        passError.setText("Oops, it looks like your password is not quite right, Try again!");
                                    }
                                    break;
                                }
                            }

                            if (!userFound) { // if the user is not found - displays not found
                                usernameError.setText("Oops! Check your username or email and try again!");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

    }


    @SuppressLint("ClickableViewAccessibility")
    public void PassVisibility() {

        //password visibility function - para sa eye icon once the user clicked.
        passVisi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // When the toggle button is pressed, show the password
                    togglePasswordVisibility(true);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // When the toggle button is released, hide the password ; parang when the user released the mouse automatically it hides the password.
                    // tago agad ng password ganurns
                    togglePasswordVisibility(false);
                }
                // Return false to allow the regular click action to happen
                return false;
            }

            //function of toggle password visibility (show or hide)
            private void togglePasswordVisibility(boolean isVisible) {
                //show or hide the password based on the eye's icon display.
                //if the isVisible is true; it is set to null (show password) ; else - it hides the password
                pass.setTransformationMethod(isVisible ? null : new PasswordTransformationMethod());
                // set the cursor at the end of the password field. like parang andun lang yung cursor as default once ni-click ni user yung eye icon.
                pass.setSelection(pass.getText().length());
            }
        });
    }


    public void register() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            // opens Register activity
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, TermsActivity.class));
            }
        });
    }
}