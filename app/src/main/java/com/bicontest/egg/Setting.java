package com.bicontest.egg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView abc = (TextView)findViewById(R.id.abc);

        EditText flashEdit = (EditText) findViewById(R.id.flashEdit);
        flashEdit.addTextChangedListener(new TextWatcher() {
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
                int flashsec = Integer.parseInt(flashEdit.getText().toString());
                ((glovalVariable) getApplication()).setFlashSecond(flashsec);
                int a = ((glovalVariable) getApplication()).getFlashSecond();
                abc.setText(a);
            }
        });
    }
}