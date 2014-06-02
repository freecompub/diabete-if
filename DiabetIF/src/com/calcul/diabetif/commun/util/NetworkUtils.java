package com.calcul.diabetif.commun.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.calcul.diabetif.commun.context.ApplicationContextSingleton;

public class NetworkUtils {

    public static boolean isOnline() {
        Context appContext = ApplicationContextSingleton.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) appContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}