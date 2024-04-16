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

public class g5AdvRead1 extends AppCompatActivity {
    ImageView play1, play2;
    ImageButton back, next;
    boolean isPlayClicked = false; // Track if the Play button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_adv_read1);

        init();
        // Initially, disable play2
        play2.setEnabled(false);
        play2.setColorFilter(getResources().getColor(R.color.lockedColor)); // Set locked color
        buttons();
    }

    public void init(){
        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        play1 = findViewById(R.id.play1Btn);
        play2 = findViewById(R.id.play2Btn);
    }

    public void buttons() {

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(), g5AdvRead2Next.class);
                startActivity(intent1);

                // Set isPlay1Clicked to true
                isPlayClicked = true;

                // Enable play2
                play2.setEnabled(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        play2.setColorFilter(null); // Remove the color filter
                    }
                }, 600);
            }
        });

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play2.isEnabled()) {
                    if (isPlayClicked) {
                        Intent intentread1 = new Intent(getBaseContext(), g5AdvRead3Next.class);
                        startActivity(intentread1);
                        play2.setColorFilter(null); // Remove the color filter
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
                Intent intentNext = new Intent(getBaseContext(), g5AdvRead3Next.class);
                startActivity(intentNext);
            }
        });
    }

}
