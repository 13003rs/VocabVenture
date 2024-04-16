package com.example.finalprojectvocabventure;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    Users userObj=Users.getInstance(); //-- calling out this constructor to access its data
    EditText un,email;
    Button proceed,loginButton,backButton;
    TextView errorUsername;
    DatabaseReference userAccountReference=FirebaseDatabase.getInstance().getReference("User Accounts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();
        userInput();
        loginButton();
        backButton();
    }
    // - - - -  Method for initializing the variables
    public void init(){
        un=findViewById(R.id.etSetUsername);
        email= findViewById(R.id.etSetEmail);
        proceed=findViewById(R.id.btnProceed);
        loginButton=findViewById(R.id.btnRegisterLogin);
        backButton=findViewById(R.id.btnRegisterBack);
        errorUsername=findViewById(R.id.tvErrorUsername);
    }
    public void userInput(){ // -- method for user inputs
        backButton(); // -- calling the method for clicking the back button
        loginButton(); // -- calling the method for clicking the login button

        proceed.setOnClickListener(new View.OnClickListener() { //-- for the proceed button
            @Override
            public void onClick(View view) {
                //--- to get the inputted data from EditTexts and convert it into String values
                String username=un.getText().toString();
                String userEmail=email.getText().toString();
                // -- for validation (to check user inputs)
                if(username.isEmpty()){
                    errorUsername.setText("Please fill the required field!");
                }else if(username.length()<3){
                    errorUsername.setText("Please input a username with at least 3 characters!");
                }
                else{
                    // Sets the username and email to the Setter method in the Users Class through the use of the Object
                    checkUsernameAvailability(username);
                    userObj.setEmail(userEmail);
                }
            }
        });
    }
    public void backButton(){ //-- to go back to the previous activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginAct=new Intent(getBaseContext(),LoginActivity.class);
                startActivity(loginAct);
            }
        });
    }
    public void loginButton(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginAct=new Intent(getBaseContext(),LoginActivity.class);
                startActivity(loginAct);
            }
        });
    }
public void checkUsernameAvailability(String username){  // -- method to check whether the inputted username is registered or not
    userAccountReference.orderByChild("Username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               boolean usernameExists=false;
                for(DataSnapshot userSnapshot: snapshot.getChildren()){ // -- this will iterate don sa database
                    String registeredUsername=userSnapshot.child("Username").getValue(String.class);
                    if (userSnapshot.exists() && registeredUsername.equalsIgnoreCase(username)){ // -- will check if may nagawa ng database or child node tas ccheck den if naregister na yung un
                    usernameExists=true; // -- tas kapag naman nakitang same yung inputted un sa stored un sa db magsset as true itong boolean variable na "usernameexists"
                    break;
                    }
                }
                  if(usernameExists) { //-- will check if whether totoo ngang nag-eexist na or registered na yung username
                      errorUsername.setText("Oops! Looks like someone has already picked that username");
                  }
                  else{
                    userObj.setUsername(username); // -- dito naman magpproceed if hindi pa registered yung un
                    Toast.makeText(getApplicationContext(), userObj.getUsername() + "\n" + userObj.getEmail(), Toast.LENGTH_SHORT).show(); //--for debugging
                    Intent nextAct=new Intent(getBaseContext(), GradeLevelActivity.class);
                    startActivity(nextAct);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
}
}
