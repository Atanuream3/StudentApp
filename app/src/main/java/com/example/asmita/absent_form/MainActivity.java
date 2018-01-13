package com.example.asmita.absent_form;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.HashMap;
import java.util.Map;
//rt static com.example.asmita.absent_form.R.layout.forgetpass;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView textView;
   //ditText email;
  //Button submit;
   SessionManager sessionManager;
    TextView forget;

    AdView adview;


    public String uname;
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_login);
        textView = (TextView) findViewById(R.id.link);

       //mail = (EditText) findViewById(R.id.forget);
      //submit = (Button) findViewById(R.id.fpass);
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-1156962038468117/4309402386");
        adview= (AdView) findViewById(R.id.adView);
        AdRequest request=new AdRequest.Builder().build();
        adview.loadAd(request);
        username = (EditText) findViewById(R.id.username);
        forget = (TextView) findViewById(R.id.forgetpass);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Signup.class));
            }
        });

        sessionManager=new SessionManager(getApplicationContext());
    //    Toast.makeText(getApplicationContext(),"status"+sessionManager.isUserLoggedIn(),Toast.LENGTH_LONG).show();

        if(sessionManager.isUserLoggedIn())
        {
            //startActivity(new Intent(getApplicationContext(),AbsentNAvigation.class));
            start(uname);
        }
    }


    @Override

    public void onStart()
    {
        super.onStart();
//        Toast.makeText(getApplicationContext(),sessionManager.getuser(),Toast.LENGTH_LONG).show();
        //uname = username.getText().toString();
  //      Toast.makeText(getApplicationContext(),"User="+sessionManager.getUserDetails(uname),Toast.LENGTH_LONG).show();
        if(sessionManager.isUserLoggedIn()) {
            //start(uname);

            //startActivity(new Intent(getApplicationContext(), AbsentNAvigation.class));
        }
        clear();
    }


    public void onBackPressed()
    {
        //super.onBackPressed();

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to exit");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //Yes Button



                MainActivity.this.finish();

                //bsentNAvigation.finish();
                //    finish();
                //    startActivity(new Intent(this,MainActivity.class));
            }

        });



        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //no button

                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();//create
        alert.show();

    }


    public  void clear()
    {
          username.setText("");
          password.setText("");
    }
    public void loginUser(View view) {

        final ProgressDialog progressDialog;

        uname = username.getText().toString();
        final String pass = password.getText().toString();

        if (uname.isEmpty() && pass.isEmpty()) {
            Toast.makeText(this, "Username and Password Required", Toast.LENGTH_LONG).show();
        } else if (uname.isEmpty()) {
            Toast.makeText(this, "Username can't be empty", Toast.LENGTH_LONG).show();
        } else if (pass.isEmpty()) {

            Toast.makeText(this, "Password can't be empty", Toast.LENGTH_LONG).show();

        } else {

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Log in...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Login_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //response.toString();
                            //If we are getting success from server
                            if (response.trim().equals(Config.login_success)) {
                                /*Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, AbsentNAvigation.class);
                                intent.putExtra("email", uname);
                                startActivity(intent);*/
                                start(uname);
                                progressDialog.dismiss();
                                sessionManager.createLoginSession(uname);
                                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                // User.setText(password);
                            } else {
                                //If the server response is not success
                                //Displaying an error message on toast
                                progressDialog.dismiss();
                                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                                Toast.makeText(MainActivity.this, "Please Check your username or password", Toast.LENGTH_LONG).show();
                                //User.setText(response);

                            }

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
                    //Adding parameters to request
                    params.put(Config.Email, uname);
                    params.put(Config.Password, pass);

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







        public void forgetPassword(View view)
    {
        /*AlertDialog.Builder b1=new AlertDialog.Builder(this);
        View mview=getLayoutInflater().inflate(R.layout.forgetpass,null);
        AlertDialog a1=b1.create();
        a1.setView(mview);
        a1.show();*/
        startActivity(new Intent(MainActivity.this,Passforget.class));
    }


    public void start(String uname)
    {
        Intent intent = new Intent(MainActivity.this, AbsentNAvigation.class);
        intent.putExtra("email", uname);
        sessionManager.getUserDetails(uname);
        startActivity(intent);

    }
}
    //Forget password code




