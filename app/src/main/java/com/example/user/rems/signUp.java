package com.example.user.rems;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class signUp extends AppCompatActivity {
    EditText name,phno,passwd,repass;
    Button button,signin;
    CheckBox checkBox;
    String uname,upass,uphno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=(EditText)findViewById(R.id.name);
        phno=(EditText)findViewById(R.id.phn);
        passwd=(EditText)findViewById(R.id.pss);
        repass=(EditText)findViewById(R.id.repss);
        button = (Button)findViewById(R.id.sign_up);
        checkBox = (CheckBox)findViewById(R.id.checkBox);



       //button.setEnabled(false);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked() || name!=null|| phno!=null || passwd!=null) {
                    button.setEnabled(true);
                }
            }
        });

        EditText yourEditText = (EditText) findViewById(R.id.pss);
        yourEditText.setFilters(new InputFilter[] {
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("[a-zA-Z ]+")){
                            return cs;
                        }
                        return "";
                    }
                }
        });


    }


    public void save_info(View view)
    {
        uname= name.getText().toString();
        upass= passwd.getText().toString();
        uphno = phno.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(uname,upass,uphno);
       // finish();
    }
    class BackgroundTask extends AsyncTask<String,Void,String>
    {
       String add_url;
        @Override
        protected  void onPostExecute(String result){
            Toast.makeText(signUp.this, result, Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String... args) {
            String name,phno,pass;
            name = args[0];
            pass = args[1];
            phno = args[2];
            try {
                URL url = new URL(add_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("passwd","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"+
                        URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(phno,"UTF-8");
                        bufferedWriter.write(data_string);
                        bufferedWriter.flush();
                         bufferedWriter.close();
                          outputStream.close();
                       InputStream inputStream = httpURLConnection.getInputStream();
                       inputStream.close();
                       httpURLConnection.disconnect();
                       return "one user inserted";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        protected void onPreExecute()
        {
            add_url="http://rentalsolution.000webhostapp.com/REMS/add_info.php";
            //super.onPreExecute();
        }
    }


}
