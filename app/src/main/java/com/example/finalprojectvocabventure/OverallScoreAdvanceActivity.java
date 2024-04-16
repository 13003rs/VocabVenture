package com.example.finalprojectvocabventure;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

public class OverallScoreAdvanceActivity extends AppCompatActivity {

    CardView basic, intermediate, advance;
    Users userObj=Users.getInstance(); //-- calling out this constructor to access its data
    DatabaseReference userDb= FirebaseDatabase.getInstance().getReference("User Accounts");
    ImageView exit;
    BottomNavigationView navbar;
    TextView home, performance, profile, performanceBar, username, advreadingscore, advspellingscore, advgrammarscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_advance);
        init();
        username.setText(userObj.getLoggedUsername());
        navBarChoice();
        viewLoggedInUserInfo();
    }
    public void init() { //-- to initialize the variables
        home=findViewById(R.id.tvHomeAdvanceOverall);
        performance=findViewById(R.id.PerformanceAdvanceOverall);
        profile=findViewById(R.id.tvProfileAdvanceOverall);
        home.setVisibility(View.GONE);
        profile.setVisibility(View.GONE);
        username=findViewById(R.id.tvUsernameOverallAdvance);
        navbar = findViewById(R.id.btmNavBarOverallAdvance);
        navbar.setSelectedItemId(R.id.performance_menu);
        advreadingscore=findViewById(R.id.tvIndvScoreAdvancePageReading);
        advspellingscore=findViewById(R.id.tvIndvScoreAdvancePageSpelling);
        advgrammarscore=findViewById(R.id.tvIndvScoreAdvancePageGrammar);
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
        performanceBar = findViewById(R.id.tvPerformanceOverallAdvance);
        performanceBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(OverallScoreAdvanceActivity.this, PerformanceActivity.class);
                startActivity(intent);
                Toast.makeText(OverallScoreAdvanceActivity.this, "Performance", Toast.LENGTH_SHORT).show();
            }
        });

        exit = findViewById(R.id.ivExitAdvanceOverall);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View exits) {
                Intent intent = new Intent(OverallScoreAdvanceActivity.this, OverallScoreActivity.class);
                startActivity(intent);
            }
        });
    }
    public void viewLoggedInUserInfo(){
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot: snapshot.getChildren()){
                    Integer userAdvReadingScore=userSnapshot.child("AdvReadingScore").getValue(Integer.class);
                    Integer userAdvSpellingScore=userSnapshot.child("AdvSpellingScore").getValue(Integer.class);
                    Integer userAdvGrammarScore=userSnapshot.child("AdvGrammarScore").getValue(Integer.class);
                    advreadingscore.setText(String.valueOf(userAdvReadingScore));
                    advspellingscore.setText(String.valueOf(userAdvSpellingScore));
                    advgrammarscore.setText(String.valueOf(userAdvGrammarScore));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}