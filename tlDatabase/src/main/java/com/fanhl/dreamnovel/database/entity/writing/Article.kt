package com.fanhl.dreamnovel.database.entity.writing

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var title: String? = null,
    @ColumnInfo var content: String? = null,
    @ColumnInfo(name = "create_time") var createTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "update_time") var updateTime: Long = System.currentTimeMillis()
) : Parcelable