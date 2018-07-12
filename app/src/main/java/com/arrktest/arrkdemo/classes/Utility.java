package com.arrktest.arrkdemo.classes;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;

public class Utility {

    public static double getMetersFromCM(int cms) {
        double meters = (double) cms / 100;
        return meters;
    }

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
