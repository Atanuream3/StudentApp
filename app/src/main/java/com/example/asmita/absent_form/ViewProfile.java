package com.example.asmita.absent_form;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewProfile extends AppCompatActivity {

    ImageView imageView;
    TextView name,mob,mail;
     String email;
    CardView layout1;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        imageView = (ImageView) findViewById(R.id.img);
        name = (TextView) findViewById(R.id.name);
        mob = (TextView) findViewById(R.id.mobileno);
        mail=(TextView) findViewById(R.id.email);
        layout1=(CardView)findViewById(R.id.card);
        Intent intent=getIntent();
        email=intent.getExtras().getString("Email");

        showProfile();
    }

    public void showProfile()
    {
        final ProgressDialog progressDialog=new ProgressDialog(ViewProfile.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.setMessage("fetching profile..");
        progressDialog.setTitle("Please Wait");

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Account_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        showJson(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Check Your internet connection",Toast.LENGTH_LONG).show();
                        //You can handle error here if you want
                        //Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.Email,email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }






    public void showJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray Result = jsonObject.getJSONArray("Result");
            JSONObject collect = Result.getJSONObject(0);
            String url = collect.getString("Image");
            String name1=collect.getString("name");
            String mobno=collect.getString("mobno");
            String email=collect.getString("email");
            //name.setText(name1);
            name.setText("Name: "+name1);
            mob.setText("Mobile No: "+mobno);
            mail.setText("Email: "+email);
            Picasso.with(getApplicationContext()).load(url).into(imageView);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
