package com.bicontest.egg;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private List<saveWord> listData = new ArrayList<>();

    // RoomDB 세팅
    private RoomDB database;
    private Activity context;
    public RecyclerAdapter(List<saveWord> listData){
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // LayoutInflater를 이용하여 전 단계에서 만들었던 worditem.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worditem, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        database = RoomDB.getInstance(context);

        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
        holder.deleteWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWord item = listData.get(holder.getAbsoluteAdapterPosition());
                database.mainDao().delete(item);

                int position = holder.getAbsoluteAdapterPosition();
                listData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listData.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

//    void addItem(Data data) {
//        // 외부에서 item을 추가시킬 함수입니다.
//        listData.add(data);
//    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private ImageButton deleteWordBtn;
//        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView2);
            textView2 = itemView.findViewById(R.id.textView3);
            deleteWordBtn = itemView.findViewById(R.id.btn_delete_word);
//            imageView = itemView.findViewById(R.id.imageView4);
        }

        void onBind(saveWord data) {
            textView1.setText(data.getWordEnglish());
            textView2.setText(data.getWordKorean());
//            imageView.setImageResource(data.getResId());
        }
    }
}