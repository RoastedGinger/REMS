package com.example.user.rems;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button login, signup;
    View view;
    EditText phnum, password;
    CheckBox checkBox;
    String pn,pass;
    String Server_url="https://rentalsolution.000webhostapp.com/REMS/validate.php";
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
                 pn = phnum.getText().toString();
                 pass = password.getText().toString();
                final String val="connected";
                StringRequest request = new StringRequest(Request.Method.POST, Server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                   String res = response.toString().trim();
                                if(res.equals(val))
                                {
                                    Toast.makeText(Login.this,"Welcome User"+res, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login.this,Home.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Login.this,response, Toast.LENGTH_LONG).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String error1 = error.toString();
                        Toast.makeText(Login.this,error1,Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        //  params.put("name",name.getText().toString().trim());
                        params.put("passwd",pass);
                        params.put("mobile",pn);

                        return params;
                    }
                };
                MySingleton.getInstance(Login.this).addToRequestQue(request);

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

