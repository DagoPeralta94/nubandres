package com.test.myapplication.data

import com.test.myapplication.data.database.dao.ShortenUrlDao
import com.test.myapplication.data.database.entities.ShortenUrlEntity
import com.test.myapplication.data.network.SearchUrlService
import com.test.myapplication.domain.model.ShortenUrlItem
import com.test.myapplication.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUrlRepository @Inject constructor(
    private val api: SearchUrlService,
    private val urlDao: ShortenUrlDao
) {

    suspend fun searchUrlShorten(url: String): ShortenUrlItem? = withContext(Dispatchers.IO) {
        val response = api.getShortenUrl(url)
        response?.toDomain()
    }

    suspend fun getAllSearchFromDatabase(): List<ShortenUrlItem> = withContext(Dispatchers.IO) {
        urlDao.getAllShortenUrl().map(ShortenUrlEntity::toDomain)
    }

    suspend fun insertUrlShorten(shortenUrl: ShortenUrlEntity) = withContext(Dispatchers.IO) {
        urlDao.insertShortenUrl(shortenUrl)
    }

    suspend fun clearShortenUrl(id: Int) = withContext(Dispatchers.IO) {
        urlDao.deleteShortenUrl(id)
    }
}