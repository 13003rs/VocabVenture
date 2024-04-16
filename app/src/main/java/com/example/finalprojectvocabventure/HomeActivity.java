package com.example.finalprojectvocabventure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    CardView basic, intermediate, advance;
    Users userObj = Users.getInstance(); //-- calling out this constructor to access its data
    BottomNavigationView navbar;
    Button info;
    TextView home, performance, profile, username;
    ImageView chooseDiffLvl, perf, prof;
    private TapTargetSequence sequence;
    private boolean isInfoClicked = false;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String INFO_SHOWN_KEY = "InfoShown";
    private boolean isSequenceRunning = false;
    DatabaseReference userDb = FirebaseDatabase.getInstance().getReference("User Accounts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        init();
        getLoggedInUser();
        navBarChoice();
        getLoggedInUserGradeAndChooseLevel();
        chosenDiffLevel();

        Users.getInstance().initialize(getApplicationContext());
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean infoShown = settings.getBoolean(INFO_SHOWN_KEY, false);

        if (!infoShown) {
            startTapTargetSequence();
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(INFO_SHOWN_KEY, true);
            editor.apply();
        }
    }
    //for tap target button
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && isInfoClicked) {
            startTapTargetSequence();
        }
    }

    public void init() { //-- to initialize the variables
        basic = findViewById(R.id.cvBasic);
        intermediate = findViewById(R.id.cvHomeIntermediate);
        advance = findViewById(R.id.cvHomeAdvance);
        //nav button
        info = findViewById(R.id.btnHomeNav);
        perf = findViewById(R.id.ivPerformanceIcon);
        home = findViewById(R.id.tvHome);
        prof = findViewById(R.id.ivProfileIcon);
        //
        performance = findViewById(R.id.tvPerformance);
        profile = findViewById(R.id.tvProfile);
        chooseDiffLvl = findViewById(R.id.ivChooseDiffLvl);
        username = findViewById(R.id.tvHomeUsername);
        performance.setVisibility(View.GONE);
        profile.setVisibility(View.GONE);
        navbar = findViewById(R.id.bnvNavBar);
        navbar.setSelectedItemId(R.id.home_menu);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInfoClicked = true;
                startTapTargetSequence();
            }
        });
    }

    //start ung sequence ng tap target
    private void startTapTargetSequence() {
        sequence = new TapTargetSequence(this);
        Typeface futuraFont = ResourcesCompat.getFont(this, R.font.futura);
        sequence.targets(
                TapTarget.forView(info, "Click this to navigate your way into this application.", "1 of 1")
                        .outerCircleColor(R.color.yellow)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.black)
                        .titleTypeface(futuraFont)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .descriptionTypeface(futuraFont)
                        .textColor(R.color.black)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(true)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(40),
                TapTarget.forView(perf, "This will take you to the Tracking Performance Screen, if you want to view and track your performance.", "1 of 3")
                        .outerCircleColor(R.color.yellow)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.black)
                        .titleTypeface(futuraFont)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .descriptionTypeface(futuraFont)
                        .textColor(R.color.black)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(true)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(40),
                TapTarget.forView(prof, "Tap here to go to your profile account where you can change your settings and update your info.", "2 of 3")
                        .outerCircleColor(R.color.yellow)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.black)
                        .titleTypeface(futuraFont)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .descriptionTypeface(futuraFont)
                        .textColor(R.color.black)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(true)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(40),
                TapTarget.forView(home, "This will take you to the homescreen (this screen), to access and choose the lesson and difficulty level that you want.", "3 of 3")
                        .outerCircleColor(R.color.yellow)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.black)
                        .titleTypeface(futuraFont)
                        .descriptionTextSize(10)
                        .descriptionTextColor(R.color.black)
                        .descriptionTypeface(futuraFont)
                        .textColor(R.color.black)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(true)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(65));

        sequence.listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {
                isSequenceRunning = false;
            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
            }

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {
                isSequenceRunning = false;
            }
        });

        sequence.start();
        isSequenceRunning = true;
    }


    public void navBarChoice() {
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.performance_menu) {
                    Intent perfAct = new Intent(getBaseContext(), OverallScoreActivity.class);
                    home.setVisibility(View.GONE);
                    profile.setVisibility(View.GONE);
                    startActivity(perfAct);
                    finish();
                    return true;
                } else if (itemId == R.id.home_menu) {
                    performance.setVisibility(View.GONE);
                    profile.setVisibility(View.GONE);
                    Intent homeAct = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(homeAct);
                    finish();
                    return true;
                } else if (itemId == R.id.profile_menu) {
                    Intent profileAct = new Intent(getBaseContext(), ViewProfileActivity.class);
                    performance.setVisibility(View.GONE);
                    home.setVisibility(View.GONE);
                    profile.setVisibility(View.VISIBLE);
                    startActivity(profileAct);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }


    public void getLoggedInUser() { //-- method for getting the Username of the current logged in user
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    userObj.setLoggedUsername(userSnapshot.child("Username").getValue(String.class));

                    //--to update the UN into the new logged in user
                    if (username != null) {
                        username.setText(userObj.getLoggedUsername());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void getLoggedInUserGradeAndChooseLevel() {
        userDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    DatabaseReference userGradeRef = userSnapshot.getRef().child("Grade Level");

                    userGradeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot gradeSnapshot) {
                            if (gradeSnapshot.exists()) {
                                Integer userGrade = gradeSnapshot.getValue(Integer.class);
                                //checking lang
                                Log.d("UserGradeLevel", "User grade retrieved: " + userGrade);

                                //Set the grade level para maretrieve and call fragments para mapassed
                                userObj.setGradeLevel(userGrade);
                                setUpFragmentClickListeners();
                            } else {
                                Log.d("UserGradeLevel", "User grade doesn't exist!");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("UserGradeLevel", "Error retrieving user grade: " + error.getMessage());

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("UserGradeLevel", "Error querying user data: " + error.getMessage());
            }
        });
    }


    private void setUpFragmentClickListeners() {
        chosenDiffLevel();
    }

    private void setUpFragmentClickListener(Fragment fragment) {
        Bundle args = new Bundle();
        args.putInt("UserGradeLevel", userObj.getGradeLevel());
        fragment.setArguments(args);

        basic.setVisibility(View.GONE);
        intermediate.setVisibility(View.GONE);
        advance.setVisibility(View.GONE);
        chooseDiffLvl.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragments, fragment)
                .addToBackStack(null)
                .commit();
    }


    public void chosenDiffLevel() {
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasicFragment basicFragment = new BasicFragment();
                setUpFragmentClickListener(basicFragment);
            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntermediateFragment intermediateFragment = new IntermediateFragment();
                setUpFragmentClickListener(intermediateFragment);
            }
        });

        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdvanceFragment advanceFragment = new AdvanceFragment();
                setUpFragmentClickListener(advanceFragment);
            }
        });
    }
}

