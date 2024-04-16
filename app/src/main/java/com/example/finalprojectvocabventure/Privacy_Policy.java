package com.example.finalprojectvocabventure;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

public class Privacy_Policy extends AppCompatActivity {

    Button acceptbtnP, declinebtnP;
    ImageView closebtnP;
    NestedScrollView Nscroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        NscrollCall();
        accept();
        decline();
        close();
    }

    @SuppressLint("MissingInflatedId")
    public void init() {
        setContentView(R.layout.activity_privacy_policy);
        acceptbtnP = findViewById(R.id.acceptButtonPrivacy);
        declinebtnP = findViewById(R.id.declineButtonPrivacy);
        closebtnP = findViewById(R.id.ivClose);
        Nscroll = findViewById(R.id.nestedScrollView);
        setButtonColorsAndClickability(false);
    }

    public void NscrollCall() {
        // scroll listener (vertically scrollable views) to check the scroll changes - like if ginalaw or not
        Nscroll.getViewTreeObserver().addOnScrollChangedListener(() -> {
            // check if the scroll is at the bottom
            boolean isScrollAtBottom = isScrollAtBottom(Nscroll);
            // change the color of the buttons once nasa bottom na yung scroll
            // e2 yung pag dinelete ko HAHAHA nakikita yung toast whether I clicked the button but hindi nga lang nag cchange color once it reached the bottom HHAHA bwiset
            setButtonColorsAndClickability(isScrollAtBottom);
        });
    }


    public void accept() {
        // acceppt button function once clicked
        acceptbtnP.setOnClickListener(v -> {
            // if the scroll is not at the bottom it toast a please scroll ; else - accepted and proceed to next window
            if (!isScrollAtBottom(Nscroll)) {
                // once lang e2 nalabas hehe - before mo I-scroll tas di na lalabas ulit after mo i-click ulit
                Toast.makeText(Privacy_Policy.this, "Please scroll until the bottom to accept.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Privacy_Policy.this, "Accepted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Privacy_Policy.this, GameInformation.class)); // palagay na lang here yung next window (yung homesceen na ata)
                finish();
            }
        });
    }

    public void decline() {
        // decline button once it is clicked
        declinebtnP.setOnClickListener(v -> {
            // if the scroll is not at the bottom it displays a toast 'please scroll' ; else - decline and proceed to login window with a toast
            if (!isScrollAtBottom(Nscroll)) {
                // once lang e2 nalabas hehe - before mo I-scroll tas di na lalabas ulit after mo i-click ulit
                Toast.makeText(Privacy_Policy.this, "Please scroll until the bottom to decline.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Privacy_Policy.this, "To register, please accept the Terms & Conditions and Privacy Policy.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Privacy_Policy.this, LoginActivity.class)); // login class ni-call d2 hehe
                finish();
            }
        });
    }

    public void close() {
        // uses lambda here para one line langs hehe
        // for close botton; once clicked it will go back to login window
        closebtnP.setOnClickListener(v -> startActivity(new Intent(Privacy_Policy.this, LoginActivity.class))); // login class here hehe
    }


    // if NestedScrollView (scroll) is at the bottom
    private boolean isScrollAtBottom(NestedScrollView scroll) {
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
        acceptbtnP.setBackgroundTintList(ContextCompat.getColorStateList(this, acceptcolor));
        declinebtnP.setBackgroundTintList(ContextCompat.getColorStateList(this, declinecolor));

        // set the clickability of the buttons based on the scroll position - if it is at the bottom
        acceptbtnP.setClickable(isScrollAtBottom);
        declinebtnP.setClickable(isScrollAtBottom);
    }
}