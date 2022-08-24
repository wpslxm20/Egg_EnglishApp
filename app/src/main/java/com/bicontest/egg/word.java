package com.bicontest.egg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class word extends AppCompatActivity {
    ImageView flash_button, setting_btn;

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word);

        init();
        getData();

        flash_button = (ImageView)findViewById(R.id.imageView3);
        flash_button.setOnClickListener(new flashClickListener());

        setting_btn = (ImageView) findViewById(R.id.setting_btn);
    }
    class flashClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), flash.class);
            startActivity(intent);//액티비티 띄우기
        }
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.worditem);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.
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
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }


}
