package com.bicontest.egg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class flash extends AppCompatActivity {

    int wordIndex = 0, maxIndex = 11;

    ImageView left_btn, right_btn, setting_btn;
    TextView kor_text, eng_text;
    Switch flash_switch;
    boolean flash_random_bool;


    Handler flash_handler = new Handler(){
        public void handleMessage(Message msg){
            if (wordIndex + 1 < maxIndex) {
                left_btn.setEnabled(true);
                left_btn.setVisibility(View.VISIBLE);
                wordIndex++;
                eng_text.setText(listTitle.get(wordIndex));
                kor_text.setText(listContent.get(wordIndex));
            }
            else if(wordIndex + 1 == maxIndex) {
                left_btn.setEnabled(true);
                right_btn.setEnabled(false);
                right_btn.setVisibility(View.INVISIBLE);
                wordIndex++;
                eng_text.setText(listTitle.get(wordIndex));
                kor_text.setText(listContent.get(wordIndex));
            }
        }
    };


    List<String> listTitleOri = Arrays.asList("apple", "computer", "study", "apple", "computer", "study", "apple", "computer", "study", "apple", "computer", "study");
    List<String> listContentOri = Arrays.asList(
            "사과",
            "컴퓨터",
            "공부하다",
            "사과",
            "컴퓨터",
            "공부하다",
            "사과",
            "컴퓨터",
            "공부하다",
            "사과",
            "컴퓨터",
            "공부하다"
    );
    List<String> listTitle = new ArrayList<String>(listTitleOri);
    List<String> listContent= new ArrayList<String>(listContentOri);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        left_btn = (ImageView) findViewById(R.id.leftBtn);
        right_btn = (ImageView) findViewById(R.id.rightBtn);
        setting_btn = (ImageView) findViewById(R.id.setting_btn);
        flash_switch = (Switch) findViewById(R.id.flashSwitch);

        left_btn.setOnClickListener(new leftClickListener());
        right_btn.setOnClickListener(new rightClickListener());
        setting_btn.setOnClickListener(new settingClickListener());
        flash_switch.setOnCheckedChangeListener(new flashSwitchListener());

        eng_text = (TextView) findViewById(R.id.engWord);
        kor_text = (TextView) findViewById(R.id.korWord);


        //플래시 랜덤이 true이면 suffle
        flash_random_bool = ((glovalVariable) getApplication()).getFlashRandBool();
        if (flash_random_bool) {
            List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
            Collections.shuffle(list);
            for (int i=0;i<maxIndex+1;i++){
                listTitle.set(i, listTitleOri.get(list.get(i)));
            }
            for (int i=0;i<maxIndex+1;i++){
                listContent.set(i, listContentOri.get(list.get(i)));
            }
        }
        else{
            Collections.copy(listTitle, listTitleOri);
            Collections.copy(listContent, listContentOri);
        }

        eng_text.setText(listTitle.get(wordIndex));
        kor_text.setText(listContent.get(wordIndex));

        //left버튼 비활성화
        left_btn.setEnabled(false);
        left_btn.setVisibility(View.INVISIBLE);
    }
    class leftClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (wordIndex - 1 > 0) {
                right_btn.setEnabled(true);
                right_btn.setVisibility(View.VISIBLE);
                wordIndex--;
                eng_text.setText(listTitle.get(wordIndex));
                kor_text.setText(listContent.get(wordIndex));
            }
            else if(wordIndex - 1 == 0) {
                right_btn.setEnabled(true);
                left_btn.setEnabled(false);
                left_btn.setVisibility(View.INVISIBLE);
                wordIndex--;
                eng_text.setText(listTitle.get(wordIndex));
                kor_text.setText(listContent.get(wordIndex));
            }
        }
    }
    class rightClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (wordIndex + 1 < maxIndex) {
                left_btn.setEnabled(true);
                left_btn.setVisibility(View.VISIBLE);
                wordIndex++;
                eng_text.setText(listTitle.get(wordIndex));
                kor_text.setText(listContent.get(wordIndex));
            }
            else if(wordIndex + 1 == maxIndex) {
                left_btn.setEnabled(true);
                right_btn.setEnabled(false);
                right_btn.setVisibility(View.INVISIBLE);
                wordIndex++;
                eng_text.setText(listTitle.get(wordIndex));
                kor_text.setText(listContent.get(wordIndex));
            }
        }
    }
    class settingClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
        }
    }

    //switch
    class flashSwitchListener implements CompoundButton.OnCheckedChangeListener{
        Timer flashTimer;
        TimerTask flashTT;
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                int flashSecond = ((glovalVariable) getApplication()).getFlashSecond() * 1000;

                flashTimer = new Timer();
                flashTT = new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = flash_handler.obtainMessage();
                        flash_handler.sendMessage(msg);
                    }
                };

                flashTimer.schedule(flashTT, flashSecond, flashSecond);
            }
            else {
                flashTT.cancel();
            }
        }
    }

//        flash_button = (ImageView)findViewById(R.id.imageView3);
//        flash_button.setOnClickListener(new word.flashClickListener());
//    }
//    class flashClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(getApplicationContext(), flash.class);
//            startActivity(intent);//액티비티 띄우기
//        }
//    }



}