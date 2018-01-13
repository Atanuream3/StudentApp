package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Csharp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csharp);
        String url="http://www.tutorialspoint.com/csharp/";
        WebView webView=(WebView)findViewById(R.id.sharp);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
