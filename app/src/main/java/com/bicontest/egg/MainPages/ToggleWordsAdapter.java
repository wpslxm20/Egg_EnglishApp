package com.bicontest.egg.MainPages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bicontest.egg.R;

import java.util.ArrayList;

// 메인 화면에서 추천 단어 토글 열면 보이는 연관어
public class ToggleWordsAdapter extends RecyclerView.Adapter<ToggleWordsAdapter.ToggleWordsViewHolder> {
    public class ToggleWordsViewHolder extends RecyclerView.ViewHolder {
        TextView toggle_word_english;
        TextView toggle_word_korean;

        public ToggleWordsViewHolder(@NonNull View itemView) {
            super(itemView);

            toggle_word_english = (TextView) itemView.findViewById(R.id.subrecommend_word_english);
            toggle_word_korean = (TextView) itemView.findViewById(R.id.subrecommend_word_korean);
        }
    }

    private ArrayList<ToggleWordsViewItem> mList = null;

    public ToggleWordsAdapter(ArrayList<ToggleWordsViewItem> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ToggleWordsAdapter.ToggleWordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.toggle_words_item, parent, false);
        ToggleWordsAdapter.ToggleWordsViewHolder vh = new ToggleWordsAdapter.ToggleWordsViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ToggleWordsAdapter.ToggleWordsViewHolder holder, int position) {
        ToggleWordsViewItem item = mList.get(position);

        holder.toggle_word_english.setText(item.getToggleWordEnglish());  // 영어
        holder.toggle_word_korean.setText(item.getToggleWordKorean());   // 한글
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
