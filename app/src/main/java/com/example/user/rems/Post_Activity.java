package com.example.user.rems;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Post_Activity extends AppCompatActivity implements View.OnClickListener{

    Button upload,choose;
    EditText name,ocontact,rent,address;
    Spinner city1,flat;
    ArrayAdapter<CharSequence> adapter,adapter1;
    ImageView image;
    private final int img=1;
    Bitmap bitmap;
   // String Server_url = "https://rentalsolution.000webhostapp.com/image.php",city,property;

    String Server_url = "https://rentalsolution.000webhostapp.com/Rentalhouse/Login/Login_v2/uploadimage.php",city,property;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_);
        upload = (Button)findViewById(R.id.upload);
        choose = (Button)findViewById(R.id.choose);
        //name = (EditText) findViewById(R.id.name);
        image = (ImageView)findViewById(R.id.image);
        ocontact = (EditText)findViewById(R.id.contact);
        address = (EditText)findViewById(R.id.main_address);
        rent = (EditText) findViewById(R.id.amount);
        choose.setOnClickListener(this);
        upload.setOnClickListener(this);
         flat = (Spinner) findViewById(R.id.flat);
         city1 = (Spinner)findViewById(R.id.city);

        adapter = ArrayAdapter.createFromResource(this, R.array.FLAT, android.R.layout.simple_spinner_item);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.CITY, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flat.setAdapter(adapter);
         flat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "", Toast.LENGTH_LONG).show();
                property = flat.getSelectedItem().toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
         city1.setAdapter(adapter1);
          city1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "", Toast.LENGTH_LONG).show();
                 city = city1.getSelectedItem().toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.choose:
                   selectimage();
                  break;
            case R.id.upload:

                uploadImage();
                break;
        }
    }

    private void selectimage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,img);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         if(requestCode==img && resultCode==RESULT_OK && data!=null){
             Uri path = data.getData();
             try {
                 bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                 image.setImageBitmap(bitmap);
                 image.setVisibility(View.VISIBLE);
//                 name.setVisibility(View.VISIBLE);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
    }

    private void uploadImage()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                            String Response = response.toString();
                            Toast.makeText(Post_Activity.this,Response,Toast.LENGTH_LONG).show();
                            image.setImageResource(0);
                            image.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String error1 = error.toString();
                Toast.makeText(Post_Activity.this,error1,Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
              //  params.put("name",name.getText().toString().trim());
                params.put("city",city);
                params.put("property",property);
                params.put("onumber",ocontact.getText().toString().trim());
                params.put("rent",rent.getText().toString().trim());
                params.put("address",address.getText().toString().trim());
                params.put("image",imagetoString(bitmap));
                return params;
            }
        };
        MySingleton.getInstance(Post_Activity.this).addToRequestQue(request);
    }

    private String imagetoString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imbByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imbByte,Base64.DEFAULT);
    }
}
