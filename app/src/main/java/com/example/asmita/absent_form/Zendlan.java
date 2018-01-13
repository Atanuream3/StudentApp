package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Zendlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zendlan);
        String url="https://www.tutorialspoint.com/zend_framework/";
        WebView webView=(WebView)findViewById(R.id.zend);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
