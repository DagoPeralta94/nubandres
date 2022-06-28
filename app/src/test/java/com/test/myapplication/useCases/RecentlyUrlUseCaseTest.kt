package com.test.myapplication.useCases

import com.test.myapplication.domain.useCase.RecentlyUrlUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RecentlyUrlUseCaseTest : UseCaseHelper() {

    private lateinit var recentlyUrlUseCase: RecentlyUrlUseCaseImpl

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        recentlyUrlUseCase = RecentlyUrlUseCaseImpl(urlRepository)
    }

    @Test
    fun `WHEN user enter in the app THEN get values from database`() = runBlocking {
        val list = listOf(getShortenDummiesUrl())
        coEvery { urlRepository.getAllSearchFromDatabase() } returns list

        val answer = recentlyUrlUseCase.getShortenUrlFromDatabase()

        coVerify(exactly = 1) { urlRepository.getAllSearchFromDatabase() }
        assert(answer == list)
    }
}