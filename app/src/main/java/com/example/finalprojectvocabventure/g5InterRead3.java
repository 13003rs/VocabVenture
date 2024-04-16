package com.example.finalprojectvocabventure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class g5InterRead3 extends AppCompatActivity {
    ImageView inter1playg5, inter2playg5;
    ImageButton back, next;
    boolean isPlayClicked = false; // Track if the Play button is clicked

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_inter_read3);

        init();
        buttons();

        inter1playg5.setColorFilter(getResources().getColor(R.color.lockedColor));
    }

    public void init(){
        inter1playg5 = findViewById(R.id.play1_btn);
        inter2playg5 = findViewById(R.id.play2_btn);
        back = findViewById(R.id.btnNext);
        next = findViewById(R.id.btnBack);
    }

    public void buttons(){
        inter1playg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "It is locked, you already take this.", Toast.LENGTH_SHORT).show();
            }
        });

        inter2playg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(), g5InterRead3Next.class);
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
                Intent intentNext = new Intent(getBaseContext(), g5InterRead3Next.class);
                startActivity(intentNext);
            }
        });
    }


}
