package com.fanhl.dreamnovel.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fanhl.dreamnovel.bizwriting.database.dao.ArticleDao
import com.fanhl.dreamnovel.bizwriting.database.entity.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase(), IAppDatabase {

    abstract fun articleDao(): ArticleDao

    override fun <T> get(): T? {
        //        if (T instanceof ArticleDao) {
        //
        //        }
        return null
    }

    companion object {
        /** TAG  */
        private val TAG = AppDatabase::class.java.simpleName

        var INSTANCE: AppDatabase? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "dream_novel").build()
            } else {
                Log.d(TAG, "AppDatabase already inited")
            }
        }
    }
}
