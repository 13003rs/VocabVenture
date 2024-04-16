package com.example.finalprojectvocabventure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class g3InterRead2 extends AppCompatActivity {
    ImageView p1ay1, pl2y;
    ImageButton nextBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_inter_read2);

        init();
        buttons();

        p1ay1.setColorFilter(getResources().getColor(R.color.lockedColor));
    }

    public void init(){
        p1ay1 = findViewById(R.id.btn_play1);
        pl2y = findViewById(R.id.btn_play2);
        nextBtn = findViewById(R.id.btnNext);
        backBtn = findViewById(R.id.btnBack);
    }

    public void buttons(){
        p1ay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "It is locked, you already take this.", Toast.LENGTH_SHORT).show();
            }
        });

        pl2y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(), g3InterRead1Next.class);
                startActivity(intent2);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intentBack);
            }
        });

        //quiz section for next button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNext = new Intent(getBaseContext(), g3InterRead1Next.class);
                startActivity(intentNext);
            }
        });
    }

}