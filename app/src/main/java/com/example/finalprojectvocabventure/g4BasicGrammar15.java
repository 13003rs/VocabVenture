package com.example.finalprojectvocabventure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class g4BasicGrammar15 extends AppCompatActivity {

    ImageButton nextBtn, backBtn,homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g4_basic_grammar15);
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
                showDialog();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backAct=new Intent(g4BasicGrammar15.this,g4BasicGrammar14.class);
                startActivity(backAct);
                finish();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() { //--for the home button
            @Override
            public void onClick(View view) {
                Intent homeAct=new Intent(g4BasicGrammar15.this,HomeActivity.class);
                startActivity(homeAct);
                finish();
            }
        });
    }
    private void showDialog() {
        dialog_quiz dialog = new dialog_quiz(QuizG4BasicGrammarQ1.class);
        dialog.show(getSupportFragmentManager(), "dialog_quiz");
    }
}