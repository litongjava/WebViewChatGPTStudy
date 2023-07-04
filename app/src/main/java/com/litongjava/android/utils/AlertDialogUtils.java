package com.litongjava.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

/**
 * @author Ping E Lee
 * @email itonglinux@qq.com
 * @date 2022/1/28
 */
public class AlertDialogUtils {
  public static void showWaringDialog(Context context, String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder = builder.setTitle("警告！")
      .setMessage(message)
      .setPositiveButton("确定", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
      });
    builder.show();
  }
}