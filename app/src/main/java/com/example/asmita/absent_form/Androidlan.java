package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Androidlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidlan);
        String url="https://www.tutorialspoint.com/android/";
        WebView webView=(WebView)findViewById(R.id.android11);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
