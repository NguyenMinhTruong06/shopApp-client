package com.example.showappclient.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertMessageViewer {
    public static void showAlertDialogMessage(Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle(null)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public static void showAlertZalopay(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
