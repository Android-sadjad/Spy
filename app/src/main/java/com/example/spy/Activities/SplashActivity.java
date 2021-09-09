package com.example.spy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.spy.Classes.MyConstant;
import com.example.spy.R;

public class SplashActivity extends AppCompatActivity {
    final int DURATION=1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        goToMainActivity();


    }

    private void goToMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, MyConstant.SPLASH_SCREEN_DURATION);


    }
}