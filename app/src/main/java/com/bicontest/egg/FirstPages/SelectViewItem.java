package com.bicontest.egg.FirstPages;

// 최초 화면에서 선택하는 단어

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class SelectViewItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "wordEng")
    private String mWordEnglish;

    @ColumnInfo(name = "wordKor")
    private String mWordKorean;

    @ColumnInfo(name = "wordCheck")
    private Boolean mWordChecked = false;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public  Boolean getWordChecked() { return mWordChecked; }

    public void setWordChecked(Boolean wordChecked) { this.mWordChecked = wordChecked; }
}
