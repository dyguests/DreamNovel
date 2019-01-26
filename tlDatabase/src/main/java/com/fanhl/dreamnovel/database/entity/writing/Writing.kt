package com.fanhl.dreamnovel.database.entity.writing

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
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
@Entity
@Parcelize
data class Paragrafo(
    @ColumnInfo var type: Int = TYPE_TEXT,
    @ColumnInfo var content: String? = null
) : Parcelable {
    companion object {
        const val TYPE_TEXT = 0
    }
}
