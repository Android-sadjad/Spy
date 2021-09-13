package com.example.spy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.spy.Classes.MyConstant;
import com.example.spy.Dialogs.PlayerDialog;
import com.example.spy.Interfaces.CallBackNumbers;
import com.example.spy.Models.NumbersModel;
import com.example.spy.R;

public class MainActivity extends AppCompatActivity {

    private TextView tvPlayerNumber;
    private TextView tvSpyNumber;
    private TextView tvTimerValue;

    private ConstraintLayout clPlayer;
    private ConstraintLayout clSpy;
    private ConstraintLayout clTimer;
    private ConstraintLayout clObjects;

    private Button btnStart;

    private NumbersModel numbersModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        init();
        setUpNumbers();
        configuration();

    }


    public void findViews() {
        tvPlayerNumber = findViewById(R.id.tv_player_number);
        tvSpyNumber = findViewById(R.id.tv_spy_number);
        tvTimerValue = findViewById(R.id.tv_timer_number);

        clObjects = findViewById(R.id.cl_objects);
        clPlayer = findViewById(R.id.cl_player);
        clSpy = findViewById(R.id.cl_spy);
        clTimer = findViewById(R.id.cl_timer);

        btnStart = findViewById(R.id.btn_start_game);

    }

    public void init() {

        numbersModel = new NumbersModel(MainActivity.this);

    }

    private void setUpNumbers() {

        tvPlayerNumber.setText(String.valueOf(numbersModel.getPlayerNumber()));
        tvSpyNumber.setText(String.valueOf(numbersModel.getSpyNumber()));
        tvTimerValue.setText(numbersModel.getTimerValue() + getString(R.string.minute));
    }

    public void configuration() {

        clPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerDialog playerDialog = new PlayerDialog(MainActivity.this, MyConstant.CL_PLAYER, new CallBackNumbers() {
                    @Override
                    public void callNumber(int number) {

                        tvPlayerNumber.setText(String.valueOf(number));

                    }

                });
                playerDialog.show();

            }
        });

        clSpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerDialog spyDialog = new PlayerDialog(MainActivity.this, MyConstant.CL_SPY, new CallBackNumbers() {
                    @Override
                    public void callNumber(int number) {
                        tvSpyNumber.setText(String.valueOf(number));
                    }

                });
                spyDialog.show();

            }
        });

        clTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerDialog playerDialog = new PlayerDialog(MainActivity.this, MyConstant.CL_TIMER, new CallBackNumbers() {
                    @Override
                    public void callNumber(int number) {
                        tvTimerValue.setText(number + getString(R.string.minute));
                    }
                });
                playerDialog.show();

            }
        });

        clObjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbersModel = new NumbersModel(MainActivity.this);

                if (numbersModel.getSpyNumber() > numbersModel.getPlayerNumber() / 2) {
                    Toast.makeText(MainActivity.this, getString(R.string.more_than_half_spy), Toast.LENGTH_LONG).show();
                    return;
                } else {
                    startActivity(new Intent(MainActivity.this, GameActivity.class));

                }


            }
        });

    }

}