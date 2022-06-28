package com.test.myapplication.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.myapplication.domain.model.ShortenUrlItem

@Entity(tableName = "shorten_url_table")
data class ShortenUrlEntity(
    @PrimaryKey
    @ColumnInfo(name = "url_complete") val urlComplete: String,
    @ColumnInfo(name = "alias") val alias: String,
    @ColumnInfo(name = "url_shorten") val urlShorten: String,
    @ColumnInfo(name = "time") val time: Long
)

fun ShortenUrlItem.toDatabase(time: Long) = ShortenUrlEntity(
    alias = alias,
    urlComplete = info.urlComplete.orEmpty(),
    urlShorten = info.urlShorten.orEmpty(),
    time = time
)