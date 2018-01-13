package com.example.asmita.absent_form;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class feedback extends AppCompatActivity {
    EditText name,feedback;
    TextView changepassword;
    Button submit;
    String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        name=(EditText)findViewById(R.id.feedback);
        feedback=(EditText)findViewById(R.id.feedback1);
        submit= (Button) findViewById(R.id.button);
        //changepassword= (TextView) findViewById(R.id.change);
        //Intent intent=getIntent();
         //name1=intent.getExtras().getString("email");
        //Toast.makeText(getApplicationContext(),name1,Toast.LENGTH_LONG).show();

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
            //case R.id.item1:
              //  Toast.makeText(this, "Rate us is Selected", Toast.LENGTH_SHORT).show();
                //return true;
            case R.id.item2:
                finishAffinity();
                //Toast.makeText(this,"Item 2 is Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                ProgressDialog progressDialog=new ProgressDialog(feedback.this);
                progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
                progressDialog.setMessage("See You Soon..");
                progressDialog.show();

                 Intent intent1=new Intent(feedback.this,MainActivity.class);

                 startActivity(intent1);
                progressDialog.dismiss();
                //Toast.makeText(this, "Item 3 is Selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void submit(View view)
    {
         final String uname=name.getText().toString();
        final String feed=feedback.getText().toString();
         // new feedback();

        if(uname.isEmpty() && feed.isEmpty())
        {
            Toast.makeText(this,"All fields are required",Toast.LENGTH_LONG).show();
        }
        else
            if(uname.isEmpty())
            {
                Toast.makeText(this,"username os required",Toast.LENGTH_LONG).show();
            }
         else
             if(feed.isEmpty())
             {
                 Toast.makeText(this,"feedback is requireed",Toast.LENGTH_LONG).show();
             }
        else
             {
                 final ProgressDialog progressDialog=new ProgressDialog(feedback.this);
                 progressDialog.setIndeterminate(true);
                 progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
                 progressDialog.setMessage("While Uploading your feedback..");
                 progressDialog.setTitle("Please Wait");

                 progressDialog.show();

                 StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Feedback_URL,
                         new Response.Listener<String>() {
                             @Override
                             public void onResponse(String response) {
                                 //response.toString();
                                 //If we are getting success from server
                                 if (response.trim().equals(Config.feedback_success)) {
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
                                     progressDialog.dismiss();
                                     Intent intent = new Intent(feedback.this, ReasonForm.class);
                                     startActivity(intent);
                                     Toast.makeText(feedback.this,"Thank you for valueable feedback",Toast.LENGTH_SHORT).show();
                                     // User.setText(password);
                                 } else {
                                     //If the server response is not success
                                     //Displaying an error message on toast
                                    // Toast.makeText(feedback.this,response,Toast.LENGTH_LONG).show();
                                     Toast.makeText(feedback.this, "Something went wrong", Toast.LENGTH_LONG).show();
                                     //User.setText(response);
                                     //User.setText(response);
                                 }
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
                         params.put(Config.name,uname);
                         params.put(Config.Response,feed);

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
