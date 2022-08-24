package com.bicontest.egg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[][] recommendWords = {{"apple", "사과"}, {"computer", "컴퓨터"}, {"science", "과학"}, {"student", "학생"}, {"August", "8월"}};
    private String[] folderNames = {"폴더1", "폴더2", "폴더3"};

    // 추천 영단어 리스트 표시에 필요한 것들
    private RecyclerView mRecommendRecyclerView;
    private ArrayList<RecommendViewItem> mRecommendList;
    private RecommendAdapter mRecommendRecyclerViewAdapter;

    // 추천 영단어 열고닫기 버튼&내용
    private ImageButton mOpenBtn;                    // 열기 버튼
    private ImageButton mCloseBtn;                   // 닫기 버튼
    private RelativeLayout mSubrecommnedWords;  // 추천단어의 연관어 내용 부분

    // 폴더 리스트 표시에 필요한 것들
    private RecyclerView mFolderRecyclerView;
    private ArrayList<FoldersViewItem> mFolderList;
    private FoldersAdapter mFolderRecyclerViewAdapter;



    ImageView setting_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setting_btn = (ImageView) findViewById(R.id.setting_btn);
        setting_btn.setOnClickListener(new MainActivity.settingClickListener());

        firstInit();

        // 추천 단어 리스트에 단어의 영어, 한글 정보 전달
        for(int i = 0; i < 5; i++){
            addRecommendItem(recommendWords[i][0], recommendWords[i][1]);
        }

        mRecommendRecyclerViewAdapter = new RecommendAdapter(mRecommendList);
        mRecommendRecyclerView.setAdapter(mRecommendRecyclerViewAdapter);
        mRecommendRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 수직 리스트
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false)); // 수평 리스트


        // 추천 단어의 연관단어 목록을 열기 위한 부분
        mOpenBtn = (ImageButton) findViewById(R.id.recommned_open_btn);
        mCloseBtn = (ImageButton) findViewById(R.id.recommned_close_btn);
        mSubrecommnedWords = (RelativeLayout) findViewById(R.id.subrecommned_words);

        /*mOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubrecommnedWords.setVisibility(View.VISIBLE);
            }
        });*/

        /*mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubrecommnedWords.setVisibility(View.GONE);
            }
        });
        */


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
    private void addRecommendItem(String wordEnglish, String wordKorean){
        RecommendViewItem item = new RecommendViewItem();

        item.setWordEnglish(wordEnglish);
        item.setWordKorean(wordKorean);

        mRecommendList.add(item);
    }

    // 폴더 리스트에 단어의 영어, 한글 정보 추가
    private void addFolderItem(String folderName){
        FoldersViewItem item = new FoldersViewItem();

        item.setFolderName(folderName);

        mFolderList.add(item);
    }

    class settingClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
        }
    }
}