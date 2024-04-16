package com.example.finalprojectvocabventure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class g5AdvRead3 extends AppCompatActivity {
    ImageView pla1y, pla2y;
    ImageButton back, next;
    boolean isPlayClicked = false; // Track if the Play button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_adv_read3);

        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        pla1y = findViewById(R.id.btnp1ay);
        pla2y = findViewById(R.id.btnp2ay);

        init();
        buttons();

        pla1y.setColorFilter(getResources().getColor(R.color.lockedColor));
    }

    public void init(){
        back = findViewById(R.id.btnBack);
        next = findViewById(R.id.btnNext);
        pla1y = findViewById(R.id.btnplayAdv1);
        pla2y = findViewById(R.id.btnplayAdv2);
    }

    public void buttons(){
        pla1y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "It is locked, you already take this.", Toast.LENGTH_SHORT).show();
            }
        });

        pla2y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(), g5AdvRead3Next.class);
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
                Intent intentNext = new Intent(getBaseContext(), g5AdvRead3Next.class);
                startActivity(intentNext);
            }
        });
    }

    }

