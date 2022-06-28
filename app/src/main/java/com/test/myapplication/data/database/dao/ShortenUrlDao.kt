package com.test.myapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.myapplication.data.database.entities.ShortenUrlEntity

@Dao
interface ShortenUrlDao {

    @Query("SELECT * FROM shorten_url_table ORDER BY time DESC")
    suspend fun getAllShortenUrl(): List<ShortenUrlEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShortenUrl(shortenUrl: ShortenUrlEntity)

    @Query("DELETE FROM shorten_url_table WHERE url_complete = :idToDelete")
    suspend fun deleteShortenUrl(idToDelete: Int)
}