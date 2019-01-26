package com.fanhl.dreamnovel.database.entity.writing

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

/**
 * 文章
 */
@Entity
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var title: String? = null,
    @Ignore var content: List<Paragrafo>? = null,
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
    companion object {
        const val TYPE_TEXT = 0
    }
}
