package com.test.myapplication.domain.di

import com.test.myapplication.domain.useCase.RecentlyUrlUseCase
import com.test.myapplication.domain.useCase.RecentlyUrlUseCaseImpl
import com.test.myapplication.domain.useCase.ShortenLinkUseCase
import com.test.myapplication.domain.useCase.ShortenLinkUseCaseImpl
import com.test.myapplication.domain.useCase.UrlValidatorUseCase
import com.test.myapplication.domain.useCase.UrlValidatorUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindUrlValidatorUseCase(
        urlValidatorUseCaseImpl: UrlValidatorUseCaseImpl
    ): UrlValidatorUseCase

    @Binds
    abstract fun bindShortenUrlUseCase(
        shortenLinkUseCaseImpl: ShortenLinkUseCaseImpl
    ): ShortenLinkUseCase

    @Binds
    abstract fun bindRecentlyUrlUseCase(
        recentlyUrlUseCaseImpl: RecentlyUrlUseCaseImpl
    ): RecentlyUrlUseCase
}