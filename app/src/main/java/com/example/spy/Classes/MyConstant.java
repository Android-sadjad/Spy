package com.example.spy.Classes;

import android.content.res.Resources;

public class MyConstant {


    public static final int SPLASH_SCREEN_DURATION = 1500;

    public static final String CL_PLAYER = "cl_player";
    public static final String CL_SPY = "cl_spy";
    public static final String CL_TIMER = "cl_timer";
    public static final String CL_OBJECTS = "cl_objects";

    public static final int MIN_PLAYER_NUMBER = 3;
    public static final int MAX_PLAYER_NUMBER = 30;
    public static final int MAX_GAME_TIME = 120;
    public static final int MIN_SPY_NUMBER = 1;
    public static int MIN_Time = 1;


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}
