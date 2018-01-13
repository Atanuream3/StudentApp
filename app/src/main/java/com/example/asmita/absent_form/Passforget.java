package com.example.asmita.absent_form;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class Passforget extends AppCompatActivity {

    EditText email;
    Button submit;
   // MainActivity main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passforget);
        email = (EditText) findViewById(R.id.forget);
        submit = (Button) findViewById(R.id.fpass);
     // main =new MainActivity();
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

    @Override
    public void onBackPressed()
    {
        finish();

    }


    public void viewpassword(View view)
    {
        final String uemail=email.getText().toString();

        if(uemail.isEmpty())
        {
            Toast.makeText(this,"Email id required",Toast.LENGTH_LONG).show();
        }
        else
        {
            final ProgressDialog progressDialog=new ProgressDialog(Passforget.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.forget_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //response.toString();
                            //If we are getting success from server
                            progressDialog.dismiss();
                            Toast.makeText(Passforget.this,response,Toast.LENGTH_SHORT).show();
                            // User.setText(password);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                            //You can handle error here if you want
                            //Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(Config.Email,uemail);


                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }


}
