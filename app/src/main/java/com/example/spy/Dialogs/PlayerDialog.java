package com.example.spy.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.spy.Activities.MainActivity;
import com.example.spy.Classes.MyConstant;
import com.example.spy.Interfaces.CallBackNumbers;
import com.example.spy.Models.NumbersModel;
import com.example.spy.R;


public class PlayerDialog extends Dialog {

    NumbersModel numbersModel;


    CallBackNumbers callBackNumbers;

    int counterPlayer;
    int counterSpy;
    int counterTimer;

    ConstraintLayout clDialog;

    ImageView ivRightArrow;
    ImageView ivLeftArrow;
    ImageView ivCancel;

    TextView tvNumber;
    TextView tvTitle;

    String tag;
    Context context;
    Button saveBtn;

    public PlayerDialog(@NonNull Context context, String tag, CallBackNumbers callBackNumbers) {
        super(context);

        setContentView(R.layout.dialog_select_number);
        this.tag = tag;
        this.context = context;
        this.callBackNumbers = callBackNumbers;
        findViews();
        init();
        setSize();
        setTransparentBackground();
        configuration();


    }

    private void setTransparentBackground() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    public void setSize() {
        clDialog.getLayoutParams().width = MyConstant.getScreenWidth() * 93 / 100;

    }

    private void init() {
        numbersModel = new NumbersModel(context);

        if (tag.equals(MyConstant.CL_PLAYER)) {
            tvTitle.setText(R.string.number_of_players);
            tvNumber.setText(String.valueOf(numbersModel.getPlayerNumber()));


        } else if (tag.equals(MyConstant.CL_SPY)) {
            tvTitle.setText(R.string.number_of_spies);
            tvNumber.setText(String.valueOf(numbersModel.getSpyNumber()));

        } else if (tag.equals(MyConstant.CL_TIMER)) {
            tvTitle.setText(R.string.time);
            tvNumber.setText(String.valueOf(numbersModel.getTimerValue()) + ".min");

        }


    }

    private void configuration() {

        ivRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tag.equals(MyConstant.CL_PLAYER)) {
                    counterPlayer = numbersModel.getPlayerNumber();


                    if (counterPlayer < 14) {
                        counterPlayer++;
                    }


                    tvNumber.setText(String.valueOf(counterPlayer));
                    numbersModel.setPlayerNumber(counterPlayer);

                } else if (tag.equals(MyConstant.CL_SPY)) {

                    counterSpy = numbersModel.getSpyNumber();



                    if (counterSpy < (numbersModel.getPlayerNumber()) / 2) {
                        counterSpy++;
                    }

                    tvNumber.setText(String.valueOf(counterSpy));
                    numbersModel.setSpyNumber(counterSpy);
                } else if (tag.equals(MyConstant.CL_TIMER)) {


                    counterTimer = numbersModel.getTimerValue();
                    if (counterTimer <= 40) {
                        counterTimer += 5;
                    }
                    tvNumber.setText(String.valueOf(counterTimer) + ".min");
                    numbersModel.setTimerValue(counterTimer);

                }

            }
        });

        ivLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tag.equals(MyConstant.CL_PLAYER)) {
                    counterPlayer = numbersModel.getPlayerNumber();

                    if (counterPlayer > 3) {
                        counterPlayer--;
                    }

                    tvNumber.setText(String.valueOf(counterPlayer));
                    numbersModel.setPlayerNumber(counterPlayer);

                } else if (tag.equals(MyConstant.CL_SPY)) {
                    counterSpy = numbersModel.getSpyNumber();

                    if (counterSpy > 1) {
                        counterSpy--;
                    }

                    tvNumber.setText(String.valueOf(counterSpy));
                    numbersModel.setSpyNumber(counterSpy);
                } else if (tag.equals(MyConstant.CL_TIMER)) {


                    counterTimer = numbersModel.getTimerValue();
                    if (counterTimer > 5) {
                        counterTimer -= 5;
                    }
                    tvNumber.setText(String.valueOf(counterTimer) + ".min");
                    numbersModel.setTimerValue(counterTimer);

                }

            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numbersModel.updateNumbersModel(context, numbersModel);
                if (tag.equals(MyConstant.CL_PLAYER)) {
                    callBackNumbers.callNumber(numbersModel.getPlayerNumber());


                } else if (tag.equals(MyConstant.CL_SPY)) {
                    if (numbersModel.getSpyNumber() > numbersModel.getPlayerNumber() / 2) {
                        Toast.makeText(context,"تعداد جاسوس ها بیش از نصف بازیکنان است", Toast.LENGTH_LONG).show();
                    return;
                    }
                    else {  callBackNumbers.callNumber(numbersModel.getSpyNumber());}


                } else if (tag.equals(MyConstant.CL_TIMER)) {

                    callBackNumbers.callNumber(numbersModel.getTimerValue());
                }


                cancel();


            }
        });


        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


    }

    private void findViews() {
        ivLeftArrow = findViewById(R.id.iv_left_arrow);
        ivRightArrow = findViewById(R.id.iv_right_arrow);
        tvNumber = findViewById(R.id.tv_number);
        tvTitle = findViewById(R.id.tv_title);
        saveBtn = findViewById(R.id.btn_save_player);
        clDialog = findViewById(R.id.cl_dialog_number);
        ivCancel = findViewById(R.id.iv_cancle);

    }


}
