package com.example.finalprojectvocabventure;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerformanceActivity extends AppCompatActivity {
    Users userObj=Users.getInstance(); //-- calling out this constructor to access its data
    DatabaseReference userDb= FirebaseDatabase.getInstance().getReference("User Accounts");
    CardView basic, intermediate, advance;
    BottomNavigationView navbar;
    TextView home, performance, profile, overallBar,username,readingperformance, spellingperformance, grammarperformance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.performance);
        init();
        username.setText(userObj.getLoggedUsername());
        navBarChoice();
        viewLoggedInUserInfo();
    }

    public void init() { //-- to initialize the variables
        home=findViewById(R.id.tvHomePerformance);
        performance=findViewById(R.id.PerformancePerformance);
        profile=findViewById(R.id.tvProfilePerformance);
        username=findViewById(R.id.tvUsernamePerformance);
        home.setVisibility(View.GONE);
        profile.setVisibility(View.GONE);
        navbar = findViewById(R.id.btmNavBarPerformance);
        navbar.setSelectedItemId(R.id.performance_menu);
        readingperformance=findViewById(R.id.tvReadingPerformance);
        spellingperformance=findViewById(R.id.tvSpellingPerformance);
        grammarperformance=findViewById(R.id.tvGrammarPerformance);

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

        overallBar = findViewById(R.id.tvOverall);
        overallBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View advances) {
                Intent intent = new Intent(PerformanceActivity.this, OverallScoreActivity.class);
                startActivity(intent);
                Toast.makeText(PerformanceActivity.this, "Overall Score", Toast.LENGTH_SHORT).show();
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
                    Integer userInterReadingScore=userSnapshot.child("InterReadingScore").getValue(Integer.class);
                    Integer userInterSpellingScore=userSnapshot.child("InterSpellingScore").getValue(Integer.class);
                    Integer userInterGrammarScore=userSnapshot.child("InterGrammarScore").getValue(Integer.class);
                    Integer userAdvReadingScore=userSnapshot.child("AdvReadingScore").getValue(Integer.class);
                    Integer userAdvSpellingScore=userSnapshot.child("AdvSpellingScore").getValue(Integer.class);
                    Integer userAdvGrammarScore=userSnapshot.child("AdvGrammarScore").getValue(Integer.class);

                    int basicread = userBasicReadingScore;
                    int interread =  userInterReadingScore;
                    int advread = userAdvReadingScore;

                    int basicspell = userBasicSpellingScore;
                    int interspell =  userInterSpellingScore;
                    int advspell = userAdvSpellingScore;

                    int basicgram = userBasicGrammarScore;
                    int intergram =  userInterGrammarScore;
                    int advgram = userAdvGrammarScore;

                    int readperf = ((basicread + interread + advread) * 100) / 30;
                    int spellperf = ((basicspell + interspell + advspell) * 100) / 30;
                    int gramperf = ((basicgram + intergram + advgram) * 100) / 30;
                    readingperformance.setText(String.valueOf(readperf));
                    spellingperformance.setText(String.valueOf(spellperf));
                    grammarperformance.setText(String.valueOf(gramperf));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}