package com.bicontest.egg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bicontest.egg.MainPages.FoldersAdapter;
import com.bicontest.egg.MainPages.FoldersViewItem;
import com.bicontest.egg.MainPages.RecommendAdapter;
import com.bicontest.egg.MainPages.RecommendViewItem;
import com.bicontest.egg.MainPages.ToggleWordsAdapter;
import com.bicontest.egg.MainPages.ToggleWordsViewItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[][] recommendWords = {{"apple", "사과"}, {"computer", "컴퓨터"}, {"science", "과학"}, {"student", "학생"}, {"August", "8월"}};
    private String[][][] toggleWords = {{{"banana", "바나나"}, {"grape", "포도"}}, {{"Test", "시험"}}, {{"Test", "시험"}}, {{"Test", "시험"}}, {{"Test", "시험"}}};
    private String[] folderNames = {"폴더1", "폴더2", "폴더3"};

    // 추천 영단어 리스트 표시에 필요한 것들
    private RecyclerView mRecommendRecyclerView;
    private ArrayList<RecommendViewItem> mRecommendList;
    private RecommendAdapter mRecommendRecyclerViewAdapter;

    // 연관어 표시에 필요한 것들
    private RecyclerView mToggleWordsRecyclerView;
    private ArrayList<ToggleWordsViewItem> mToggleWordsList;
    private ToggleWordsAdapter mToggleWordsRecyclerViewAdapter;

    // 폴더 리스트 표시에 필요한 것들
    private RecyclerView mFolderRecyclerView;
    private ArrayList<FoldersViewItem> mFolderList;
    private FoldersAdapter mFolderRecyclerViewAdapter;

    // 설정 버튼
    ImageView setting_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setting_btn = (ImageView) findViewById(R.id.setting_btn);
        setting_btn.setOnClickListener(new MainActivity.settingClickListener());

        firstInit();

        // 추천 단어 리스트에 단어의 영어, 한글 정보 전달 + 연관어 정보
        for(int i = 0; i < 5; i++){
            addRecommendItem(recommendWords[i][0], recommendWords[i][1], toggleWords[i]);
        }

        mRecommendRecyclerViewAdapter = new RecommendAdapter(mRecommendList);
        mRecommendRecyclerView.setAdapter(mRecommendRecyclerViewAdapter);
        mRecommendRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 수직 리스트
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false)); // 수평 리스트

        // 연관어 토글 내용을 위한 부분
        //mToggleWordsRecyclerViewAdapter = new ToggleWordsAdapter(mToggleWordsList);
        //mToggleWordsRecyclerView.setAdapter(mToggleWordsRecyclerViewAdapter);
        //mToggleWordsRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 수직 리스트
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false)); // 수평 리스트

        // 폴더 리스트에 폴더명 정보 전달
        for(int i = 0; i < 2; i++){
            addFolderItem(folderNames[i]);
        }
        addFolderItem("+");

        mFolderRecyclerViewAdapter = new FoldersAdapter(mFolderList);
        mFolderRecyclerView.setAdapter(mFolderRecyclerViewAdapter);
        mFolderRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 수직 리스트
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false)); // 수평 리스트
    }

    // 추천 단어 리스트, 폴더 리스트에 필요한 부분
    private void firstInit(){
        // 추천 단어 리스트
        mRecommendRecyclerView = (RecyclerView) findViewById(R.id.recommend_recyclerview);
        mRecommendList = new ArrayList<>();

        // 폴더 리스트
        mFolderRecyclerView = (RecyclerView) findViewById(R.id.folders_recyclerview);
        mFolderList = new ArrayList<>();
    }

    // 추천 리스트에 단어의 영어, 한글 정보 추가
    private void addRecommendItem(String wordEnglish, String wordKorean, String[][] toggleWord){
        RecommendViewItem item = new RecommendViewItem();

        item.setWordEnglish(wordEnglish);
        item.setWordKorean(wordKorean);

        /*ToggleWordsViewItem toggleItem = new ToggleWordsViewItem();
        for (int i=0; i<toggleWord.length; i++) {
            toggleItem.setToggleWordEnglish(toggleWord[i][0]);
            toggleItem.setToggleWordKorean(toggleWord[i][1]);
        }*/

        mRecommendList.add(item);
    }

    // 폴더 리스트에 단어의 영어, 한글 정보 추가
    private void addFolderItem(String folderName){
        FoldersViewItem item = new FoldersViewItem();

        item.setFolderName(folderName);

        mFolderList.add(item);
    }

    // 설정 버튼 클릭 시
    class settingClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
        }
    }
}