package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ReactJs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_js);
        String url="https://www.tutorialspoint.com/reactjs/";
        WebView webView=(WebView)findViewById(R.id.reactjs);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
