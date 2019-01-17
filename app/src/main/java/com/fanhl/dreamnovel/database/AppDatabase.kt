package com.fanhl.dreamnovel.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fanhl.dreamnovel.database.dao.bizwriting.ArticleDao
import com.fanhl.dreamnovel.database.entity.writing.Article
import com.fanhl.dreamnovel.ui.entrance.SplashActivity

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase(), IAppDatabase {

    abstract fun articleDao(): ArticleDao

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(clazz: Class<T>): T {
        if (clazz.isAssignableFrom(SplashActivity::class.java)) {
            TODO("测试用")
        } else if (clazz.isAssignableFrom(ArticleDao::class.java)) {
            return articleDao() as T
        }
        throw Exception("未找到对应 dao")
    }

    companion object {
        /** TAG  */
        private val TAG = AppDatabase::class.java.simpleName

        fun init(context: Context) {
            if (IAppDatabase.instance == null) {
                IAppDatabase.instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "dream_novel").build()
            } else {
                Log.d(TAG, "AppDatabase already inited")
            }
        }
    }
}
