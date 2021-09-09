package com.example.spy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spy.Interfaces.CallBackNumbers;
import com.example.spy.Classes.MyConstant;
import com.example.spy.Models.NumbersModel;
import com.example.spy.Dialogs.PlayerDialog;
import com.example.spy.R;

public class MainActivity extends AppCompatActivity {

    NumbersModel numbersModel;

    TextView tvPlayerNumber;
    TextView tvSpyNumber;
    TextView tvTimerValue;

    ConstraintLayout clPlayer;
    ConstraintLayout clSpy;
    ConstraintLayout clTimer;
    ConstraintLayout clObjects;

    Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        init();
        setUpNumbers();
        configuration();

    }

    private void setUpNumbers() {
        tvPlayerNumber.setText(String.valueOf(numbersModel.getPlayerNumber()));
        tvSpyNumber.setText(String.valueOf(numbersModel.getSpyNumber()));
        tvTimerValue.setText(String.valueOf(numbersModel.timerValue) +".min");
    }

    public void init() {
        numbersModel = new NumbersModel(MainActivity.this);

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
        clTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              PlayerDialog playerDialog=new PlayerDialog(MainActivity.this, MyConstant.CL_TIMER, new CallBackNumbers() {
                  @Override
                  public void callNumber(int number) {
                      tvTimerValue.setText(String.valueOf(number)+".min");
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
        clObjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public void findViews() {
        tvPlayerNumber = findViewById(R.id.tv_player_number);
        tvSpyNumber = findViewById(R.id.tv_spy_number);
        tvTimerValue = findViewById(R.id.tv_timer_number);

        clObjects = findViewById(R.id.cl_objects);
        clPlayer = findViewById(R.id.cl_player);
        clSpy = findViewById(R.id.cl_spy);
        clTimer = findViewById(R.id.cl_timer);

        btnStart = findViewById(R.id.start_button);

    }
}