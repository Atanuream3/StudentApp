package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Joomlaopenlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joomlaopenlan);
        String url="https://www.tutorialspoint.com/joomla/";
        WebView webView=(WebView)findViewById(R.id.jjj);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
