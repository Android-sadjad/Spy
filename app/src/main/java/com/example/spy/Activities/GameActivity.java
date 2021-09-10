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
    ArrayList<Integer> randomSpy;
    ArrayList<String> nameList;

    int counterPlayer=0;
    int counter=0;



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
        nameList=new ArrayList<String>();

        String[] locationList=getResources().getStringArray(R.array.location_list);

        randomName = new Random().nextInt() % locationList.length;
        if (randomName<0){
            randomName*=-1;
        }
        nameObject=locationList[randomName];

        int spyNumber=numbersModel.getSpyNumber();
        int playerNumber=numbersModel.getPlayerNumber();


        for (int i=0;i<spyNumber;i++){

            int rand=new Random().nextInt() % playerNumber;

            if (rand<0){
                rand*=-1;
            }

            if (randomSpy.contains(rand)){
                i--;
            }else
            randomSpy.add(rand);
        }


        for (int i=0;i<playerNumber;i++){
            if(randomSpy.contains(i)){
                nameList.add(getString(R.string.you_are_spy));
            }else
            nameList.add(nameObject);
        }


    }

    private void configuration() {

        btnNextPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNextPlayer.getText().equals(getString(R.string.close))){
                  finish();
                }
                counterPlayer++;
                if (counterPlayer%2==0 && counterPlayer<=(numbersModel.getPlayerNumber())*2-1){
                    tvNameObject.setText(R.string.get_to_next_player);
                    btnNextPlayer.setText(R.string.button_turn_around);
                }else if(counterPlayer%2!=0 && counterPlayer<=(numbersModel.getPlayerNumber())*2-1){


                    tvNameObject.setText(nameList.get(counter++));
                    btnNextPlayer.setText(R.string.button_cover_up);
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