package com.example.finalprojectvocabventure;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;

public class UpdateProfileActivity extends AppCompatActivity {
    AlertDialog.Builder deleteAcc, saveAcc, updateInfo, deleteInfo;
    Users userObj=Users.getInstance();
    DatabaseReference userDb = FirebaseDatabase.getInstance().getReference("User Accounts");
    Button delete, save;
    BottomNavigationView navbar;
    TextView home, performance, profile, viewProfile, username, errorPass, errorConfirmPass, errorUsername, errorGradeLevel;
    EditText editUsername, editPassword, confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        init();
        username.setText(userObj.getLoggedUsername());
        navBarChoice();
    }

    public void init() {
        home = findViewById(R.id.tvHomeUpdateProfile);
        performance = findViewById(R.id.PerformanceUpdateProfile);
        profile = findViewById(R.id.tvProfileUpdateProfile);
        username = findViewById(R.id.tvUsernameUpdateProfile);
        editUsername = findViewById(R.id.etEPUsername);
        editPassword = findViewById(R.id.etEPPass);
        confirmPass = findViewById(R.id.etEPConfirmPass);
        errorPass = findViewById(R.id.tvEPErrorPass);
        errorConfirmPass = findViewById(R.id.tvEPErrorConfirmPass);
        errorUsername = findViewById(R.id.tvEPErrorUsername);
        performance.setVisibility(View.GONE);
        home.setVisibility(View.GONE);
        navbar = findViewById(R.id.btmNavBarUpdateProfile);
        updateInfo = new AlertDialog.Builder(this);
        deleteInfo = new AlertDialog.Builder(this);
        navbar.setSelectedItemId(R.id.profile_menu);
    }

    public void navBarChoice() {
        navbar.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.performance_menu) {
                    Intent perfAct = new Intent(getBaseContext(), OverallScoreActivity.class);
                    home.setVisibility(View.GONE);
                    profile.setVisibility(View.GONE);
                    startActivity(perfAct);
                } else if (item.getItemId() == R.id.home_menu) {
                    performance.setVisibility(View.GONE);
                    profile.setVisibility(View.GONE);
                    Intent homeAct = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(homeAct);
                } else if (item.getItemId() == R.id.profile_menu) {
                    Intent profileAct = new Intent(getBaseContext(), ViewProfileActivity.class);
                    performance.setVisibility(View.GONE);
                    home.setVisibility(View.GONE);
                    profile.setVisibility(View.VISIBLE);
                    startActivity(profileAct);
                } else {
                    return false;
                }
                return true;
            }
        });

        viewProfile = findViewById(R.id.tvViewProfile);
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(UpdateProfileActivity.this, ViewProfileActivity.class);
                startActivity(intent);
                Toast.makeText(UpdateProfileActivity.this, "View Profile", Toast.LENGTH_SHORT).show();
            }
        });

        delete = findViewById(R.id.btnDeleteAccount);
        deleteAcc = new AlertDialog.Builder(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAcc.setTitle("Confirm Deletion of Account");
                deleteAcc.setMessage("Are you sure you want to delete your account?");
                deleteAcc.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        deleteAccount();
                    }
                });
                deleteAcc.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = deleteAcc.create();
                dialog.show();
            }
        });
            save = findViewById(R.id.btnSaveChanges);
            saveAcc = new AlertDialog.Builder(this);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (areAllFieldsEmpty()) {
                        Toast.makeText(UpdateProfileActivity.this, "Whoops, looks like there are no changes to be made", Toast.LENGTH_SHORT).show();
                    } else {
                        saveAcc.setTitle("Confirm Save Changes");
                        saveAcc.setMessage("Are you sure you want to save the changes you have made in your account?");
                        saveAcc.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if (checkInputError()) {
                                    errorPass.setText("");
                                    errorConfirmPass.setText("");
                                    errorUsername.setText("");
                                    showPasswordDialog();
                                }
                            }
                        });

                        saveAcc.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                editPassword.setText("");
                                confirmPass.setText("");
                                editUsername.setText("");
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = saveAcc.create();
                        dialog.show();
                    }
                }
            });
        }




    public void setUpdateInfo() {
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String editedUsername = editUsername.getText().toString().trim(); // Trim to remove leading/trailing whitespaces
                    String editedPassword = editPassword.getText().toString().trim();

                    // Using HashMap to update user data in the database
                    Map<String, Object> updates = new HashMap<>();
                    if (!editedUsername.isEmpty()) {
                        updates.put("Username", editedUsername);
                        userObj.setUsername(editedUsername); // Update the local instance
                    }
                    if (!editedPassword.isEmpty()) {
                        updates.put("Password", editedPassword);
                        userObj.setPassword(editedPassword); // Update the local instance
                    }

                    // Update Firebase database
                    userDb.child(userSnapshot.getKey()).updateChildren(updates);

                    // Show update confirmation dialog
                    updateInfo.setTitle("Information");
                    updateInfo.setMessage("Great job! Your profile has been updated successfully!");
                    updateInfo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editPassword.setText("");
                            confirmPass.setText("");
                        }
                    });
                    updateInfo.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });
    }

    public boolean doesUsernameAlreadyExist(String editedUsername) {
        boolean[] usernameExists = {false};
        Log.d("UpdateProfileActivity", "Checking if username exists: " + editedUsername);
        userDb.orderByChild("Username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    if (userSnapshot.exists() && editedUsername.equalsIgnoreCase(userSnapshot.child("Username").getValue(String.class))) {
                        usernameExists[0] = true;
                        break;
                    }
                }
                if (usernameExists[0]) {
                    errorUsername.setText("Oops! Looks like someone has already picked that username");
                }  else if (editedUsername.isEmpty()) {
                    errorUsername.setText("");
                }   else {
                    showPasswordDialog();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("UpdateProfileActivity", "Database error: " + error.getMessage());
            }
        });

        return !usernameExists[0];
    }
    public boolean checkPassword() { // -- method to validate or check the inputted password
        String password = editPassword.getText().toString();
        String confirmPassword = confirmPass.getText().toString();
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            errorPass.setText("Oops, please input this field!");
            errorConfirmPass.setText("Oops, please input this field!");
            return true; //--has error
        } else if (password.length() < 8) { //- to check the length of the character of the password (should be atleast 8 characters)
            errorPass.setText("Oops! Your password should be longer. Make it at least 8 characters.");
            errorConfirmPass.setText("");
            return true; //--has error
        } else if (!confirmPassword.equals(password)) { //-- to check whether the confirm pass matched or not
            errorConfirmPass.setText("Whoops! Passwords didn't match. Give it another try!");
            errorPass.setText("");
            return true; //--has error
        } else {
            userObj.setPassword(password);
            return false; //-- no error
        }
    }


    public void deleteAccount() { //-- method to delete the account and records of the user
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String userId = userSnapshot.getKey(); //-- to get the userID (unique key identifier nung user)
                    userDb.child(userId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() { // -- this will remove the record of the logged in user
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // An alert dialog will show if the deletion or the task is successful
                                deleteInfo.setTitle("Information");
                                deleteInfo.setMessage("Yay! Your account has been successfully deleted!");
                                deleteInfo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent loginAct = new Intent(getBaseContext(), LoginActivity.class);
                                        startActivity(loginAct);
                                        finish();
                                    }
                                });
                            } else {
                                deleteInfo.setTitle("Information");
                                deleteInfo.setMessage("Oops! Something went wrong while deleting your account. Please try again");
                                deleteInfo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                            }
                            deleteInfo.show();
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void showPasswordDialog(){ //-- to show the password dialog that would prompt the user to enter its current password first before being able to update its info sa db
        AlertDialog.Builder passwordDialog = new AlertDialog.Builder(UpdateProfileActivity.this);
        passwordDialog.setTitle("Verify Password");
        passwordDialog.setMessage("Hi there! 😊 To make sure it's you, can you enter your password?");
        final EditText inputPassword = new EditText(UpdateProfileActivity.this);//-- creating a textview that we will put sa dialog box so that they would just input their password to the dialogbox
        inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); //-- setting the input type to text and password
        passwordDialog.setView(inputPassword);
        passwordDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String currentPassword = inputPassword.getText().toString();
                verifyPassword(currentPassword); //-- will verify the inputted password

            }
        });
        passwordDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editPassword.setText("");
                confirmPass.setText("");
                editUsername.setText("");
                dialogInterface.cancel();
            }
        });
        passwordDialog.show();
    }
    private void verifyPassword(String currentPassword){ //-- to verify the inputted password in the dialog if it matches the user's current password
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String editedUsername=editUsername.getText().toString();
                    String storedPassword = userSnapshot.child("Password").getValue(String.class);
                    if (currentPassword.equals(storedPassword)) {
                        setUpdateInfo();
                        if (!editedUsername.isEmpty()) {
                            userObj.setUsername(editedUsername);
                            userObj.setLoggedUsername(editedUsername);
                            username.setText(userObj.getLoggedUsername());
                            editUsername.setText("");
                        }
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Oops, looks like you have entered a wrong password. Try again!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public boolean checkInputError() { //-- to check input errors first before calling the method "PassowrdDialog" to input its current pass and be able to update the info
        boolean hasError = false;
        if(!editPassword.getText().toString().isEmpty()) {
            if (checkPassword()) {
                hasError = true;
            }
        }
        if(!editUsername.getText().toString().isEmpty()) {
            if (doesUsernameAlreadyExist(editUsername.getText().toString())) {
                hasError = true;
            }
        }
        return !hasError;  // return true if there are no errors
    }
    public boolean areAllFieldsEmpty() {
        boolean isNotEmpty = true;
        String editedUsername = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String inputedConfirmPass = confirmPass.getText().toString();
        if (editedUsername.isEmpty() && password.isEmpty() && inputedConfirmPass.isEmpty()) {
            isNotEmpty =false;
        }
        return !isNotEmpty;
    }
}