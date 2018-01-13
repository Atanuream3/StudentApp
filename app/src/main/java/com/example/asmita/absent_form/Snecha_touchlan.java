package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Snecha_touchlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snecha_touchlan);
        String url="https://www.tutorialspoint.com/sencha_touch/";
        WebView webView=(WebView)findViewById(R.id.snecha);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
