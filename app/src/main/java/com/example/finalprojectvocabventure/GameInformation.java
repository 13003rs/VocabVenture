package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class GameInformation extends AppCompatActivity {

    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_information);

        start = findViewById(R.id.ContinueButton);

        // Set up a click listener for the button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the HomeActivity when the button is clicked
                startActivity(new Intent(GameInformation.this, RegisterActivity.class));
            }
        });
    }
}