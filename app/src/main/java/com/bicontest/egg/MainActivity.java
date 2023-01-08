package com.bicontest.egg;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bicontest.egg.MainPages.FoldersAdapter;
import com.bicontest.egg.MainPages.FoldersViewItem;
import com.bicontest.egg.MainPages.MainFragment;
import com.bicontest.egg.MainPages.RecommendAdapter;
import com.bicontest.egg.MainPages.RecommendViewItem;
import com.bicontest.egg.MainPages.ToggleWordsViewItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // fragment 제어를 위한 부분
    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private SearchResultFragment searchResultFragment;

    private Toolbar mSearchBar;       // 툴바
    private SearchView mSearchView;   // 검색창
    private ImageButton mSettingBtn;  // 설정 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fragment 제어를 위한 부분: MainFragment <-> SearchResultFragment
        fragmentManager = getSupportFragmentManager();
        mainFragment = new MainFragment();
        searchResultFragment = new SearchResultFragment();

        // 시작은 MainFragment로 세팅
        fragmentManager.beginTransaction().replace(R.id.main_container, mainFragment).commit();

        // 툴바
        mSearchBar = findViewById(R.id.search_toolbar);
        setSupportActionBar(mSearchBar);

        // 검색 창
        mSearchView = (SearchView) findViewById(R.id.searchview);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // 검색어 입력 시 검색 Fragment
                fragmentManager.beginTransaction().add(R.id.main_container, searchResultFragment).commit();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.println(Log.DEBUG,"Test", "---------------------------------------------------");
                //Log.println(Log.DEBUG,"Test", "검색어 입력");
                String searchWord = newText;

                return false;
            }
        });

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
    }
}