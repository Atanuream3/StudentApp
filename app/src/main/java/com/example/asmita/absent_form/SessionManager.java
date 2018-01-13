package com.example.asmita.absent_form;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by asmita on 26/6/17.
 */

public class SessionManager {
    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;
    int Private_Mode=0;
    private static final String Prefer_Name="Asmita";

    private static final String Is_User_Login="IsUserLogin";

     public static final String Key_Email="email";

    public SessionManager(Context context) {
        this.context = context;
        preferences=context.getSharedPreferences(Prefer_Name,Private_Mode);
        editor=preferences.edit();

    }
    public void  createLoginSession(String email)
    {
        editor.putBoolean(Is_User_Login,true);
        editor.putString(Key_Email,email);
        editor.commit();
    }

    public String getuser()
    {
       return preferences.getString(Key_Email,"Default name");
    }
    public  boolean checkLogin()
    {
        //check login status
       if(!this.isUserLoggedIn())
       {
           Intent intent=new Intent(context,MainActivity.class);

           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

           context.startActivity(intent);

           return true;

       }
       return false;

    }

    public HashMap<String,String> getUserDetails(String email)
    {
        HashMap<String,String> user=new HashMap<>();
        user.put(Key_Email,email);
        return user;
    }

    public boolean isUserLoggedIn()
    {

        return preferences.getBoolean(Is_User_Login,false);
    }




}
