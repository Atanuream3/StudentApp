package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Drupallan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drupallan);
        String url="https://www.tutorialspoint.com/drupal";
        WebView webView=(WebView)findViewById(R.id.druple111);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
