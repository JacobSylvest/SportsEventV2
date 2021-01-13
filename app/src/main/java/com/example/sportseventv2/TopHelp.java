package com.example.sportseventv2;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TopHelp extends TopBundMenu {

    private WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_help);

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true); //aktiverer javascript
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.geotrail.dk");
        showNavProfil();
    }
}