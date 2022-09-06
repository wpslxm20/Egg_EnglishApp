package com.bicontest.egg;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
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

    // 폴더 리스트 표시에 필요한 것들
    private RecyclerView mFolderRecyclerView;
    private ArrayList<FoldersViewItem> mFolderList;
    private FoldersAdapter mFolderRecyclerViewAdapter;

    // ImageView setting_btn; // 설정 버튼
    Toolbar search_bar;       // 툴바

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 툴바
        search_bar = findViewById(R.id.searchBar);
        setSupportActionBar(search_bar);

        // 설정 버튼
        //setting_btn = (ImageView) findViewById(R.id.setting_btn);
        //setting_btn.setOnClickListener(new MainActivity.settingClickListener());

        firstInit();

        // 추천 단어 리스트에 단어의 영어, 한글 정보 전달 + 연관어 정보
        for(int i = 0; i < 5; i++){
            addRecommendItem(recommendWords[i][0], recommendWords[i][1], toggleWords[i]);
        }

        mRecommendRecyclerViewAdapter = new RecommendAdapter(mRecommendList);
        mRecommendRecyclerView.setAdapter(mRecommendRecyclerViewAdapter);
        mRecommendRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 수직 리스트
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
        RecommendViewItem recommendItem = new RecommendViewItem();

        recommendItem.setWordEnglish(wordEnglish);
        recommendItem.setWordKorean(wordKorean);
        recommendItem.setToggleItem(buildToggleWords(toggleWord));

        mRecommendList.add(recommendItem);
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

    // 폴더 리스트에 단어의 영어, 한글 정보 추가
    private void addFolderItem(String folderName){
        FoldersViewItem item = new FoldersViewItem();

        item.setFolderName(folderName);

        mFolderList.add(item);
    }

    // 설정 버튼 클릭 시
    /*class settingClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
        }
    }*/

    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_toolbar, menu);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        MenuItem mSearch = menu.findItem(R.id.menu_search);


        SearchView sv = (SearchView) findViewById(R.id.menu_search);
        sv.onActionViewExpanded(); //바로 검색 할 수 있도록
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
//            searchView.setQueryHint("hint");
//            queryTextListener = new SearchView.OnQueryTextListener() {
//                 @Override
//                 public boolean onQueryTextChange(String newText) {
//
//                    return true;
//                 }
//                 @Override
//                 public boolean onQueryTextSubmit(String query) {
//
//                    return true;
//                 }
//            };
//            searchView.setOnQueryTextListener(queryTextListener);
//         }

//        SearchView sv = (SearchView) mSearch.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query){
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                return true;
            }
        });


        return true;
    }

}