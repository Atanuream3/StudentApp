package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AngularJs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angular_js);

        String url="https://www.tutorialspoint.com/angularjs/angularjs_overview.htm";
       WebView webView=(WebView)findViewById(R.id.js1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
