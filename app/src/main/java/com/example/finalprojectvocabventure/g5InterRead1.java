package com.example.finalprojectvocabventure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class g5InterRead1 extends AppCompatActivity {
    ImageView inter1playg5, inter2playg5;
    ImageButton back, next, home;
    boolean isPlayClicked = false; // Track if the Play button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_inter_read1);

        init();
        // Initially, disable play2
        inter2playg5.setEnabled(false);
        inter2playg5.setColorFilter(getResources().getColor(R.color.lockedColor)); // Set locked color
        buttons();
    }



    public void init(){
        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        home = findViewById(R.id.btnHome);
        inter1playg5 = findViewById(R.id.Btn1_play);
        inter2playg5 = findViewById(R.id.Btn2_play);
    }

    public void buttons() {

        inter1playg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(), g5InterRead2Next.class);
                startActivity(intent1);

                // Set isPlay1Clicked to true
                isPlayClicked = true;

                // Enable play2
                inter2playg5.setEnabled(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        inter2playg5.setColorFilter(null); // Remove the color filter
                    }
                }, 600);
            }
        });

        inter2playg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inter2playg5.isEnabled()) {
                    if (isPlayClicked) {
                        Intent intentread1 = new Intent(getBaseContext(), g5InterRead3Next.class);
                        startActivity(intentread1);
                        inter2playg5.setColorFilter(null); // Remove the color filter
                    } else {
                        // Handle the case where play1 is not clicked
                        Toast.makeText(getApplicationContext(), "Play1 is not clicked yet.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle the case where play2 is not enabled
                    Toast.makeText(getApplicationContext(), "Play2 is locked. Please click Play1 first.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intentBack);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(getBaseContext(), g5InterRead2Next.class);
                startActivity(intentNext);
            }
        });
    }
}
