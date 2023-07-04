package com.litongjava.android.utils;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/28
 */
public class PermissionUtils {
  private static Logger log = LoggerFactory.getLogger(PermissionUtils.class);

  public static void requestPermission(Activity activity, String permission, int requestCode) {
    boolean b = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    log.info("checkSelfPermission:{},result:{}", permission,b);
    if (!b) {
      //申请权限
      if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
        log.info("requestPermission:{}", permission);
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
      } else {
        log.info("requestPermission:{}", permission);
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
      }
    }
  }
}