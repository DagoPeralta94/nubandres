package com.test.myapplication.data.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.test.myapplication.data.network.SearchUrlApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(interceptor: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(interceptor)
            .baseUrl("https://url-shortener-nu.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): SearchUrlApiClient {
        return retrofit.create(SearchUrlApiClient::class.java)
    }
}