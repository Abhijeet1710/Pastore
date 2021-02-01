package com.example.pastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.pastore.sp.Sp;

public class SplashActtivity extends AppCompatActivity {

    Sp s = new Sp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_PastoreNight);
            s.DAYMODE = false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acttivity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActtivity.this, MainActivity.class);
                startActivity(mainIntent);
                SplashActtivity.this.finish();
            }
        }, 1200);

    }
}