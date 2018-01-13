package com.example.asmita.absent_form;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
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

public class ChangeProfilePhoto extends AppCompatActivity {

    Button filechoose,Fileupload;
    int pic_image_request=1;
    Bitmap bitmap;
    ImageView imageView;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_photo);
        filechoose=(Button)findViewById(R.id.buttonChoose);
        Fileupload=(Button)findViewById(R.id.buttonUpload);
        imageView=(ImageView)findViewById(R.id.imageView);
        Intent intent=getIntent();
        email=intent.getExtras().getString("EMAIL");
    }

    public void onBackPressed() {
        try {
            startActivity(new Intent(ChangeProfilePhoto.this, AbsentNAvigation.class));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void choose(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), pic_image_request);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_image_request && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    public void upload(View view)
    {
         final ProgressDialog progressDialog=new ProgressDialog(ChangeProfilePhoto.this);
  progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please wait while Updating your profile...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.updatePhoto_url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
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
              //  params.put(Config.Name,uname);
                params.put(Config.Email,email);
                //params.put(Config.Password,pass);
                //params.put(Config.Mobno,mob);
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