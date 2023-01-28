package com.bicontest.egg.MainPages;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;

@Dao
public interface FolderDAO {
    @Insert(onConflict = REPLACE)
    void insert(FoldersViewItem mainData);

    @Delete
    void delete(FoldersViewItem mainData);

    @Delete
    void reset(List<FoldersViewItem> mainData);

//    @Query("UPDATE folder_table SET mFolderName = :sText WHERE ID = :sID")
//    void update(int sID, String sText);

    @Query("SELECT * FROM folder_table")
    List<FoldersViewItem> getAll();
}
