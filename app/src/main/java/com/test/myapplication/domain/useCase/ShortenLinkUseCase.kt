package com.test.myapplication.domain.useCase

import com.test.myapplication.data.SearchUrlRepository
import com.test.myapplication.data.database.entities.toDatabase
import com.test.myapplication.domain.model.ShortenUrlItem
import javax.inject.Inject

interface ShortenLinkUseCase {
    suspend fun getShortenUrlFromApi(url: String): ShortenUrlItem?
}

class ShortenLinkUseCaseImpl @Inject constructor(
    private val repository: SearchUrlRepository
) : ShortenLinkUseCase {

    override suspend fun getShortenUrlFromApi(url: String): ShortenUrlItem? {
        val result = repository.searchUrlShorten(url)
        result?.let { data ->
            repository.insertUrlShorten(data.toDatabase(System.currentTimeMillis()))
        }
        return result
    }
}