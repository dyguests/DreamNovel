package com.fanhl.dreamnovel.database.entity.writing

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var title: String? = null,
    @ColumnInfo var content: String? = null
)
