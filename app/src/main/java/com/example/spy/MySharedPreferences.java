package com.example.spy;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

public class MySharedPreferences {

    Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static MySharedPreferences mySharedPreferences = null;

    public static MySharedPreferences getInstance(Context context) {
        if (mySharedPreferences == null) {
            mySharedPreferences = new MySharedPreferences(context);
        }


        return mySharedPreferences;
    }


    public MySharedPreferences(Context context) {

        sharedPreferences = context.getSharedPreferences("setNumbers", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        this.context = context;

    }


    public void putNumberModel(NumbersModel numbersModel) {

        String numberStr = gson.toJson(numbersModel, NumbersModel.class);
        editor.putString("numbersSetting", numberStr).apply();
    }

    public NumbersModel getNumberModel() {

        String numberStr = sharedPreferences.getString("numbersSetting", null);
        if (numberStr == null) {
            return null;
        }
        return gson.fromJson(numberStr, NumbersModel.class);

    }

}
