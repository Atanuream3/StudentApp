package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Node_Js extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node__js);
        String url="https://www.tutorialspoint.com/nodejs";
        WebView webView=(WebView)findViewById(R.id.nodejs);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
