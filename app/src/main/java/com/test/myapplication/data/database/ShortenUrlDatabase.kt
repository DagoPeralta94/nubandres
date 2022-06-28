package com.test.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.myapplication.data.database.dao.ShortenUrlDao
import com.test.myapplication.data.database.entities.ShortenUrlEntity

@Database(entities = [ShortenUrlEntity::class], version = 2)
abstract class ShortenUrlDatabase : RoomDatabase() {

    abstract fun getShortenUrlDao(): ShortenUrlDao
}