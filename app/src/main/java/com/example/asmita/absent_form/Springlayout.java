package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Springlayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_springlayout);
        String url="https://www.tutorialspoint.com./spring/";
        WebView webView=(WebView)findViewById(R.id.spring1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
