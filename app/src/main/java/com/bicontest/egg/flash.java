package com.bicontest.egg;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class flash extends AppCompatActivity {

    int wordIndex = 0, maxIndex = 11;

    ImageView left_btn, right_btn, setting_btn;
    TextView kor_text, eng_text;

    List<String> listTitle = Arrays.asList("apple", "computer", "study", "apple", "computer", "study", "apple", "computer", "study", "apple", "computer", "study");
    List<String> listContent = Arrays.asList(
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);


        left_btn = (ImageView) findViewById(R.id.leftBtn);
        right_btn = (ImageView) findViewById(R.id.rightBtn);
        setting_btn = (ImageView) findViewById(R.id.setting_btn);

        left_btn.setOnClickListener(new leftClickListener());
        right_btn.setOnClickListener(new rightClickListener());
        setting_btn.setOnClickListener(new settingClickListener());

        eng_text = (TextView) findViewById(R.id.engWord);
        kor_text = (TextView) findViewById(R.id.korWord);

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