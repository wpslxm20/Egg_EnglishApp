package com.bicontest.egg.MainPages;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// 메인 화면에서 보이는 폴더
@Entity(tableName = "folder_table")
public class FoldersViewItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "mFolderName")
    private String mFolderName;

    public int getId() { return id; }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFolderName() {
        return mFolderName;
    }

    public void setFolderName(String folderName) {
        this.mFolderName = folderName;
    }

}
