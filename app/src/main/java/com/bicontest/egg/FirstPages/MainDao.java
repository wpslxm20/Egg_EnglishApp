package com.bicontest.egg.FirstPages;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
public interface MainDao
{
    @Insert(onConflict = REPLACE)
    void insert(SelectViewItem mainData);

    @Delete
    void delete(SelectViewItem mainData);

    @Delete
    void reset(ArrayList<SelectViewItem> mainData);

//    @Query("UPDATE word_table SET text = :sText WHERE ID = :sID")
//    void update(int sID, String sText);

    @Query("SELECT * FROM word_table")
    ArrayList<SelectViewItem> getAll();
}
