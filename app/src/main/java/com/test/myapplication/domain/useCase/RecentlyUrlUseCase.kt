package com.test.myapplication.domain.useCase

import com.test.myapplication.data.SearchUrlRepository
import com.test.myapplication.domain.model.ShortenUrlItem
import javax.inject.Inject

interface RecentlyUrlUseCase {
    suspend fun getShortenUrlFromDatabase(): List<ShortenUrlItem>
}

class RecentlyUrlUseCaseImpl @Inject constructor(
    private val repository: SearchUrlRepository
) : RecentlyUrlUseCase {

    override suspend fun getShortenUrlFromDatabase(): List<ShortenUrlItem> {
        return repository.getAllSearchFromDatabase()
    }
}