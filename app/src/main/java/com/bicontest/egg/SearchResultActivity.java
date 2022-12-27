package com.bicontest.egg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.bicontest.egg.MainPages.RecommendAdapter;
import com.bicontest.egg.MainPages.RecommendViewItem;
import com.bicontest.egg.MainPages.ToggleWordsViewItem;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    private String[][] searchWords = {{"apple", "사과"}, {"computer", "컴퓨터"}, {"science", "과학"}, {"student", "학생"}, {"August", "8월"}};
    private String[][][] toggleWords = {{{"banana", "바나나"}, {"grape", "포도"}}, {{"Test", "시험"}}, {{"Test", "시험"}}, {{"Test", "시험"}}, {{"Test", "시험"}}};

    // 검색 결과 리스트 표시에 필요한 것들
    private RecyclerView mSearchRecyclerView;
    private ArrayList<RecommendViewItem> mSearchList;
    private RecommendAdapter mSearchRecyclerViewAdapter;

    private Toolbar mSearchBar;       // 툴바
    private ImageButton mSettingBtn;  // 설정 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

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

        firstInit();

        // 추천 단어 리스트에 단어의 영어, 한글 정보 전달 + 연관어 정보
        for(int i = 0; i < 5; i++){
            addSearchItem(searchWords[i][0], searchWords[i][1], toggleWords[i]);
        }

        mSearchRecyclerViewAdapter = new RecommendAdapter(mSearchList);
        mSearchRecyclerView.setAdapter(mSearchRecyclerViewAdapter);
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 수직 리스트
    }

    // 검색 결과 리스트에 필요한 부분
    private void firstInit(){
        // 검색 결과 리스트
        mSearchRecyclerView = (RecyclerView) findViewById(R.id.recommend_recyclerview);
        mSearchList = new ArrayList<>();
    }

    // 검색 결과 리스트에 단어의 영어, 한글 정보 추가
    private void addSearchItem(String wordEnglish, String wordKorean, String[][] toggleWord){
        RecommendViewItem searchItem = new RecommendViewItem();

        searchItem.setWordEnglish(wordEnglish);
        searchItem.setWordKorean(wordKorean);
        searchItem.setToggleItem(buildToggleWords(toggleWord));

        mSearchList.add(searchItem);
    }

    // 연관어 부분
    private ArrayList<ToggleWordsViewItem> buildToggleWords(String[][] toggleWord) {
        ArrayList<ToggleWordsViewItem> mToggleWordsList = new ArrayList<>();

        for (int i=0; i<toggleWord.length; i++) {
            ToggleWordsViewItem toggleItem = new ToggleWordsViewItem(toggleWord[i][0], toggleWord[i][1]);
            //Log.println(Log.DEBUG, "debug", "----------------------------------------------------------------");
            //Log.println(Log.DEBUG, "Data", toggleWord[i][0] + " " + toggleWord[i][1]);

            mToggleWordsList.add(toggleItem);
        }

        return mToggleWordsList;
    }
}