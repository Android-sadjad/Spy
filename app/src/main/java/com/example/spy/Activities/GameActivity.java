package com.example.spy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spy.Models.NumbersModel;
import com.example.spy.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    int randomName;
    List<Integer> randomSpy;

    int counterPlayer=0;
    int counterSpy=0;

    ArrayList<String> nameList;

    NumbersModel numbersModel;

    TextView tvNameObject;
    Button btnNextPlayer;

    String nameObject;


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
        btnNextPlayer = findViewById(R.id.next_btn);
    }

    private void init() {
        numbersModel=new NumbersModel(GameActivity.this);
        randomSpy=new ArrayList<Integer>();

        String[] locationList=getResources().getStringArray(R.array.location_list);

        randomName = new Random().nextInt() % locationList.length;
        if (randomName<0){
            randomName*=-1;
        }
        nameObject=locationList[randomName];



        int spyNumber=numbersModel.getSpyNumber();
        int playerNumber=numbersModel.getPlayerNumber();


        for (int i=0;i<spyNumber;i++){
            randomSpy.add(new Random().nextInt() % playerNumber);
        }



    }

    private void configuration() {

        btnNextPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNextPlayer.getText().equals(R.string.close)){
                  finish();
                }
                counterPlayer++;
                if (counterPlayer%2==0 && counterPlayer<=(numbersModel.getPlayerNumber())*2-1){
                    tvNameObject.setText(R.string.get_to_next_player);
                    btnNextPlayer.setText(R.string.button_cover_up);
                }else if(counterPlayer%2!=0 && counterPlayer<=(numbersModel.getPlayerNumber())*2-1){

                    tvNameObject.setText(nameObject);
                    btnNextPlayer.setText(R.string.button_turn_around);
                }else {
                    btnNextPlayer.setText(R.string.close);
                    tvNameObject.setText("تایمر");

                }


            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}