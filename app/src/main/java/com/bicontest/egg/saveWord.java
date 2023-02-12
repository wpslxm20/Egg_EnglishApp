package com.bicontest.egg;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.bicontest.egg.MainPages.FoldersViewItem;

@Entity(tableName = "word_table",
foreignKeys = @ForeignKey(
        entity = FoldersViewItem.class,
        parentColumns = "folderId",
        childColumns = "folderId",
        onDelete = ForeignKey.CASCADE  // 삭제될 때 같이 삭제
))
public class saveWord {
//    @Embedded public FoldersViewItem foldersViewItem;
//    @Relation(
//            parentColumn = "folderId",
//            entityColumn = "wordId"
//    )

    @PrimaryKey(autoGenerate = true)
    private int wordId;

    @ColumnInfo(name = "wordEng")
    private String mWordEnglish;

    @ColumnInfo(name = "wordKor")
    private String mWordKorean;

    @ColumnInfo(name = "folderId")
    private int folderId;

    public int getWordId()
    {
        return wordId;
    }

    public void setWordId(int id)
    {
        this.wordId = id;
    }

    public int getFolderId()
    {
        return folderId;
    }

    public void setFolderId(int id)
    {
        this.folderId = id;
    }

    public String getWordEnglish() {
        return mWordEnglish;
    }

    public void setWordEnglish(String wordEnglish) {
        this.mWordEnglish = wordEnglish;
    }

    public String getWordKorean() {
        return mWordKorean;
    }

    public void setWordKorean(String wordKorean) {
        this.mWordKorean = wordKorean;
    }

}
