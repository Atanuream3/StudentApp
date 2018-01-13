package com.example.asmita.absent_form;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AbsentNAvigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//to check is user logged in or not

    SessionManager sessionManager;
    String email;
    ImageView image;
    TextView name, ur1;
    Button networking, linux;

    String hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        networking = (Button) findViewById(R.id.networking);
        linux = (Button) findViewById(R.id.opensource);
        View header = navigationView.getHeaderView(0);
        image = (ImageView) header.findViewById(R.id.imageview);
        ur1 = (TextView) findViewById(R.id.url1);
        name = (TextView) header.findViewById(R.id.name);
        sessionManager = new SessionManager(getApplicationContext());
        //Toast.makeText(getApplicationContext(),"status"+sessionManager.isUserLoggedIn(),Toast.LENGTH_LONG).show();
        if (sessionManager.checkLogin())
            finish();
        Intent intent = getIntent();
        email = intent.getExtras().getString("email");
        email = sessionManager.getuser();
        //Toast.makeText(this,email,Toast.LENGTH_LONG).show();
        sessionManager.getUserDetails(email);
        //Toast.makeText(getApplicationContext(),sessionManager.getuser(),Toast.LENGTH_LONG).show();

        fetch();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.absent_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.update) {

            Intent intent = new Intent(this, ChangeProfilePhoto.class);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
        }

        if (id == R.id.chpass) {
            Intent intent;

            intent = new Intent(getApplicationContext(), ChangePassword.class);
            intent.putExtra("Email", email);
            startActivity(intent);


        }
        if (id == R.id.exit) {
            finishAffinity();
        }
        if (id == R.id.signout) {
            sessionManager.editor.clear();
            sessionManager.editor.commit();


            startActivity(new Intent(AbsentNAvigation.this, MainActivity.class));

        }
        if (id == R.id.account) {
           // Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(AbsentNAvigation.this, ViewProfile.class);
            intent2.putExtra("Email", email);
            startActivity(intent2);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment f1 = null;
        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(AbsentNAvigation.this, ReasonForm.class));

        } else if (id == R.id.nav_gallery) {

            //f1=new BlankFragment();
            startActivity(new Intent(AbsentNAvigation.this, Schedule.class));

        } else if (id == R.id.nav_slideshow) {


            AlertDialog.Builder builder=new AlertDialog.Builder(AbsentNAvigation.this);
            builder.setMessage("Are you sure you want to exit");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) { //Yes Button


                    delete();
                   AbsentNAvigation.this.finish();

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

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(AbsentNAvigation.this, feedback.class));

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(AbsentNAvigation.this, Develpoer.class));

        } else if (id == R.id.nav_send) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<p> RASM"));

            Intent chooser=Intent.createChooser(intent,"Send");
            startActivity(chooser);

        }

        if (f1 != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rel, f1);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //fetch Image
    public void fetch() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Image_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        showJson(response);
                        Toast.makeText(AbsentNAvigation.this, response, Toast.LENGTH_SHORT).show();
                        //Picasso.with(getApplication()).load(response).into(image);
                        // User.setText(password);

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
                params.put(Config.Email, email);


                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void delete()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.delete_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.trim().equals(Config.delete_url_success)) {
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

                            Toast.makeText(AbsentNAvigation.this,"Account Deleted Successfully",Toast.LENGTH_SHORT).show();
                            sessionManager.editor.clear();
                            sessionManager.editor.commit();

                            startActivity(new Intent(AbsentNAvigation.this, MainActivity.class));

                            // User.setText(password);
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
                params.put(Config.Email, email);


                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    public void showJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray Result = jsonObject.getJSONArray("Result");
            JSONObject collect = Result.getJSONObject(0);
            String url = collect.getString("Image");
            String name1 = collect.getString("name");
            name.setText(name1);
            Picasso.with(getApplicationContext()).load(url).into(image);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void openlinux(View view) {

        startActivity(new Intent(this, Linux.class));
    }

    public void openNet(View view) {

        startActivity(new Intent(this, Networking.class));
    }
    public void joomlaopen(View view)
    {
        startActivity(new Intent(this,Joomlaopenlan.class));
    }
    public void zendopen(View view)
    {
        startActivity(new Intent(this,Zendlan.class));
    }

    public void javat(View view)
    {
        startActivity(new Intent(this, Languages.class));
    }

    public void py(View view)

    {
        startActivity(new Intent(this,PythonL.class));
    }

    public void xamarinopen(View view)
    {
        startActivity(new Intent(this,Xamarinlan.class));

    }
    public void androidopen(View view)
    {
        startActivity(new Intent(this,Androidlan.class));
    }

    public void monoopen(View view)
    {
        startActivity(new Intent(this,Monolan.class));
    }
    public void senchaopen(View view)
    {
       startActivity(new Intent(this,Snecha_touchlan.class));
    }
    public void language(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        View nview=getLayoutInflater().inflate(R.layout.newlanguages,null);

        AlertDialog dialog=builder.create();
        dialog.setView(nview);
        dialog.show();

        //startActivity(new Intent(this,Languages.class));
    }

    //public void deleteAccount(View view)
   // {
     //   startActivity(new Intent(this,MainActivity.class));
    //}

    public void nodeopen(View view)
    {
        startActivity(new Intent(this,AngularJs.class));
    }

    public void nodejsopen(View view)
    {

        startActivity(new Intent(this,Node_Js.class));
    }

    public void reactjsopen(View view)
    {
        startActivity(new Intent(this,ReactJs.class));
    }

    public void linuxopen(View view)
    {
        startActivity(new Intent(this,LinuxCommands.class));
    }

   public void google(View view)
   {
       startActivity(new Intent(this,Mygo.class));
   }

   public void one(View view)
   {
      startActivity(new Intent(this,WebDevelopement.class));
   }

   public void ASP(View view)
   {
       startActivity(new Intent(this,Asp.class));
   }
   public void PHP(View view)
   {
       startActivity(new Intent(this,Php.class));
   }

   public void springopen(View view)
   {
       startActivity(new Intent(this,Springlayout.class));
   }
   public void webd(View view)
   {
       AlertDialog.Builder builder=new AlertDialog.Builder(this);
       View wview=getLayoutInflater().inflate(R.layout.webapp,null);
       AlertDialog dialog=builder.create();
       dialog.setView(wview);
       dialog.show();

       //startActivity(new Intent(this,WebDevelopement.class));
   }

   public void database(View view)
   {
       startActivity(new Intent(this,Database.class));
   }
   public void CSHARP(View view)
   {
       startActivity(new Intent(this,Csharp.class));
   }

   public void drupleopen(View view)
   {
       startActivity(new Intent(this,Drupallan.class));
   }
    public void linus(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View  mview=getLayoutInflater().inflate(R.layout.opensource,null);
        AlertDialog dialog=builder.create();
        dialog.setView(mview);
        dialog.show();
    }

}
