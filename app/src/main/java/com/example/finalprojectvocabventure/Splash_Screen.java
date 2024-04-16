package com.example.finalprojectvocabventure;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.os.Handler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash_Screen extends AppCompatActivity {
    Users userObj = Users.getInstance();
    DatabaseReference logSessionDb = FirebaseDatabase.getInstance().getReference("User Accounts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isUserLoggedIn();
            }
        }, 2700);
    }

    private void isUserLoggedIn() {
        logSessionDb.orderByChild("LoggedIn").equalTo(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean loggedIn = false;
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    // Check if the user exists and has the correct logged-in state
                    Integer logInState = userSnapshot.child("LoggedIn").getValue(Integer.class);
                    if(userSnapshot.exists() && logInState==1){
                        loggedIn = true;
                        break;
                    }
                }

                if (loggedIn) {
                    // User is logged in, go to HomeActivity
                    Intent homeAct = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(homeAct);
                } else {
                    // No logged-in user, go to LoginActivity
                    Intent loginAct = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(loginAct);
                }

                finish(); // Finish the splash activity
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
