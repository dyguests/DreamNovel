package com.fanhl.dreamnovel.database.dao.writing

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fanhl.dreamnovel.database.DbClient
import com.fanhl.dreamnovel.database.entity.writing.Article
import com.fanhl.dreamnovel.database.entity.writing.Paragrafo
import com.shopify.livedataktx.map
import io.reactivex.Flowable


/**
 * 文章
 */
@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): Flowable<List<Article>>

    @Query("SELECT * FROM article ORDER BY update_time DESC")
    fun getRecents(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg articles: Article): List<Long>

    @Delete
    fun delete(article: Article)

    @Update
    fun update(article: Article)

    /**
     * 最好不要直接调用这个方法。
     *
     * 而是通过 [com.fanhl.dreamnovel.database.dao.writing.WritingDaoKt.getRecentOverviews] 来调用。
     */
    @TypeConverter
    @Query(
        "SELECT a.id,\n" +
                "       a.title,\n" +
                "       a.create_time,\n" +
                "       a.update_time,\n" +
                "       p.id paragrafo_id,\n" +
                "       p.type,\n" +
                "       p.content\n" +
                "  FROM article a\n" +
                "       LEFT JOIN\n" +
                "       paragrafo p ON a.id = p.article_id\n" +
                " GROUP BY p.article_id\n" +
                " ORDER BY a.update_time DESC,\n" +
                "          p.id ASC;\n"
    )
    fun getRecentArticleAndFirstParagrafos(): LiveData<List<ArticleAndFirstParagrafo>>

    data class ArticleAndFirstParagrafo(
        @ColumnInfo var id: Long = 0,
        @ColumnInfo var title: String? = null,
        @ColumnInfo(name = "create_time") var createTime: Long = System.currentTimeMillis(),
        @ColumnInfo(name = "update_time") var updateTime: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "paragrafo_id") var paragrafoId: Long? = null,
        @ColumnInfo var type: Int = Paragrafo.TYPE_TEXT,
        @ColumnInfo var content: String? = null
    )
}

/**
 * 获取最近的数据，并取得article.paragrafo的第一条数据
 */
fun ArticleDao.getRecentOverviews(): LiveData<List<Article>> {
    return getRecentArticleAndFirstParagrafos()
        .map {
            it?.map { aafp ->
                Article(
                    id = aafp.id,
                    title = aafp.title,
                    content = mutableListOf(
                        Paragrafo(
                            articleId = aafp.id,
                            id = aafp.paragrafoId ?: 0,
                            type = aafp.type,
                            content = aafp.content
                        )
                    ).takeIf { aafp.paragrafoId != null },
                    createTime = aafp.createTime,
                    updateTime = aafp.updateTime
                )
            } ?: listOf()
        }
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