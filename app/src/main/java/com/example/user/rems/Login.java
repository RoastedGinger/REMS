package com.example.user.rems;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button login, signup;
    View view;
    EditText phnum, password;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.log);
        signup = (Button) findViewById(R.id.sign);
        phnum = (EditText) findViewById(R.id.ph);
        password = (EditText) findViewById(R.id.pss);
      /*  ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(Login.this, "approved", Toast.LENGTH_SHORT).show();
        }
        else
       {
           Toast.makeText(Login.this, "No network connection try again later", Toast.LENGTH_SHORT).show();
        }
        */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Home.class);
                startActivity(a);

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), signUp.class);
                startActivity(a);

            }
        });

    }
}

