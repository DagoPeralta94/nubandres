package com.test.myapplication.useCases

import com.test.myapplication.data.database.entities.toDatabase
import com.test.myapplication.domain.useCase.ShortenLinkUseCase
import com.test.myapplication.domain.useCase.ShortenLinkUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ShortenLinkUseCaseTest : UseCaseHelper() {

    private lateinit var shortenLinkUseCase: ShortenLinkUseCaseImpl

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        shortenLinkUseCase = ShortenLinkUseCaseImpl(urlRepository)
    }

    @Test
    fun `WHEN api return a short link THEN save it in database`() = runBlocking {
        val data = getShortenDummiesUrl()
        coEvery { urlRepository.searchUrlShorten(URL_COMPLETE) } returns data

        shortenLinkUseCase.getShortenUrlFromApi(URL_COMPLETE)

        coVerify(exactly = 1) { urlRepository.insertUrlShorten(any()) }
    }

}