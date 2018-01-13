package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Monolan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monolan);
        String url="http://www.mono-project.com/archived/monkeyguide/";
      //String url="https://google.com";
        WebView webView=(WebView)findViewById(R.id.mono);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
