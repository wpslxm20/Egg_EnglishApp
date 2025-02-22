package com.bicontest.egg;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bicontest.egg.FirstPages.MainDao;

import com.bicontest.egg.MainPages.FoldersDao;
import com.bicontest.egg.MainPages.FoldersViewItem;

@Database(entities = {saveWord.class, FoldersViewItem.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;

    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context)
    {
        if (database == null)
        {
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    //아래 메소드는 버전이 변경되면 이전 데이터들이 모두 유실됨
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract FoldersDao folderDAO();
    public abstract MainDao mainDao();

}
