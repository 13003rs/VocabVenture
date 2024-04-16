package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class g4AdvRead1 extends AppCompatActivity {
    ImageView play;
    ImageButton back, next, home;
    boolean isPlayClicked = false; // Track if the Play button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g4_adv_read1);

        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        home = findViewById(R.id.btnHome);
        play = findViewById(R.id.playBtn);

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
                Intent intent = new Intent(g4AdvRead1.this, g4AdvRead2.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(g4AdvRead1.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action when the Play button is clicked
                isPlayClicked = true; // Update the flag to indicate the Play button is clicked
                // Enable the Next button
                next.setEnabled(true);
                next.setAlpha(1f); // Optionally, set full opacity for the enabled button
                Intent intent = new Intent(g4AdvRead1.this, g4AdvRead1Next.class);
                startActivity(intent);
            }
        });

        // Initially, disable the Next button
        next.setEnabled(false);
        next.setAlpha(0.5f); // Optionally, set reduced opacity for the disabled button
    }
}
