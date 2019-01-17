package com.fanhl.dreamnovel.database;

import android.content.Context;
import android.util.Log;

import com.fanhl.dreamnovel.bizwriting.database.dao.ArticleDao;
import com.fanhl.dreamnovel.bizwriting.database.entity.Article;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase /*implements IAppDatabase */{
    /** TAG */
    public static final String TAG = AppDatabase.class.getSimpleName();

    public static AppDatabase INSTANCE;

    public static void init(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "dream_novel").build();
        } else {
            Log.d(TAG, "AppDatabase already inited");
        }
    }

//    @Override
//    public <T> T get() {
////        if (T instanceof ArticleDao) {
////
////        }
//        return null;
//    }

    public abstract ArticleDao articleDao();

}
