package com.bicontest.egg.MainPages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bicontest.egg.RoomDB;
import com.bicontest.egg.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private RoomDB database;

    private String[][] recommendWords = {{"apple", "사과"}, {"computer", "컴퓨터"}, {"science", "과학"}, {"student", "학생"}, {"August", "8월"}};
    private String[][][] toggleWords = {{{"banana", "바나나"}, {"grape", "포도"}}, {{"Test", "시험"}}, {{"Test", "시험"}}, {{"Test", "시험"}}, {{"Test", "시험"}}};
    private String[] folderNames = {"폴더1", "폴더2", "폴더3" , "폴더4"};

    // 추천 영단어 리스트 표시에 필요한 것들
    private RecyclerView mRecommendRecyclerView;
    private ArrayList<RecommendViewItem> mRecommendList;
    private RecommendAdapter mRecommendRecyclerViewAdapter;

    // 폴더 리스트 표시에 필요한 것들
    private RecyclerView mFolderRecyclerView;
    private List<FoldersViewItem> mFolderList = new ArrayList<FoldersViewItem>();
    private FoldersAdapter mFolderRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = RoomDB.getInstance(getActivity());
        mFolderList = database.folderDAO().getAll();

        firstInit();

        // 추천 단어 리스트에 단어의 영어, 한글 정보 전달 + 연관어 정보
        for(int i = 0; i < 5; i++){
            addRecommendItem(recommendWords[i][0], recommendWords[i][1], toggleWords[i]);
        }

        mRecommendRecyclerViewAdapter = new RecommendAdapter(mRecommendList);
        mRecommendRecyclerView.setAdapter(mRecommendRecyclerViewAdapter);
        mRecommendRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity())); // 수직 리스트
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false)); // 수평 리스트

        // 폴더 리스트에 폴더명 정보 전달
        for(int i = 0; i < folderNames.length; i++){
            addFolderItem(folderNames[i]);
        }
        addFolderItem("+");

        mFolderRecyclerViewAdapter = new FoldersAdapter(mFolderList);
        mFolderRecyclerView.setAdapter(mFolderRecyclerViewAdapter);
        mFolderRecyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2)); // 수직 리스트
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false)); // 수평 리스트
    }

    // 추천 단어 리스트, 폴더 리스트에 필요한 부분
    private void firstInit(){
        // 추천 단어 리스트
        mRecommendRecyclerView = (RecyclerView) requireActivity().findViewById(R.id.recommend_recyclerview);
        mRecommendList = new ArrayList<>();

        // 폴더 리스트
        mFolderRecyclerView = (RecyclerView) requireActivity().findViewById(R.id.folders_recyclerview);
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

    // 폴더 리스트에 단어의 정보 추가
    private void addFolderItem(String folderName){
        FoldersViewItem item = new FoldersViewItem();

        item.setFolderName(folderName);

        mFolderList.add(item);
    }
}