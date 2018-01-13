package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebDevelopement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_developement);
        String url="https://www.w3schools.com";
        WebView webView=(WebView)findViewById(R.id.beweb);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
