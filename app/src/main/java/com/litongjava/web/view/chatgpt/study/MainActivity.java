package com.litongjava.web.view.chatgpt.study;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private WebView myWebView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getSupportActionBar().hide(); //隐藏ActionBar
    setContentView(R.layout.activity_main);

    myWebView = (WebView) findViewById(R.id.webview);
    myWebView.loadUrl("https://chat.openai.com/");

    WebSettings webSettings = myWebView.getSettings();
    // 开启 JavaScript（如果需要的话）
    webSettings.setJavaScriptEnabled(true);

    // 开启 DOM storage API
    webSettings.setDomStorageEnabled(true);

    // 开启 database storage API
    webSettings.setDatabaseEnabled(true);

    // 设置缓存模式
    webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

    myWebView.setWebViewClient(new WebViewClient());
  }
}