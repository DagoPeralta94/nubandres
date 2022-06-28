package com.test.myapplication.viewModels

import com.test.myapplication.domain.useCase.ShortenLinkUseCase
import com.test.myapplication.domain.useCase.UrlValidatorUseCase
import com.test.myapplication.domain.useCase.ValidatorResult
import com.test.myapplication.domain.useCase.success
import com.test.myapplication.presentation.recentlyUrl.RecentlyUrlViewModel
import com.test.myapplication.presentation.searchUrl.SearchUrlUiState
import com.test.myapplication.presentation.searchUrl.SearchUrlViewModel
import com.test.myapplication.useCases.UseCaseHelper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchUrlViewModelTest : UseCaseHelper() {

    @RelaxedMockK
    private lateinit var urlValidatorUseCase: UrlValidatorUseCase

    @RelaxedMockK
    private lateinit var urlShortenLinkUseCase: ShortenLinkUseCase

    private lateinit var searchUrlViewModel: SearchUrlViewModel

    private lateinit var searchUrlUiState: StateFlow<SearchUrlUiState>

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchUrlViewModel = SearchUrlViewModel(urlValidatorUseCase, urlShortenLinkUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
        searchUrlUiState = searchUrlViewModel.uiState
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN URL is correct Then send the info to the view`() = runTest {
        val dummieData = getShortenDummiesUrl()
        coEvery { urlShortenLinkUseCase.getShortenUrlFromApi(URL_COMPLETE) } returns dummieData

        searchUrlViewModel.onUrlCorrect(URL_COMPLETE)

        assert(searchUrlUiState.value.result == dummieData)
    }

    @Test
    fun `WHEN URL is correct THEN alert the view`() = runTest {
        val dummieData = ValidatorResult(true, true)
        coEvery { urlValidatorUseCase.invoke(URL_COMPLETE) } returns dummieData

        searchUrlViewModel.onUrlToSearch(URL_COMPLETE)

        assertTrue(searchUrlUiState.value.isUrlCorrect)
    }
}