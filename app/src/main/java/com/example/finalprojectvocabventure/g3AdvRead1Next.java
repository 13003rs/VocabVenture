package com.example.finalprojectvocabventure;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class g3AdvRead1Next extends AppCompatActivity {
    VideoView p1;
    ImageView back, next;
    Button fullscreenButton;
    boolean isFullscreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3_adv_read1_next);

        p1 = findViewById(R.id.g3aread1);
        back = findViewById(R.id.backButton);
        fullscreenButton = findViewById(R.id.fullscreenButton);
        next = findViewById(R.id.nextButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFullscreen();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.g3advp1;
        p1.setVideoURI(Uri.parse(videoPath));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(p1);

        // Set media controller to the VideoView
        p1.setMediaController(mediaController);
        p1.start();
    }

    private void toggleFullscreen() {
        View decorView = getWindow().getDecorView();
        if (!isFullscreen) {
            // Hide system bars for fullscreen mode
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Set to landscape on enter

            // Change button text immediately to 'EXIT FULLSCREEN'
            fullscreenButton.setText("EXIT FULLSCREEN");
            back.setVisibility(View.GONE); // Hide back button
        } else {
            // Show system bars in fullscreen mode
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Set to portrait on exit
            fullscreenButton.setText("FULLSCREEN");
            back.setVisibility(View.VISIBLE);
        }
        isFullscreen = !isFullscreen;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

    private void showDialog() {
        dialog_quiz dialog = new dialog_quiz(QuizG3AdvReadQ6.class);
        dialog.show(getSupportFragmentManager(), "dialog_quiz");
    }
}
