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

public class g4InterRead1 extends AppCompatActivity {
    ImageView play1, play2;
    ImageButton back, next;
    boolean isPlay1Clicked = false; // Track if the Play1 button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g4_inter_read1);

        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        play1 = findViewById(R.id.playInter1);
        play2 = findViewById(R.id.playInter2);

        // Initially, disable play2
        play2.setEnabled(false);
        play2.setColorFilter(getResources().getColor(R.color.lockedColor)); // Set locked color


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intentBack);
            }
        });

        //quiz section for next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(g4InterRead1.this, g4InterRead1Next.class);
                startActivity(intentNext);
            }
        });

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(), g4InterRead1Next.class);
                startActivity(intent1);

                // Set isPlay1Clicked to true
                isPlay1Clicked = true;

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
                    if (isPlay1Clicked) {
                        Intent intentread1 = new Intent(getBaseContext(), g4InterRead2Next.class);
                        startActivity(intentread1);
                        play2.setColorFilter(null); // Remove the color filter
                    } else {
                        // Handle the case where play1 is not clicked
                        Toast.makeText(g4InterRead1.this, "Play1 is not clicked yet.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle the case where play2 is not enabled
                    Toast.makeText(g4InterRead1.this, "Play2 is locked. Please click Play1 first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}