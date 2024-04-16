package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class g3AdvRead1 extends AppCompatActivity {
    ImageView btnplay1, btnplay2;
    ImageButton back, next;
    boolean isbtnplay1Clicked = false; // Track if the Play button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_adv_read1);

        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        btnplay1 = findViewById(R.id.playAdv1);
        btnplay2 = findViewById(R.id.playAdv2);

        // Initially, disable play2
        btnplay2.setEnabled(false);
        btnplay2.setColorFilter(getResources().getColor(R.color.lockedColor)); // Set locked color

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //quiz section for next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(g3AdvRead1.this, g3AdvRead2Next.class);
                startActivity(intent);
            }
        });

        btnplay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(g3AdvRead1.this, g3AdvRead2Next.class);
                startActivity(intent);

                // Set isPlay1Clicked to true
                isbtnplay1Clicked = true;

                // Enable play2
                btnplay2.setEnabled(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnplay2.setColorFilter(null); // Remove the color filter
                    }
                }, 600);
            }
        });

      btnplay2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(btnplay2.isEnabled()){
                  if (isbtnplay1Clicked){
                      new Handler().postDelayed(new Runnable() {
                          @Override
                          public void run() {
                              btnplay2.setColorFilter(null); // Remove the color filter
                          }
                      }, 600);
                      Intent intent2 = new Intent(getBaseContext(), g3AdvRead1Next.class);
                      startActivity(intent2);
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
    }
}


