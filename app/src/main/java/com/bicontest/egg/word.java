package com.bicontest.egg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class word extends AppCompatActivity {
    ImageView flash_button;


    private RecyclerAdapter adapter;

    private Toolbar mSearchBar;     // 툴바
    private ImageButton mSettingBtn;  // 설정 버튼
    private ImageButton deleteFolderBtn;  // 플래시 실행 버튼

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word);

        // 툴바
        mSearchBar = findViewById(R.id.search_toolbar);
        setSupportActionBar(mSearchBar);

        // 설정 버튼
        mSettingBtn = (ImageButton) findViewById(R.id.toolbar_setting_btn);
        mSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.println(Log.DEBUG, "debug", "----------------------------------------------------------------");
                //Log.println(Log.DEBUG, "debug", "설정 버튼 클릭");
                Intent intent = new Intent(getApplicationContext(), Setting.class); // 설정 페이지로 이동
                startActivity(intent);
            }
        });

        init();
        getData();

        flash_button = (ImageView)findViewById(R.id.btn_play);
        flash_button.setOnClickListener(new flashClickListener());

        // 폴더 삭제 버튼
       deleteFolderBtn = (ImageButton) findViewById(R.id.btn_delete_folder);
       deleteFolderBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
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
