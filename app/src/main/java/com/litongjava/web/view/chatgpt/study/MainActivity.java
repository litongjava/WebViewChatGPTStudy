package com.litongjava.web.view.chatgpt.study;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide(); //隐藏ActionBar
    ViewInjectUtils.injectActivity(this, this);


    initPermission();
//    loadWebView();

    String url = "https://chat.openai.com";
    CustomTabsIntent intent = new CustomTabsIntent.Builder()
      //隐藏地址栏
      .setUrlBarHidingEnabled(true)
//      .setShowTitle(false)
      .build();
    intent.launchUrl(MainActivity.this, Uri.parse(url));

  }

  @Override
  protected void onResume() {
    super.onResume();
    // 在这里调用 finish() 来结束您的应用程序
    finish();
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
        } else {
          log.info("onRequestPermissionsResult denied");
          String message = "请前往设置->应用-><本应用名称>->权限中打开相关权限，否则部分功能无法正常运行！";
          AlertDialogUtils.showWaringDialog(this, message);
        }
        return;
      }
    }
  }
}