package com.example.finalprojectvocabventure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class g4BasicRead1 extends AppCompatActivity {
    private ImageButton nextBtn,homeBtn,backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g4_basic_read1);

        nextBtn = findViewById(R.id.btnNext);
        homeBtn = findViewById(R.id.btnHome);
        backBtn = findViewById(R.id.btnBack);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent basicread2 = new Intent(g4BasicRead1.this, g4BasicRead2.class);
                startActivity(basicread2);
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent basicread = new Intent(g4BasicRead1.this, HomeActivity.class);
                startActivity(basicread);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent basicread = new Intent(g4BasicRead1.this, BasicFragment.class);
                startActivity(basicread);
            }
        });
    }
}
