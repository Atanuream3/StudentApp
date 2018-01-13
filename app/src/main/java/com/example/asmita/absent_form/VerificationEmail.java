package com.example.asmita.absent_form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VerificationEmail extends AppCompatActivity {


    Button vCode;
    EditText vcode;
    String email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_email);

        vcode= (EditText) findViewById(R.id.forget);
        vCode= (Button) findViewById(R.id.submit);
        Intent intent=getIntent();
        email1=intent.getExtras().getString("email");
        Toast.makeText(this,email1,Toast.LENGTH_LONG).show();

    }


    public void viewcode(View view) {

        final String code = vcode.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.verify_url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //response.toString();
                        //If we are getting success from server
                        //
                        Toast.makeText(VerificationEmail.this,response,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(VerificationEmail.this,MainActivity.class));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        //You can handle error here if you want
                        //Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //Adding parameters to requestpa
                params.put(Config.Key, code);
                params.put(Config.Email, email1);


                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
