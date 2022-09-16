package com.exam.foodit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.exam.foodit.Activity.LoginPage;
import com.exam.foodit.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    public void init(){
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    Intent intent= new Intent(getApplicationContext(), LoginPage.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        };

        background.start();

    }
}