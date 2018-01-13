package com.example.asmita.absent_form;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Linux extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linux);
        String url="https://www.javatpoint.com/linux-tutorial";
       // String url1="http://www.tutorialspoint.com/operating_system/os_linux.htm";
        WebView webView=(WebView)findViewById(R.id.linux);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
     //   webView.loadUrl(url1);


    }


}
