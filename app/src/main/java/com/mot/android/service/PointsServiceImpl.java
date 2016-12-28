package com.mot.android.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.mot.android.myapplication.R;

/**
 * Created by hemal on 12/27/2016.
 */

public class PointsServiceImpl {

    private static PointsServiceImpl instance = new PointsServiceImpl();

    final public static String  KEY_FILE_NAME = "com.mot.android.service.PointsFileName";
    final public static String  KEY_POINTS = "com.mot.android.service.PointsFileName";

    public static PointsServiceImpl getInstance(){
        return instance;
    }

    public Integer getPoints(Context context, String userId){

        Integer points = 0;

        SharedPreferences sharedPref = context.getSharedPreferences(KEY_FILE_NAME, Context.MODE_PRIVATE);

        String userKey = KEY_POINTS+"."+userId;

        points = sharedPref.getInt(userKey,0);

        return points;
    }

    public void storePoints(Context context, String userId, Integer points){

        System.out.println("**== Storing new points for userId " + userId + " : " + points);

        SharedPreferences sharedPref = context.getSharedPreferences(KEY_FILE_NAME, Context.MODE_PRIVATE);

        String userKey = KEY_POINTS+"."+userId;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(userKey,points);
        editor.commit();

    }

    public void incrementPoints(Context context, String userId){
        Integer points = getPoints(context,userId);
        points = points + 1;
        storePoints(context,userId,points);

    }

    public void decrementPoints(Context context, String userId ){

        Integer points = getPoints(context,userId);
        
        if ( points > 0 ) {
            points = points - 1;
        }

        storePoints(context,userId,points);

    }


    public void resetPoints(Context context, String userId){

        Integer points = 0;
        storePoints(context,userId,points);

    }


}
