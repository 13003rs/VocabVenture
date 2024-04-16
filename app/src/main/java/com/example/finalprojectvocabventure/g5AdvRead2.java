package com.example.finalprojectvocabventure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class g5AdvRead2 extends AppCompatActivity {
    ImageView play1, play2;
    ImageButton back, next;
    boolean isPlayClicked = false; // Track if the Play button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_adv_read2);

        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        play1 = findViewById(R.id.play1Btn);
        play2 = findViewById(R.id.play2Btn);

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
                Intent intent = new Intent(g5AdvRead2.this, g5AdvRead3Next.class);
                startActivity(intent);
            }
        });

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action when the Play button is clicked
                isPlayClicked = true; // Update the flag to indicate the Play button is clicked
                // Enable the Next button
                next.setEnabled(true);
                next.setAlpha(1f); // Optionally, set full opacity for the enabled button
                Intent intent = new Intent(g5AdvRead2.this, g5AdvRead2Next.class);
                startActivity(intent);
            }
        });


    }
}
