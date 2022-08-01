package com.bicontest.egg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.FoldersViewHolder> {

    public class FoldersViewHolder extends RecyclerView.ViewHolder {
        TextView folder_name;


        public FoldersViewHolder(@NonNull View itemView) {
            super(itemView);

            folder_name = (TextView) itemView.findViewById(R.id.folder_name);
        }
    }

    private ArrayList<FoldersViewItem> mList = null;

    public FoldersAdapter(ArrayList<FoldersViewItem> mList) {
        this.mList = mList;
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public FoldersAdapter.FoldersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.folders_item, parent, false);
        FoldersAdapter.FoldersViewHolder vh = new FoldersAdapter.FoldersViewHolder(view);
        return vh;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull FoldersAdapter.FoldersViewHolder holder, int position) {
        FoldersViewItem item = mList.get(position);

        holder.folder_name.setText(item.getFolderName());  // 폴더명
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
