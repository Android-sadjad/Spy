package com.example.spy.Classes;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyConstant {


    public static final int SPLASH_SCREEN_DURATION = 1500;

    public static final String CL_PLAYER = "cl_player";
    public static final String CL_SPY = "cl_spy";
    public static final String CL_TIMER = "cl_timer";
    public static final String CL_OBJECTS = "cl_objects";





    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }



}
