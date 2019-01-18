package com.fanhl.dreamnovel.database.dao.writing

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fanhl.dreamnovel.database.entity.writing.Article
import io.reactivex.Flowable

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): Flowable<List<Article>>

    @Insert
    fun insertAll(vararg articles: Article)

    @Delete
    fun delete(article: Article)

    @Update
    fun update(article: Article)
}
