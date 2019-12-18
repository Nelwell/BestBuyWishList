package com.example.bestbuywishlist.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bestbuywishlist.R;
/*
Launcher Activity
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Taken from StackOverflow @ https://stackoverflow.com/questions/3072173/how-to-call-a-method-after-a-delay-in-android
        // Delays the call to mainActivity by the specified amount of time in milliseconds
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainActivity(); // Method to call after delay
            }
        }, 3000); // 3 seconds
    }

    // Launches Main Activity following expiration of splash screen
    protected void mainActivity() {
        Intent launchApp = new Intent(this, MainActivity.class);
        startActivity(launchApp);
    }
}
