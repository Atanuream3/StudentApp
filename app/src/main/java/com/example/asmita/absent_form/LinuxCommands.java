package com.example.asmita.absent_form;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LinuxCommands extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linux_commands);
        String url="http://searchdatacenter.techtarget.com/tutorial/77-Linux-commands-and-utilities-youll-actually-use";
        WebView webView=(WebView)findViewById(R.id.linuxcommands);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
