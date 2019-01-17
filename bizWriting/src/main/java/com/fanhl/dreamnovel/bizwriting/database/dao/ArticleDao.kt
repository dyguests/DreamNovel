package com.fanhl.dreamnovel.bizwriting.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fanhl.dreamnovel.bizwriting.database.entity.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Insert
    fun insertAll(vararg articles: Article)

    @Delete
    fun delete(article: Article)

    @Update
    fun update(article: Article)
}
