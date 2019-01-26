package com.fanhl.dreamnovel.database.dao.writing

import androidx.room.*
import com.fanhl.dreamnovel.database.DbClient
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

/**
 * 这里的逻辑是直接 article.content==null，则从ParagrafoDao中去取content
 */
fun Article.queryContent() = DbClient.get<ParagrafoDao>().getAll(id)


@Dao
interface ParagrafoDao {
    @Query("SELECT * FROM paragrafo")
    fun getAll(): Flowable<List<Paragrafo>>

    @Query("SELECT * FROM paragrafo WHERE article_id=:articleId")
    fun getAll(articleId: Long): Flowable<List<Paragrafo>>
}