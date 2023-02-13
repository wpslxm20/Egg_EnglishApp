package com.bicontest.egg.MainPages;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoldersDao {
    @Insert(onConflict = REPLACE)
    void insert(FoldersViewItem mainData);

    @Delete
    void delete(FoldersViewItem mainData);

    @Delete
    void reset(List<FoldersViewItem> mainData);

    @Query("SELECT mFolderName FROM folder_table WHERE folderId = :folderId") // id 이용해서 record 불러오기
    String getFolderNameById(int folderId);

    @Query("DELETE FROM folder_table WHERE folderId = :folderId")
    void deleteFolderById(int folderId);
//    @Query("UPDATE folder_table SET mFolderName = :sText WHERE ID = :sID")
//    void update(int sID, String sText);


    @Query("SELECT * FROM folder_table")
    List<FoldersViewItem> getAll();
}
