package com.example.finalprojectvocabventure;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfileActivity extends AppCompatActivity {
    Users userObj = Users.getInstance();
    Button logout;
    AlertDialog.Builder logoutbtn;
    BottomNavigationView navbar;
    DatabaseReference userDb= FirebaseDatabase.getInstance().getReference("User Accounts");
    TextView home, performance, profile, updateProfile, username,viewProfileUN, gradelvl,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);
        init();
        username.setText(userObj.getLoggedUsername());
        navBarChoice();
        viewLoggedInUserInfo();
    }

    public void init() { //-- to initialize the variables
        home=findViewById(R.id.tvHomeViewProfile);
        performance=findViewById(R.id.PerformanceViewProfile);
        profile=findViewById(R.id.tvProfileViewProfile);
        username=findViewById(R.id.tvUsernameViewProfile);
        viewProfileUN=findViewById(R.id.tvVPUsername);
        email=findViewById(R.id.tvVPEmail);
        gradelvl=findViewById(R.id.tvVPGradeLvl);
        performance.setVisibility(View.GONE);
        home.setVisibility(View.GONE);
        navbar = findViewById(R.id.btmNavBarViewProfile);
        navbar.setSelectedItemId(R.id.profile_menu);
    }
    public void navBarChoice() { //-- method for getting the chosen menu of the user
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
                } else{
                    return false;
                }
                return true;
            }
        });

        logout = findViewById(R.id.btnLogOut);
        logoutbtn = new AlertDialog.Builder(this);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutbtn.setTitle("Confirm Logout");
                logoutbtn.setMessage("Are you sure you want to log out?");
                logoutbtn.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    // -- to logout user
                    public void onClick(DialogInterface dialog, int i) {

                        logoutUser();
                    }
                });
                logoutbtn.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = logoutbtn.create();
                dialog.show();
            }
        });

        updateProfile = findViewById(R.id.tvUpdateProfile);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(ViewProfileActivity.this, UpdateProfileActivity.class);
                startActivity(intent);
                Toast.makeText(ViewProfileActivity.this, "Update Profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // -- to display the details/info of the user
    public void viewLoggedInUserInfo(){
userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        for(DataSnapshot userSnapshot: snapshot.getChildren()){
            Integer userGradeLevel=userSnapshot.child("Grade Level").getValue(Integer.class);
            String userEmail=userSnapshot.child("Email").getValue(String.class);
            viewProfileUN.setText(userObj.getLoggedUsername());
            email.setText(userEmail);
            gradelvl.setText(String.valueOf(userGradeLevel));
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
    }
    public void logoutUser(){ // - method for logging out the user in the firebase db
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot:snapshot.getChildren()){
                    userDb.child(userSnapshot.getKey()).child("LoggedIn").setValue(0);

                    //-- redirecting to login activity
                    Intent loginAct=new Intent(getBaseContext(),LoginActivity.class);
                    startActivity(loginAct);
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}