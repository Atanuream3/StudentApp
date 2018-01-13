package com.example.asmita.absent_form;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity  implements View.OnClickListener{
    EditText name,email,password,mobno;
    Button submit ,filechoose;
    int pic_image_request;
    Bitmap bitmap;
    ImageView imageView;
    public String email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.mobileno);
        mobno=(EditText)findViewById(R.id.address);
        submit=(Button)findViewById(R.id.button2);
        filechoose=(Button)findViewById(R.id.upload);

        filechoose.setOnClickListener(this);


        imageView= (ImageView) findViewById(R.id.imageView);

    }



    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    public void onStart()
    {
        super.onStart();
    }



    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private  void Showfile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), pic_image_request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == pic_image_request && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Uri uri=data.getData();
            try
            {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

        }


    }

    public void submit(View view)
    {
        final String uname = name.getText().toString();
        email1 = email.getText().toString();
        final String pass = password.getText().toString();
        final String mob = mobno.getText().toString();
        if (uname.isEmpty() && email1.isEmpty() && pass.isEmpty() && mob.isEmpty()) {
            Toast.makeText(this, "ALL fields are Required", Toast.LENGTH_LONG).show();
        } else if (uname.isEmpty()) {
            Toast.makeText(this, "Name is Required", Toast.LENGTH_LONG).show();
        } else if (email1.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show();
        } else if (pass.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show();

        } else if (mob.isEmpty()) {
            Toast.makeText(this, "Mobile number is required", Toast.LENGTH_LONG).show();
        } else if (mob.length() > 10 || mob.length() < 10) {
            Toast.makeText(this, "Enter a valid Mobileno", Toast.LENGTH_LONG).show();
        }
        else
        {
            final ProgressDialog progressDialog=new ProgressDialog(Signup.this);
            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Please Wait..");
            progressDialog.setMessage("While Registering");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Register_URL,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                            if(response.equals("This email is already registered"))
                            {
                                progressDialog.dismiss();
                                Toast.makeText(Signup.this, response, Toast.LENGTH_LONG).show();

                            }
                            else {

                                progressDialog.dismiss();
                                Toast.makeText(Signup.this, response, Toast.LENGTH_LONG).show();
                                show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                            //You can handle error here if you want
                            //Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    String image=getStringImage(bitmap);
                    //Adding parameters to requestpa
                    params.put(Config.Name,uname);
                    params.put(Config.Email,email1);
                    params.put(Config.Password,pass);
                    params.put(Config.Mobno,mob);
                    params.put(Config.imagekey,image);


                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
           /* startActivity(new Intent(MainActivity.this,feedback.class));
            progressDialog=ProgressDialog.show(MainActivity.this,"","please Wait...",true);
            progressDialog.dismiss();*/


    }


    }


    public void show()
    {
          Intent intent=new Intent(this,VerificationEmail.class);
          intent.putExtra("email",email1);
          startActivity(intent);
    }



    @Override
    public void onClick(View v) {

        if(v == filechoose)
        {
            Showfile();
        }
    }




}




