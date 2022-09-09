package com.bicontest.egg;

import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView abc = (TextView)findViewById(R.id.abc);

        EditText flashEdit = (EditText) findViewById(R.id.flashEdit);

        Switch flash_random = (Switch) findViewById(R.id.flashRandom);
        flash_random.setOnCheckedChangeListener(new flashSwitchRandomListener());

        //이렇게 안 해주면 flashSecond 수정 후에 activity 새로고침 되면 flash_editText 내용이 초기화됨
        flashEdit.setText(Integer.toString(((glovalVariable) getApplication()).getFlashSecond()));
        flashEdit.addTextChangedListener(new TextWatcher() {

            // 안 쓰는 거 주석 처리했더니 에러 남
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //입력하기 전
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //EditText에 변화가 있을 때
            }

            @Override
            public void afterTextChanged(Editable s) {
                //입력이 끝났을 때
                String flashText = flashEdit.getText().toString();
                if(flashText.getBytes().length <= 0){
                    abc.setText("숫자를 입력하세요");
                }
                else {
                    int flashsec = Integer.parseInt(flashText);
                    ((glovalVariable) getApplication()).setFlashSecond(flashsec);
                    int a = ((glovalVariable) getApplication()).getFlashSecond();
                    abc.setText(Integer.toString(a));
                }
            }
        });


    }
    //switch
    class flashSwitchRandomListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                ((glovalVariable) getApplication()).setFlashRandBool(true);
            }
            else {
                ((glovalVariable) getApplication()).setFlashRandBool(false);
            }
        }
    }
}