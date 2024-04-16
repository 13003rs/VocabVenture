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

public class OverallScoreBasicActivity extends AppCompatActivity {
    Users userObj=Users.getInstance(); //-- calling out this constructor to access its data
    DatabaseReference userDb= FirebaseDatabase.getInstance().getReference("User Accounts");
    ImageView exit;
    BottomNavigationView navbar;
    TextView home, performance, profile, performanceBar, username, basicreadingscore, basicspellingscore, basicgrammarscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_basic);
        init();
        username.setText(userObj.getLoggedUsername());
        navBarChoice();
        viewLoggedInUserInfo();
    }

    public void init() { //-- to initialize the variables
        home=findViewById(R.id.tvHomeBasicOverall);
        performance=findViewById(R.id.tvPerformanceOverallBasic);
        profile=findViewById(R.id.tvProfileBasicOverall);
        home.setVisibility(View.GONE);
        profile.setVisibility(View.GONE);
        username=findViewById(R.id.tvUsernameOverallAdvance);
        navbar = findViewById(R.id.btmNavBarOverallBasic);
        navbar.setSelectedItemId(R.id.performance_menu);
        basicreadingscore=findViewById(R.id.tvIndvScoreBasicPageReading);
        basicspellingscore=findViewById(R.id.tvIndvScoreBasicPageSpelling);
        basicgrammarscore=findViewById(R.id.tvIndvScoreBasicPageGrammar);
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
        performanceBar = findViewById(R.id.tvPerformanceOverallBasic);
        performanceBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(OverallScoreBasicActivity.this, PerformanceActivity.class);
                startActivity(intent);
                Toast.makeText(OverallScoreBasicActivity.this, "Performance", Toast.LENGTH_SHORT).show();
            }
        });

        exit = findViewById(R.id.ivExitBasicOverall);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View exits) {
                Intent intent = new Intent(OverallScoreBasicActivity.this, OverallScoreActivity.class);
                startActivity(intent);
            }
        });
    }
    public void viewLoggedInUserInfo(){
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot: snapshot.getChildren()){
                    Integer userBasicReadingScore=userSnapshot.child("BasicReadingScore").getValue(Integer.class);
                    Integer userBasicSpellingScore=userSnapshot.child("BasicSpellingScore").getValue(Integer.class);
                    Integer userBasicGrammarScore=userSnapshot.child("BasicGrammarScore").getValue(Integer.class);
                    basicreadingscore.setText(String.valueOf(userBasicReadingScore));
                    basicspellingscore.setText(String.valueOf(userBasicSpellingScore));
                    basicgrammarscore.setText(String.valueOf(userBasicGrammarScore));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}