package com.example.finalprojectvocabventure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OverallScoreIntermediateActivity extends AppCompatActivity {
    Users userObj=Users.getInstance(); //-- calling out this constructor to access its data
    DatabaseReference userDb= FirebaseDatabase.getInstance().getReference("User Accounts");
    ImageView exit;
    BottomNavigationView navbar;
    TextView home, performance, profile, performanceBar, username, interreadingscore, interspellingscore, intergrammarscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_intermediate);
        init();
        username.setText(userObj.getLoggedUsername());
        navBarChoice();
        viewLoggedInUserInfo();
    }

    public void init() { //-- to initialize the variables
        home=findViewById(R.id.tvHomeIntermediateOverall);
        performance=findViewById(R.id.PerformanceIntermediateOverall);
        profile=findViewById(R.id.tvProfileIntermediateOverall);
        home.setVisibility(View.GONE);
        profile.setVisibility(View.GONE);
        username=findViewById(R.id.tvUsernameOverallAdvance);
        navbar = findViewById(R.id.btmNavBarOverallIntermediate);
        navbar.setSelectedItemId(R.id.performance_menu);
        interreadingscore=findViewById(R.id.tvIndvScoreInterPageReading);
        interspellingscore=findViewById(R.id.tvIndvScoreInterPageSpelling);
        intergrammarscore=findViewById(R.id.tvIndvScoreInterPageGrammar);
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
        performanceBar = findViewById(R.id.tvPerformanceOverallIntermediate);
        performanceBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(OverallScoreIntermediateActivity.this, PerformanceActivity.class);
                startActivity(intent);
                Toast.makeText(OverallScoreIntermediateActivity.this, "Performance", Toast.LENGTH_SHORT).show();
            }
        });

        exit = findViewById(R.id.ivExitIntermediateOverall);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View exits) {
                Intent intent = new Intent(OverallScoreIntermediateActivity.this, OverallScoreActivity.class);
                startActivity(intent);
            }
        });
    }
    public void viewLoggedInUserInfo(){
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot: snapshot.getChildren()){
                    Integer userInterReadingScore=userSnapshot.child("InterReadingScore").getValue(Integer.class);
                    Integer userInterSpellingScore=userSnapshot.child("InterSpellingScore").getValue(Integer.class);
                    Integer userInterGrammarScore=userSnapshot.child("InterGrammarScore").getValue(Integer.class);
                    interreadingscore.setText(String.valueOf(userInterReadingScore));
                    interspellingscore.setText(String.valueOf(userInterSpellingScore));
                    intergrammarscore.setText(String.valueOf(userInterGrammarScore));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}