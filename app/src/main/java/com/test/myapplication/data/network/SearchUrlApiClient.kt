package com.test.myapplication.data.network

import com.test.myapplication.data.model.ShortenUrlModel
import com.test.myapplication.data.model.ShortenUrlRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SearchUrlApiClient {

    @Headers("Content-Type: application/json")
    @POST("/api/alias")
    suspend fun getShortenUrl(@Body url: ShortenUrlRequest): Response<ShortenUrlModel>
}