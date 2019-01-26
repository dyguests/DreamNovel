package com.fanhl.dreamnovel.database.entity.writing

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

/**
 * 文章
 */
/**
 * @param content 注意此字段直接从ArticleDao中是取不到的,需要从ParagrafoDao中去取
 */
@Entity
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var title: String? = null,
    @Ignore var content: MutableList<Paragrafo>? = null,
    @ColumnInfo(name = "create_time") var createTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "update_time") var updateTime: Long = System.currentTimeMillis()
) : Parcelable

/**
 * 段落（一篇文章分成多个段落）
 */
@Entity(
    foreignKeys = [
        ForeignKey(entity = Article::class, parentColumns = arrayOf("id"), childColumns = arrayOf("article_id"))
    ]
)
@Parcelize
data class Paragrafo(
    @ColumnInfo(name = "article_id") var articleId: Long = 0,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var type: Int = TYPE_TEXT,
    @ColumnInfo var content: String? = null
) : Parcelable {
    /**
     * 这里判断当前段落是不是初始状态（默认状态）
     *
     * 一般用来保存时，初始状态不保存
     */
    fun isInitState() = when (type) {
        TYPE_TEXT -> {
            content.isNullOrBlank()
        }
        else -> {
            content.isNullOrBlank()
        }
    }

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_Image = 1
    }
}
