package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Languages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        String url="http://www.tutorialspoint.com/java/";
        WebView webView=(WebView)findViewById(R.id.language);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
