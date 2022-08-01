package com.bicontest.egg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    public class RecommendViewHolder extends RecyclerView.ViewHolder {
        TextView word_english;
        TextView word_korean;


        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);

            word_english = (TextView) itemView.findViewById(R.id.recommend_word_english);
            word_korean = (TextView) itemView.findViewById(R.id.recommend_word_korean);
        }
    }

    private ArrayList<RecommendViewItem> mList = null;

    public RecommendAdapter(ArrayList<RecommendViewItem> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public RecommendAdapter.RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recommend_item, parent, false);
        RecommendAdapter.RecommendViewHolder vh = new RecommendAdapter.RecommendViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.RecommendViewHolder holder, int position) {
        RecommendViewItem item = mList.get(position);

        holder.word_english.setText(item.getWordEnglish());  // 영어
        holder.word_korean.setText(item.getWordKorean());   // 한글
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
