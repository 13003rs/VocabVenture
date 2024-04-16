package com.example.finalprojectvocabventure;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OverallScoreActivity extends AppCompatActivity {

    CardView basic, intermediate, advance;
    Users userObj=Users.getInstance(); //-- calling out this constructor to access its data

    BottomNavigationView navbar;
    TextView home, performance, profile, performanceBar,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_score);
        Log.d("Splash_Screen", "Checking for logged-in user: " + userObj.getLoggedUsername());
        init();
        username.setText(userObj.getLoggedUsername());
        navBarChoice();
    }

    public void init() { //-- to initialize the variables
        basic = findViewById(R.id.cvBasicOverall);
        intermediate = findViewById(R.id.cvIntermediate);
        advance = findViewById(R.id.cvAdvanceOverall);
        home=findViewById(R.id.tvHome);
        performance=findViewById(R.id.Performance);
        profile=findViewById(R.id.tvProfile);
        username=findViewById(R.id.tvUsernameOverallPage);
        profile.setVisibility(View.GONE);
        home.setVisibility(View.GONE);
        navbar = findViewById(R.id.bottomNavBarOverall);
        navbar.setSelectedItemId(R.id.performance_menu);

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
                    return true;
                } else if (item.getItemId() == R.id.home_menu) {
                    performance.setVisibility(View.GONE);
                    profile.setVisibility(View.GONE);
                        Intent homeAct = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(homeAct);
                        return true;
                    }
                else if (item.getItemId() == R.id.profile_menu) {
                    Intent profileAct = new Intent(getBaseContext(), ViewProfileActivity.class);
                    performance.setVisibility(View.GONE);
                    home.setVisibility(View.GONE);
                    startActivity(profileAct);
                    return true;
                }
                    return false;

            }
        });

        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View basics) {
                Intent intent = new Intent(OverallScoreActivity.this, OverallScoreBasicActivity.class);
                startActivity(intent);
            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View intermediates) {
                Intent intent = new Intent(OverallScoreActivity.this, OverallScoreIntermediateActivity.class);
                startActivity(intent);
            }
        });

        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(OverallScoreActivity.this, OverallScoreAdvanceActivity.class);
                startActivity(intent);
            }
        });

        performanceBar = findViewById(R.id.tvPerformance);
        performanceBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(OverallScoreActivity.this, PerformanceActivity.class);
                startActivity(intent);
                Toast.makeText(OverallScoreActivity.this, "Performance", Toast.LENGTH_SHORT).show();
            }
        });
    }
}