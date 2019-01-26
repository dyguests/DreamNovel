package com.fanhl.dreamnovel.database.dao.writing

import androidx.room.*
import com.fanhl.dreamnovel.database.DbClient
import com.fanhl.dreamnovel.database.entity.writing.Article
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import io.reactivex.Flowable

/**
 * 文章
 */
@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): Flowable<List<Article>>

    @Query("SELECT * FROM article ORDER BY update_time DESC")
    fun getLastests(): Flowable<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg articles: Article): List<Long>

    @Delete
    fun delete(article: Article)

    @Update
    fun update(article: Article)
}

/**
 * 这里的逻辑是直接 article.content==null，则从ParagrafoDao中去取content
 */
fun Article.queryContent() = DbClient.get<ParagrafoDao>().getAll(id)

/**
 * 段落
 */
@Dao
interface ParagrafoDao {
    @Query("SELECT * FROM paragrafo")
    fun getAll(): Flowable<List<Paragrafo>>

    @Query("SELECT * FROM paragrafo WHERE article_id=:articleId")
    fun getAll(articleId: Long): Flowable<List<Paragrafo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg paragrafo: Paragrafo)
}