package com.fanhl.dreamnovel.database.entity.writing

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

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
        const val TYPE_TEXT = 1
    }
}
