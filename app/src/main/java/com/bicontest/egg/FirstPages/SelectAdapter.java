package com.bicontest.egg.FirstPages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bicontest.egg.R;

import java.util.ArrayList;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView word_english;
        TextView word_korean;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            word_english = (TextView) itemView.findViewById(R.id.select_word_english);
            word_korean = (TextView) itemView.findViewById(R.id.select_word_korean);
        }
    }

    private ArrayList<SelectViewItem> mList = null;

    public SelectAdapter(ArrayList<SelectViewItem> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.first_select_item, parent, false);
        SelectAdapter.ViewHolder vh = new SelectAdapter.ViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull SelectAdapter.ViewHolder holder, int position) {
        SelectViewItem item = mList.get(position);

        holder.word_english.setText(item.getWordEnglish());  // 영어
        holder.word_korean.setText(item.getWordKorean());   // 한글
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
