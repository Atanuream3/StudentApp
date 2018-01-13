package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Mygo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygo);
        String url="https://developers.google.com/";
        WebView webView=(WebView)findViewById(R.id.mygo);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
