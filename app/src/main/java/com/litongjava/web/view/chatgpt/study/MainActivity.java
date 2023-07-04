package com.litongjava.web.view.chatgpt.study;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.litongjava.android.utils.AlertDialogUtils;
import com.litongjava.android.utils.PermissionUtils;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

  private static final int permissionRequestCode = 10024;
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @FindViewById(R.id.webview)
  private WebView myWebView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide(); //隐藏ActionBar
    ViewInjectUtils.injectActivity(this, this);

    initPermission();

  }

  private void initPermission() {
    PermissionUtils.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, permissionRequestCode);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case permissionRequestCode: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          log.info("onRequestPermissionsResult granted");
          loadWebView();
        } else {
          log.info("onRequestPermissionsResult denied");
          String message = "请前往设置->应用-><本应用名称>->权限中打开相关权限，否则部分功能无法正常运行！";
          AlertDialogUtils.showWaringDialog(this, message);
          loadWebView();
        }
        return;
      }
    }
  }

  private void loadWebView() {
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

    // 启用 WebView 内容访问
    webSettings.setAllowContentAccess(true);

    // 启用文件访问
    webSettings.setAllowFileAccess(true);

    // 启用剪贴板访问
    webSettings.setAllowUniversalAccessFromFileURLs(true);
    webSettings.setAllowFileAccessFromFileURLs(true);

    myWebView.setWebViewClient(new WebViewClient());
  }
}