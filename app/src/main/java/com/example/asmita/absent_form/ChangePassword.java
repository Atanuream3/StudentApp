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

public class ChangePassword extends AppCompatActivity {
    EditText current,newp,retype;
    Button submit;
    String EmailName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        current=(EditText)findViewById(R.id.currentpass);
        newp=(EditText)findViewById(R.id.newpassword);
        retype=(EditText)findViewById(R.id.retypepass);
        Intent intent=getIntent();
        EmailName=intent.getExtras().getString("Email");
        Toast.makeText(getApplicationContext(),EmailName,Toast.LENGTH_LONG).show();

    }
    @Override
    public void onBackPressed()
    {
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
           // case R.id.item1:
             //   Toast.makeText(this, "Rate us is Selected", Toast.LENGTH_SHORT).show();
               // return true;
            case R.id.item2:
                finishAffinity();
                //Toast.makeText(this,"Item 2 is Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                ProgressDialog progressDialog=new ProgressDialog(ChangePassword.this);
                progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
                progressDialog.setMessage("See You Soon..");
                progressDialog.show();


                Intent intent=new Intent(ChangePassword.this,MainActivity.class);
                progressDialog.dismiss();
                startActivity(intent);
               //Toast.makeText(this, "Item 3 is Selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void submit(View view)
    {
        final String cpass=current.getText().toString();
        final String npass=newp.getText().toString();
        final String rpass=retype.getText().toString();


        if(cpass.isEmpty() && npass.isEmpty() && rpass.isEmpty())
        {
            Toast.makeText(this,"All fields are required",Toast.LENGTH_LONG).show();
        }
        else if(cpass.isEmpty())
        {
            Toast.makeText(this,"current password is required",Toast.LENGTH_LONG).show();

        }
        else if(npass.isEmpty())
        {
            Toast.makeText(this,"New password is required",Toast.LENGTH_LONG).show();
        }
        else if(rpass.isEmpty())
        {
            Toast.makeText(this,"Retype password is required",Toast.LENGTH_LONG).show();
        }
        else if(!(npass.equals(rpass)))
        {
            Toast.makeText(this,"password retype agian don't match",Toast.LENGTH_LONG).show();
        }
        else
        {
            final ProgressDialog progressDialog=new ProgressDialog(ChangePassword.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Please Wait..");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Change_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //response.toString();
                            //If we are getting success from server
                            if (response.trim().equals(cpass)) {
                                //Creating a shared preference
                                // SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                //SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editoroa
                                //editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                //editor.putString(Config.EMAIL_SHARED_PREF, username);

                                //Saving values to editor
                                //editor.commit();

                                //Starting profile activity
                             //   Intent intent = new Intent(ChangePassword.this, ReasonForm.class);
                               // startActivity(intent);
                                progressDialog.dismiss();
                                Toast.makeText(ChangePassword.this,"Password Changed ",Toast.LENGTH_SHORT).show();
                                // User.setText(password);
                            } else {
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(ChangePassword.this,response,Toast.LENGTH_LONG).show();
                                            Toast.makeText(ChangePassword.this, "Something went wrong", Toast.LENGTH_LONG).show();
                                //User.setText(response);
                                //User.setText(response);
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
                    //Adding parameters to request
                    params.put(Config.Password,cpass);
                    params.put(Config.newpass,npass);
                    params.put(Config.Email,EmailName);


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
}




