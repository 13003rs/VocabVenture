package com.example.finalprojectvocabventure;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class TermsAndPolicyActivity extends AppCompatActivity {

    Button acceptbtn, declinebtn;
    ImageView closebtn;
    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_and_policy);

        acceptbtn = findViewById(R.id.acceptButton);
        declinebtn = findViewById(R.id.declineButton);
        closebtn = findViewById(R.id.ivClose);
        scroll = findViewById(R.id.svScroll);

        setButtonColorsAndClickability(false);

        // scroll listener (vertically scrollable views) to check the scroll changes - like if ginalaw or not
        scroll.getViewTreeObserver().addOnScrollChangedListener(() -> {
            // check if the scroll is at the bottom
            boolean isScrollAtBottom = isScrollAtBottom(scroll);
            // change the color of the buttons once nasa bottom na yung scroll
            // e2 yung pag dinelete ko HAHAHA nakikita yung toast whether I clicked the button but hindi nga lang nag cchange color once it reached the bottom HHAHA bwiset
            setButtonColorsAndClickability(isScrollAtBottom);
        });

        // acceppt button function once clicked
        acceptbtn.setOnClickListener(v -> {
            // if the scroll is not at the bottom it toast a please scroll ; else - accepted and proceed to next window
            if (!isScrollAtBottom(scroll)) {
                // once lang e2 nalabas hehe - before mo I-scroll tas di na lalabas ulit after mo i-click ulit
                Toast.makeText(TermsAndPolicyActivity.this, "Please scroll until the bottom to accept.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(TermsAndPolicyActivity.this, "Accepted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TermsAndPolicyActivity.this, RegisterActivity.class)); // palagay na lang here yung next window (yung homesceen na ata)
                finish();
            }
        });

        // decline button once it is clicked
        declinebtn.setOnClickListener(v -> {
            // if the scroll is not at the bottom it displays a toast 'please scroll' ; else - decline and proceed to login window with a toast
            if (!isScrollAtBottom(scroll)) {
                // once lang e2 nalabas hehe - before mo I-scroll tas di na lalabas ulit after mo i-click ulit
                Toast.makeText(TermsAndPolicyActivity.this, "Please scroll until the bottom to decline.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(TermsAndPolicyActivity.this, "To register, please accept the Terms & Conditions and Privacy Policy.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TermsAndPolicyActivity.this, LoginActivity.class)); // login class ni-call d2 hehe
                finish();
            }
        });

        // uses lambda here para once line langs hehe
        // for close botton; once clicked it will go back to login window
        closebtn.setOnClickListener(v -> startActivity(new Intent(TermsAndPolicyActivity.this, LoginActivity.class))); // login class here hehe
    }


    // if scrollview (scroll) is at the bottom
    private boolean isScrollAtBottom(ScrollView scroll) {
        // get the vertical scroll position
        int scrollY = scroll.getScrollY();
        // get height of the scroll
        int height = scroll.getHeight();
        // get the total scroll height
        int scrollViewHeight = scroll.getChildAt(0).getHeight();

        // check if the vertical and height position is >= 2 da scrollViewHeight ; if yes - it means it is at the bottom
        return (scrollY + height) >= scrollViewHeight;
    }

    //functions for buttons color and its clickability
    private void setButtonColorsAndClickability(boolean isScrollAtBottom) {

        //set buttons colors: if the scroll is at the bottom, the accept/decline button change to its designated color ; else - it displays a dark gray color
        // xml yung accept/decline_button_background hehe
        int acceptcolor = isScrollAtBottom ? R.color.accept_button_background : android.R.color.darker_gray;
        int declinecolor = isScrollAtBottom ? R.color.decline_button_background : android.R.color.darker_gray;

        // apply the set button colors
        acceptbtn.setBackgroundTintList(ContextCompat.getColorStateList(this, acceptcolor));
        declinebtn.setBackgroundTintList(ContextCompat.getColorStateList(this, declinecolor));

        // set the clickability of the buttons based on the scroll position - if it is at the bottom
        acceptbtn.setClickable(isScrollAtBottom);
        declinebtn.setClickable(isScrollAtBottom);
    }
}