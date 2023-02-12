package com.bicontest.egg.MainPages;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.bicontest.egg.saveWord;

// 메인 화면에서 보이는 폴더

//@Entity (foreignKeys
//        = [ForeignKey(
//        entity = saveWord::class,  // 외래키 연결대상 Entity 클래스
//        parentColumns = ["category_uid"],    // 외래키 연결대상 Entity 필드명
//        childColumns = ["record_category_uid"],
//        onDelete = ForeignKey.CASCADE	     // 삭제될 경우 같이 삭제 설정
//)])
@Entity(tableName = "folder_table")
public class FoldersViewItem {

    @PrimaryKey(autoGenerate = true)
    private int folderId;

    @ColumnInfo(name = "mFolderName")
    private String mFolderName;

    public int getFolderId() { return folderId; }

    public void setFolderId(int folderId)
    {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return mFolderName;
    }

    public void setFolderName(String folderName) {
        this.mFolderName = folderName;
    }

}
