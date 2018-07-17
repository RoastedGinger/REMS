package com.example.user.rems;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String answer;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //   final MediaPlayer mp=MediaPlayer.create(this,R.raw.lg)
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        } else {

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (null != activeNetwork) {
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String name = prefs.getString("UserID", "0");
                Toast.makeText(Splash.this, name, Toast.LENGTH_SHORT).show();
                if (!name.equals("0")) {
                    Intent intent = new Intent(Splash.this, Home.class);
                    startActivity(intent);
                    finish();//"No name defined" is the default value.
                } else {
                    Intent intent1 = new Intent(Splash.this, Login.class);
                    startActivity(intent1);
                }
            } else
                answer = "No internet Connectivity";
            Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_LONG).show();

        }
        }

}
