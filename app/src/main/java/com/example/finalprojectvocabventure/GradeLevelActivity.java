package com.example.finalprojectvocabventure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GradeLevelActivity extends AppCompatActivity {
    Users userObj = Users.getInstance();

    Button grade1,grade2,grade3,grade4,grade5,grade6,login,back;
AlertDialog.Builder adBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gradelevel);
        init();
        backButton();
        loginButton();
    }
    public void init(){
grade3=findViewById(R.id.btnGrade3);
grade4=findViewById(R.id.btnGrade4);
grade5=findViewById(R.id.btnGrade5);
back=findViewById(R.id.btnGradeLvlBack);
login=findViewById(R.id.btnGradeLvlLogin);
adBuilder=new AlertDialog.Builder(this);
    }
public void userGradeLevel(View v){ // --  for getting the user's grade level
        // -- for checking the clicked buttons and getting the grade level through it
        if (v==grade3){
            userObj.setGradeLevel(Integer.parseInt(grade3.getText().toString()));
            Toast.makeText(getApplicationContext(), String.valueOf(userObj.getGradeLevel()), Toast.LENGTH_SHORT).show(); //--for debugging
            showConfirmationDialog();
        }
        else if (v==grade4){
            userObj.setGradeLevel(Integer.parseInt(grade4.getText().toString()));
            Toast.makeText(getApplicationContext(), String.valueOf(userObj.getGradeLevel()), Toast.LENGTH_SHORT).show();
            showConfirmationDialog();
        }
        else if (v==grade5){
            userObj.setGradeLevel(Integer.parseInt(grade5.getText().toString()));
            Toast.makeText(getApplicationContext(), String.valueOf(userObj.getGradeLevel()), Toast.LENGTH_SHORT).show();
            showConfirmationDialog();
        }
}
//-- method for the confirmation dialog to give the user a choice whether if they are sure or not with their chosen grade level
private void showConfirmationDialog(){
        adBuilder.setTitle("Confirmation");
        adBuilder.setMessage("Are you sure with your selected grade level?");
        adBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { //-- if the user is sure with their choice
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), String.valueOf(userObj.getGradeLevel()), Toast.LENGTH_SHORT).show();
                Intent nextActivity=new Intent(getBaseContext(), PasswordActivity.class);
                startActivity(nextActivity);
                finish();
            }
        });
        adBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                userObj.setGradeLevel(0);
            }
        });
    Toast.makeText(GradeLevelActivity.this, String.valueOf(userObj.getGradeLevel()), Toast.LENGTH_SHORT).show(); //-- for debugging
    adBuilder.show();
}
    public void backButton(){ //-- to go back to the previous activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prevAct=new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(prevAct);
            }
        });
    }
    public void loginButton(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}