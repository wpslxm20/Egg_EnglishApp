package com.bicontest.egg.FirstPages;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.bicontest.egg.saveWord;

import java.util.List;

@Dao
public interface MainDao
{
    @Insert(onConflict = REPLACE)
    void insert(saveWord mainData);

    @Delete
    void delete(saveWord mainData);

    @Delete
    void reset(List<saveWord> mainData);

    @Query("SELECT * FROM word_table WHERE folderId = :sID")
    List<saveWord> getWordByFolder(int sID);

    @Query("SELECT * FROM word_table")
    List<saveWord> getAll();
}