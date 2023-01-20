package com.bicontest.egg;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bicontest.egg.MainPages.RecommendAdapter;
import com.bicontest.egg.MainPages.RecommendViewItem;
import com.bicontest.egg.MainPages.ToggleWordsViewItem;
import com.bicontest.egg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResultFragment extends Fragment {
    private String[][] searchWords = {{"apple", "사과"}, {"computer", "컴퓨터"}, {"science", "과학"}, {"student", "학생"}, {"August", "8월"}};
    private String[][][] toggleWords = {{{"banana", "바나나"}, {"grape", "포도"}}, {{"Test", "시험"}}, {{"Test", "시험"}}, {{"Test", "시험"}}, {{"Test", "시험"}}};

    // 검색어 = 검색할 단어
    private String mSearchWord;

    // 파이어베이스 DB 접근
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    // 검색 결과 리스트 표시에 필요한 것들
    private RecyclerView mSearchRecyclerView;
    private ArrayList<RecommendViewItem> mSearchList;
    private RecommendAdapter mSearchRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 검색어 데이터 받아오기
        mSearchWord = this.getArguments().getString("SearchWord");
        //Log.d("Test", mSearchWord + "-----------------------------");

        getWordsThread.start();

        // 데이터 가져오는 동안 기다리기
        try {
            getWordsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // recylerview 세팅
        firstInit();

        // 추천 단어 리스트에 단어의 영어, 한글 정보 전달 + 연관어 정보
        /*for(int i = 0; i < 5; i++){
            addSearchItem(searchWords[i][0], searchWords[i][1], toggleWords[i]);
        }*/

        mSearchRecyclerViewAdapter = new RecommendAdapter(mSearchList);
        mSearchRecyclerView.setAdapter(mSearchRecyclerViewAdapter);
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity())); // 수직 리스트
    }

    // 검색 결과 리스트에 필요한 부분
    private void firstInit(){
        // 검색 결과 리스트
        mSearchRecyclerView = (RecyclerView) requireActivity().findViewById(R.id.search_result_recyclerview);
        mSearchList = new ArrayList<>();
    }

    // 검색 결과 리스트에 단어의 영어, 한글 정보 추가
    private void addSearchItem(String wordEnglish, String wordKorean, String[][] toggleWord){
        RecommendViewItem searchItem = new RecommendViewItem();

        searchItem.setWordEnglish(wordEnglish);
        searchItem.setWordKorean(wordKorean);
        searchItem.setToggleItem(buildToggleWords(toggleWord));

        mSearchList.add(searchItem);

        mSearchRecyclerViewAdapter.notifyDataSetChanged();
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

    // 파이어베이스에서 데이터 가져와서 검색 결과 리스트 만들기
    Thread getWordsThread = new Thread(new Runnable() {
        @Override
        public void run() {
            database = FirebaseDatabase.getInstance();   // 데이터베이스 선언, 할당
            myRef = database.getReference("words");

            myRef.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                // 해당 단어가 검색어를 포함하고 있는 경우
                                if(snapshot.child("eng").getValue().toString().contains(mSearchWord)) {
                                    addSearchItem(
                                            snapshot.child("eng").getValue().toString(), // 영어
                                            snapshot.child("kor").getValue().toString(), // 한글
                                            toggleWords[0]); // 연관어

                                    Log.d("SearchText", "검색 결과 : " + snapshot.getValue());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    }
            );
        }
    });
}