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

import com.example.spy.Classes.MyConstant;
import com.example.spy.Interfaces.CallBackNumbers;
import com.example.spy.Models.NumbersModel;
import com.example.spy.R;

public class PlayerDialog extends Dialog {

    private ConstraintLayout clDialog;

    private ImageView ivRightArrow;
    private ImageView ivLeftArrow;
    private ImageView ivCancel;

    private TextView tvNumber;
    private TextView tvTitle;
    private Button saveBtn;

    private NumbersModel numbersModel;
    private final CallBackNumbers callBackNumbers;

    private int counter;

    private final String tag;


    public PlayerDialog(@NonNull Context context, String tag, CallBackNumbers callBackNumbers) {
        super(context);
        setContentView(R.layout.dialog_select_number);
        setCancelable(false);

        this.tag = tag;
        this.callBackNumbers = callBackNumbers;

        findViews();
        setTransparentBackground();
        setViewSize();
        init();
        configuration();

    }


    private void findViews() {

        clDialog = findViewById(R.id.cl_dialog_number);

        tvTitle = findViewById(R.id.tv_title);
        tvNumber = findViewById(R.id.tv_number);

        ivLeftArrow = findViewById(R.id.iv_left_arrow);
        ivRightArrow = findViewById(R.id.iv_right_arrow);

        saveBtn = findViewById(R.id.btn_save_player);
        ivCancel = findViewById(R.id.iv_cancle);
    }

    private void setTransparentBackground() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    public void setViewSize() {
        clDialog.getLayoutParams().width = MyConstant.getScreenWidth() * 93 / 100;

    }

    private void init() {

        numbersModel = new NumbersModel(getContext());

        switch (tag) {

            case MyConstant.CL_PLAYER:
                counter=numbersModel.getPlayerNumber();
                tvTitle.setText(R.string.number_of_players);
                tvNumber.setText(String.valueOf(counter));
                break;

            case MyConstant.CL_SPY:
                counter=numbersModel.getSpyNumber();
                tvTitle.setText(R.string.number_of_spies);
                tvNumber.setText(String.valueOf(counter));
                break;

            case MyConstant.CL_TIMER:
                counter=numbersModel.getTimerValue();
                tvTitle.setText(R.string.time);
                tvNumber.setText(counter+getContext().getString(R.string.minute));
                break;
        }

    }

    private void configuration() {

        ivRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (tag) {

                    case MyConstant.CL_PLAYER:

                        if (counter < MyConstant.MAX_PLAYER_NUMBER) {
                            counter++;
                        }
                        else
                            Toast.makeText(getContext(), "حداکثر تعداد بازیکنان 20 نفر میباشد", Toast.LENGTH_SHORT).show();

                        tvNumber.setText(String.valueOf(counter));
                        break;

                    case MyConstant.CL_SPY:

                        if (counter < (numbersModel.getPlayerNumber()) / 2) {
                            counter++;
                        }
                        else {
                            Toast.makeText(getContext(), "تعداد جاسوس ها باید کمتر از نصف تعداد بازیکنان باشد", Toast.LENGTH_LONG).show();
                            return;
                        }

                        tvNumber.setText(String.valueOf(counter));
                        break;

                    case MyConstant.CL_TIMER:

                        if (counter <= MyConstant.MAX_GAME_TIME) {
                            counter ++;
                        }
                        tvNumber.setText(counter + getContext().getString(R.string.minute));

                        break;
                }

            }
        });

        ivLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (tag) {
                    case MyConstant.CL_PLAYER:

                        if(counter<=numbersModel.getSpyNumber()*2){
                            Toast.makeText(getContext(), "تعداد بازیکنان باید بیشتر از 2 برابر تعداد جاسوس ها باشد.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (counter > MyConstant.MIN_PLAYER_NUMBER) {
                            counter--;
                        }
                        else
                            Toast.makeText(getContext(), "حداقل تعداد بازیکنان 3 نفر میباشد", Toast.LENGTH_SHORT).show();

                        tvNumber.setText(String.valueOf(counter));
                        break;

                    case MyConstant.CL_SPY:


                        if (counter > MyConstant.MIN_SPY_NUMBER) {
                            counter--;
                        }
                        else
                            Toast.makeText(getContext(), "حداقل تعداد جاسوس ها 1 نفر است", Toast.LENGTH_SHORT).show();

                        tvNumber.setText(String.valueOf(counter));
                        break;

                    case MyConstant.CL_TIMER:

                        if (counter > MyConstant.MIN_Time) {
                            counter --;
                        }
                        else
                            Toast.makeText(getContext(), "کمترین زمان بازی 1 دقیقه است", Toast.LENGTH_SHORT).show();
                        tvNumber.setText(counter+getContext().getString(R.string.minute));

                        break;
                }

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (tag) {

                    case MyConstant.CL_PLAYER:
                        numbersModel.setPlayerNumber(counter);
                        break;
                    case MyConstant.CL_SPY:
                        numbersModel.setSpyNumber(counter);
                        break;
                    case MyConstant.CL_TIMER:
                        numbersModel.setTimerValue(counter);

                        break;
                }

                numbersModel.updateNumbersModel(getContext(), numbersModel);
                callBackNumbers.callNumber(counter);

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

}
