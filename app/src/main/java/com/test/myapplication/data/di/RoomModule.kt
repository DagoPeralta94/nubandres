package com.test.myapplication.data.di

import android.content.Context
import androidx.room.Room
import com.test.myapplication.data.database.ShortenUrlDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val SHORTEN_URL_DATABASE_NAME = "shorten_url_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShortenUrlDatabase::class.java, SHORTEN_URL_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideShortenUrlDao(db: ShortenUrlDatabase) = db.getShortenUrlDao()
}