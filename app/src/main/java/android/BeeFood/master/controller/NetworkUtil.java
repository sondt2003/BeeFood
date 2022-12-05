package android.BeeFood.master.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static String getNetWorkState(Context context) {
        String status = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi Connected";
                return status;
            } else if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                status="Mobile data connected";
                return status;
            }
        } else {
            status="No internet";
            return status;
        }
        return status;
    }
}