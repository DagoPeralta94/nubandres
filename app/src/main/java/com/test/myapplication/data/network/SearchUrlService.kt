package com.test.myapplication.data.network

import com.test.myapplication.data.model.ShortenUrlModel
import com.test.myapplication.data.model.ShortenUrlRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUrlService @Inject constructor(
    private val api: SearchUrlApiClient
) {

    suspend fun getShortenUrl(url: String): ShortenUrlModel? = withContext(Dispatchers.IO) {
        val response = api.getShortenUrl(ShortenUrlRequest(url))
        response.body()
    }
}