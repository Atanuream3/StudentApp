package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PythonL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python_l);
        String url="https://www.tutorialspoint.com/python/";
        WebView webView=(WebView)findViewById(R.id.python1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}
