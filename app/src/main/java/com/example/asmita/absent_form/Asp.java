package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Asp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asp);
        String url="https://www.tutorialspoint.com/asp.net/";
        WebView webView=(WebView)findViewById(R.id.Asp1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
