package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Database extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        String url="https://www.tutorialspoint.com/sql/index/htm";
        WebView webView=(WebView)findViewById(R.id.oracl);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
