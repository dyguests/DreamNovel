package com.fanhl.dreamnovel.database.dao.writing

import androidx.room.*
import com.fanhl.dreamnovel.database.entity.writing.Article
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import io.reactivex.Flowable

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): Flowable<List<Article>>

    @Query("SELECT * FROM article ORDER BY update_time DESC")
    fun getLastests(): Flowable<List<Article>>

    @Insert
    fun insertAll(vararg articles: Article)

    @Delete
    fun delete(article: Article)

    @Update
    fun update(article: Article)
}

@Dao
interface ParagrafoDao {
    @Query("SELECT * FROM paragrafo")
    fun getAll(): Flowable<List<Paragrafo>>
}