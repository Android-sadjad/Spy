package com.example.spy;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;


public class PlayerDialog extends Dialog {

    NumbersModel numbersModel;



    CallBackNumbers callBackNumbers;

    int counterPlayer;
    int counterSpy;

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
            tvTitle.setText("تعداد بازیکنان");
            tvNumber.setText(String.valueOf(numbersModel.getPlayerNumber()));


        } else if (tag.equals(MyConstant.CL_SPY)) {
            tvTitle.setText("تعداد جاسوس ها");
            tvNumber.setText(String.valueOf(numbersModel.getSpyNumber()));

        }


    }

    private void configuration() {

        ivRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tag.equals(MyConstant.CL_PLAYER)) {
                    counterPlayer = numbersModel.playerNumber;

                    if (counterPlayer < 14) {
                        counterPlayer++;
                    }


                    tvNumber.setText(String.valueOf(counterPlayer));
                    numbersModel.setPlayerNumber(counterPlayer);

                } else if (tag.equals(MyConstant.CL_SPY)) {
                    counterSpy = numbersModel.spyNumber;

                    if (counterSpy < (numbersModel.getPlayerNumber()) / 2) {
                        counterSpy++;
                    }

                    tvNumber.setText(String.valueOf(counterSpy));
                    numbersModel.setSpyNumber(counterSpy);
                }

            }
        });

        ivLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tag.equals(MyConstant.CL_PLAYER)) {
                    counterPlayer = numbersModel.playerNumber;

                    if (counterPlayer > 0) {
                        counterPlayer--;
                    }

                    tvNumber.setText(String.valueOf(counterPlayer));
                    numbersModel.setPlayerNumber(counterPlayer);

                } else if (tag.equals(MyConstant.CL_SPY)) {
                    counterSpy = numbersModel.spyNumber;

                    if (counterSpy > 0) {
                        counterSpy--;
                    }

                    tvNumber.setText(String.valueOf(counterSpy));
                    numbersModel.setSpyNumber(counterSpy);
                }

            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numbersModel.updateNumbersModel(context, numbersModel);
                if (tag.equals(MyConstant.CL_PLAYER)) {
                    callBackNumbers.callNumber(numbersModel.playerNumber);
                } else if (tag.equals(MyConstant.CL_SPY)) {
                    callBackNumbers.callNumber(numbersModel.spyNumber);

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
        ivCancel=findViewById(R.id.iv_cancle);

    }



}
