package com.fanhl.dreamnovel.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fanhl.dreamnovel.database.dao.writing.ArticleDao
import com.fanhl.dreamnovel.database.dao.writing.ParagrafoDao
import com.fanhl.dreamnovel.database.entity.writing.Article
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo

@Database(entities = [Article::class, Paragrafo::class], version = 3)
abstract class AppDatabase : RoomDatabase(), IAppDatabase {

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(clazz: Class<T>) = when {
        clazz.isAssignableFrom(ArticleDao::class.java) -> articleDao() as T
        clazz.isAssignableFrom(ParagrafoDao::class.java) -> paragrafoDao() as T
        else -> throw Exception("未找到对应 dao")
    }

    abstract fun articleDao(): ArticleDao
    abstract fun paragrafoDao(): ParagrafoDao

    companion object {
        /** TAG  */
        private val TAG = AppDatabase::class.java.simpleName

        fun init(context: Context) {
            if (IAppDatabase.instance != null) {
                Log.d(TAG, "AppDatabase already inited")
                return
            }

            IAppDatabase.instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "dream_novel")
                .addMigrations(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.apply {
                            execSQL("ALTER TABLE article ADD COLUMN create_time INTEGER")
                            execSQL("ALTER TABLE article ADD COLUMN update_time INTEGER")
                        }
                    }
                })
                .build()
        }
    }
}
