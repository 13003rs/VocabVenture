package com.example.finalprojectvocabventure;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class PasswordActivity extends AppCompatActivity {
    EditText password,confirmPass;
    Users userObj=Users.getInstance(); //-- calling out this constructor to access its data
    AlertDialog.Builder adBuilder;
    TextView errorPass,errorConfirmPass;
    ToggleButton passVisibility, confirmPassVisibility;
    DatabaseReference registerDb= FirebaseDatabase.getInstance().getReferenceFromUrl("https://finalprojectvocabventure-eb81e-default-rtdb.firebaseio.com/");
    Button register,back,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        init();
        Toast.makeText(getApplicationContext(), String.valueOf(userObj.getGradeLevel()), Toast.LENGTH_SHORT).show();
        userPassword();
        backButton();
        loginButton();
    }
    public void init(){
        password=findViewById(R.id.etPassword);
        confirmPass=findViewById(R.id.etConfirmPass);
        register=findViewById(R.id.btnPassRegister);
        back=findViewById(R.id.btnPassBack);
        login=findViewById(R.id.btnPassLogin);
        errorPass=findViewById(R.id.tvErrorPass);
        errorConfirmPass=findViewById(R.id.tvErrorConfirmPass);
        passVisibility=findViewById(R.id.togBtnRegisterPassVisi);
        confirmPassVisibility=findViewById(R.id.togBtnConfirmPass);
        adBuilder=new AlertDialog.Builder(this);
    }
    public void userPassword(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPass = password.getText().toString();
                String confirmPassword= confirmPass.getText().toString();

                if(userPass.isEmpty() || confirmPassword.isEmpty()){
                    errorPass.setText("Oops, please input all fields!");
                    errorConfirmPass.setText("Oops, please input all fields!");
                    password.setText("");
                    confirmPass.setText("");
                } else if(userPass.length()<8){
                    errorPass.setText("Oops! Your password should be longer. Make it at least 8 characters.");
                    errorConfirmPass.setText("");
                } else if (!confirmPassword.equals(userPass)) {
                    errorConfirmPass.setText("Whoops! Passwords didn't match. Give it another try!");
                    errorPass.setText("");
                }
                else {
                    userObj.setPassword(userPass);
                    writeDataToDB();

                }
            }
        });
    }
    public void writeDataToDB(){
        //--  to generate a unique key as the userID of the users
        String userId = registerDb.child("User Accounts").push().getKey();

        registerDb.child("User Accounts").child(userId).child("Username").setValue(userObj.getUsername());
        registerDb.child("User Accounts").child(userId).child("Email").setValue(userObj.getEmail());
        registerDb.child("User Accounts").child(userId).child("Grade Level").setValue(userObj.getGradeLevel());
        registerDb.child("User Accounts").child(userId).child("Password").setValue(userObj.getPassword());
        registerDb.child("User Accounts").child(userId).child("LoggedIn").setValue(0);
        registerDb.child("User Accounts").child(userId).child("BasicGrammarScore").setValue(0);
        registerDb.child("User Accounts").child(userId).child("InterGrammarScore").setValue(0);
        registerDb.child("User Accounts").child(userId).child("AdvGrammarScore").setValue(0);
        registerDb.child("User Accounts").child(userId).child("BasicReadingScore").setValue(0);
        registerDb.child("User Accounts").child(userId).child("InterReadingScore").setValue(0);
        registerDb.child("User Accounts").child(userId).child("AdvReadingScore").setValue(0);
        registerDb.child("User Accounts").child(userId).child("BasicSpellingScore").setValue(0);
        registerDb.child("User Accounts").child(userId).child("InterSpellingScore").setValue(0);
        registerDb.child("User Accounts").child(userId).child("AdvSpellingScore").setValue(0);

        adBuilder.setTitle("Information");
        adBuilder.setMessage("Registration Successful!");
        adBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent loginAct=new Intent(getBaseContext(),LoginActivity.class);
                startActivity(loginAct);
                finish();
            }
        });
        adBuilder.show();
    }
    public void backButton(){ //-- to go back to the previous activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prevAct=new Intent(getBaseContext(),GradeLevelActivity.class);
                startActivity(prevAct);
            }
        });
    }
    public void loginButton(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginAct=new Intent(getBaseContext(),LoginActivity.class);
                startActivity(loginAct);

            }
        });
    }
    public void togglePasswordVisibility(View view) {
        ToggleButton toggleButton = (ToggleButton) view;
        boolean isChecked = toggleButton.isChecked();
        togglePasswordVisibility(password, isChecked);
    }

    public void toggleConfirmPasswordVisibility(View view) {
        ToggleButton toggleButton = (ToggleButton) view;
        boolean isChecked = toggleButton.isChecked();
        toggleConfirmPassVisibility(confirmPass, isChecked);
    }

    private void togglePasswordVisibility(EditText password,boolean isVisible) {
        // Show or hide the password based on the toggle state.
        password.setTransformationMethod(isVisible ? null : new PasswordTransformationMethod());
        // Set the cursor at the end of the password field.
        password.setSelection(password.getText().length());
    }
    private void toggleConfirmPassVisibility(EditText confirmPass,boolean isVisible) {
        // Show or hide the password based on the toggle state.
        confirmPass.setTransformationMethod(isVisible ? null : new PasswordTransformationMethod());
        // Set the cursor at the end of the password field.
        confirmPass.setSelection(confirmPass.getText().length());
    }
}


