package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class g3AdvRead2 extends AppCompatActivity {
    ImageView playbtn1, playbtn2;
    ImageButton back, next;
    boolean isPlayClicked = false; // Track if the Play button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_adv_read2);

        init();
        buttons();

        playbtn1.setColorFilter(getResources().getColor(R.color.lockedColor));
    }

    public void init(){
        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        playbtn1 = findViewById(R.id.btnplayAdv1);
        playbtn2 = findViewById(R.id.btnplayAdv2);
    }

    public void buttons(){
        playbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "It is locked, you already take this.", Toast.LENGTH_SHORT).show();
            }
        });

        playbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(), g3AdvRead1Next.class);
                startActivity(intent2);
            }
        });

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
                Intent intentNext = new Intent(getBaseContext(), g3AdvRead1Next.class);
                startActivity(intentNext);
            }
        });
    }

}