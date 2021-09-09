package com.example.spy.Models;

import android.content.Context;

import com.example.spy.Classes.MySharedPreferences;

public class NumbersModel {

    public int playerNumber;
    public int spyNumber;
    public int timerValue;

    public NumbersModel(Context context) {
        NumbersModel numbersModel = MySharedPreferences.getInstance(context).getNumberModel();


        if (numbersModel == null) {
            playerNumber = 5;
            spyNumber = 1;
            timerValue = 5;

            updateNumbersModel(context, this);

        } else {

            playerNumber = numbersModel.getPlayerNumber();
            spyNumber = numbersModel.getSpyNumber();
            timerValue = numbersModel.getTimerValue();

        }


    }


    public void updateNumbersModel(Context context, NumbersModel numbersModel) {

        MySharedPreferences.getInstance(context).putNumberModel(numbersModel);
    }


    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getSpyNumber() {
        return spyNumber;
    }

    public void setSpyNumber(int spyNumber) {
        this.spyNumber = spyNumber;
    }

    public int getTimerValue() {
        return timerValue;
    }

    public void setTimerValue(int timerValue) {
        this.timerValue = timerValue;
    }
}
