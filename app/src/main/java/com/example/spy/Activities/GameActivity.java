package com.example.spy.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spy.Models.NumbersModel;
import com.example.spy.R;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView tvNameObject;
    private TextView tvNextPlayer;

    private Button btnNextPlayer;
    private ImageView ivLogoCard;


    private ArrayList<String> nameObjectList;

    private int stepCounter = 0;
    private int playerCounter = 0;
    int spyNumber;
    int playerNumber ;

    private long timerSize;
    private Boolean timeRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        findViews();
        init();
        configuration();
    }


    private void findViews() {
        tvNameObject = findViewById(R.id.tv_name_object);
        tvNextPlayer = findViewById(R.id.tv_next_player);

        btnNextPlayer = findViewById(R.id.btn_next);
        ivLogoCard = findViewById(R.id.iv_logo_card);
    }

    private void init() {

        NumbersModel numbersModel = new NumbersModel(GameActivity.this);

        ArrayList<Integer> spyIndexList = new ArrayList<Integer>();
        nameObjectList = new ArrayList<String>();

        timerSize = (int) numbersModel.getTimerValue() * 60000;

        String[] locationList = getResources().getStringArray(R.array.location_list);

        int randomNameIndex = Math.abs(new Random().nextInt() % locationList.length);
        String nameObject = locationList[randomNameIndex];

        spyNumber = numbersModel.getSpyNumber();
        playerNumber = numbersModel.getPlayerNumber();


        for (int i = 0; i < spyNumber; i++) {

            int rand = Math.abs(new Random().nextInt() % playerNumber);

            if (spyIndexList.contains(rand)) {
                i--;
            } else
                spyIndexList.add(rand);
        }

        for (int i = 0; i < playerNumber; i++) {
            if (spyIndexList.contains(i)) {
                nameObjectList.add(getString(R.string.you_are_spy));
            } else
                nameObjectList.add(nameObject);
        }


    }

    private void configuration() {

        btnNextPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNextPlayer.getText().equals(getString(R.string.close))) {
                    finish();
                }
                stepCounter++;
                if (stepCounter % 2 == 0 && stepCounter <= playerNumber * 2 - 1) {

                    ivLogoCard.setVisibility(View.VISIBLE);
                    tvNextPlayer.setVisibility(View.VISIBLE);
                    tvNameObject.setVisibility(View.GONE);
                    tvNameObject.setText(R.string.get_to_next_player);
                    btnNextPlayer.setText(R.string.button_turn_around);

                } else if (stepCounter % 2 != 0 && stepCounter <= playerNumber * 2 - 1) {
                    tvNextPlayer.setVisibility(View.GONE);
                    ivLogoCard.setVisibility(View.GONE);
                    tvNameObject.setVisibility(View.VISIBLE);

                    tvNameObject.setText(nameObjectList.get(playerCounter++));
                    btnNextPlayer.setText(R.string.button_cover_up);
                } else {

                    tvNextPlayer.setVisibility(View.GONE);
                    ivLogoCard.setVisibility(View.GONE);

                    btnNextPlayer.setText(R.string.close);
                    startTimer();

                }


            }
        });


    }

    private void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(timerSize, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerSize = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                tvNameObject.setText(getString(R.string.finish));
            }
        }.start();

    }


    private void updateTimer() {
        int minutes = (int) timerSize / 60000;
        int seconds = (int) timerSize % 60000 / 1000;
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;
        tvNameObject.setText(timeLeftText);
    }

}