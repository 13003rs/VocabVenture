package com.example.finalprojectvocabventure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class g5BasicSpelling9 extends AppCompatActivity {

    ImageButton nextBtn, backBtn,homeBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_basic_spelling9);

        init();
        buttons();
    }
    public void init(){
        nextBtn=findViewById(R.id.btnNext);
        backBtn=findViewById(R.id.btnBack);
        homeBtn=findViewById(R.id.btnHome);
    }
    public void buttons(){ //-- method for the function of the buttons
        nextBtn.setOnClickListener(new View.OnClickListener() { //-- for the next button
            @Override
            public void onClick(View view) {
                Intent nextAct=new Intent(g5BasicSpelling9.this,g5BasicSpelling10.class);
                startActivity(nextAct);
                finish();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() { //-- for back button
            @Override
            public void onClick(View view) {
                Intent backAct=new Intent(g5BasicSpelling9.this,g5BasicSpelling8.class);
                startActivity(backAct);
                finish();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() { //--for the home button
            @Override
            public void onClick(View view) {
                Intent homeAct=new Intent(g5BasicSpelling9.this,HomeActivity.class);
                startActivity(homeAct);
                finish();
            }
        });
    }
}