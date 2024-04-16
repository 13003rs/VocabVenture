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

public class PerformanceBasicActivity extends AppCompatActivity {
    Users userObj=Users.getInstance(); //-- calling out this constructor to access its data
    ImageView exit;
    BottomNavigationView navbar;
    TextView home, performance, profile, overallBar, usname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performance_basic);
        init();
        navBarChoice();
        usname.setText(userObj.getLoggedUsername());
    }
    public void init() { //-- to initialize the variables
        home=findViewById(R.id.tvHomeBasicPerformance);
        performance=findViewById(R.id.PerformanceBasicPerformance);
        profile=findViewById(R.id.tvProfileBasicPerformance);
        home.setVisibility(View.GONE);
        profile.setVisibility(View.GONE);
        navbar = findViewById(R.id.btmNavBarPerformanceBasic);
        navbar.setSelectedItemId(R.id.performance_menu);
        usname = findViewById(R.id.tvUsernamePerformanceBasic);

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

        exit = findViewById(R.id.ivExitBasicPerformance);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View exits) {
                Intent intent = new Intent(PerformanceBasicActivity.this, PerformanceActivity.class);
                startActivity(intent);
            }
        });

        overallBar = findViewById(R.id.tvOverallBasic);
        overallBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(PerformanceBasicActivity.this, OverallScoreActivity.class);
                startActivity(intent);
                Toast.makeText(PerformanceBasicActivity.this, "Overall Score", Toast.LENGTH_SHORT).show();
            }
        });
    }
}