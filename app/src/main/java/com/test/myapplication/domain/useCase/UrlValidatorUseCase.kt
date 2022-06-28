package com.test.myapplication.domain.useCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface UrlValidatorUseCase {
    suspend operator fun invoke(url: String): ValidatorResult
}

@Singleton
class UrlValidatorUseCaseImpl @Inject constructor() : UrlValidatorUseCase {
    override suspend fun invoke(url: String): ValidatorResult =
        withContext(Dispatchers.Default) {
            ValidatorResult(
                containDot = url.contains("."),
                containWww = url.contains("www")
            )
        }
}

data class ValidatorResult(val containDot: Boolean, val containWww: Boolean)

val ValidatorResult.success get() = containDot && containWww