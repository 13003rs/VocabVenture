package com.example.finalprojectvocabventure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class g5InterSpelling3 extends AppCompatActivity {

    ImageButton nextBtn, backBtn,homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5_inter_spelling3);
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
                Intent nextAct=new Intent(g5InterSpelling3.this,g5InterSpelling4.class);
                startActivity(nextAct);
                finish();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {  //-- for the back button
            @Override
            public void onClick(View v) {
                Intent backAct=new Intent(g5InterSpelling3.this,g5InterSpelling2.class);
                startActivity(backAct);
                finish();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() { //--for the home button
            @Override
            public void onClick(View view) {
                Intent homeAct=new Intent(g5InterSpelling3.this,HomeActivity.class);
                startActivity(homeAct);
                finish();
            }
        });
    }
}