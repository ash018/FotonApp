package com.mis.smakash.fotonapp;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utility {

    public static final int NETWORK_STATUS_NOT_CONNECTED = 0, NETWORK_STAUS_WIFI = 1, NETWORK_STATUS_MOBILE = 2;

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;



    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static int getConnectivityStatusString(Context context) {
        int conn = Utility.getConnectivityStatus(context);
        int status = 0;
        if (conn == Utility.TYPE_WIFI) {
            status = NETWORK_STAUS_WIFI;
        } else if (conn == Utility.TYPE_MOBILE) {
            status = NETWORK_STATUS_MOBILE;
        } else if (conn == Utility.TYPE_NOT_CONNECTED) {
            status = NETWORK_STATUS_NOT_CONNECTED;
        }
        return status;
    }

    public Boolean isConnected(Context context) {
        Boolean status = false;

        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInf = conMgr.getAllNetworkInfo();

        for (NetworkInfo inf : netInf) {
            if (inf != null) {
                if (inf.getTypeName().toUpperCase().contains("WIFI")
                        || inf.getTypeName().toUpperCase().contains("MOBILE")) {
                    if (inf.getState() == NetworkInfo.State.CONNECTED) {
                        status = true;
                        break;
                    }
                }
            }
        }

        return status;

    }

    public static void showOkDialog(Context context, String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setCancelable(true);
        alert.setMessage(message);
        alert.setPositiveButton("OK", null);
        alert.show();
    }
}
