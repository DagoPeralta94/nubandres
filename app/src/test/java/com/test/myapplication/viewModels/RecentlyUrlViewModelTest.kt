package com.test.myapplication.viewModels

import com.test.myapplication.domain.useCase.RecentlyUrlUseCase
import com.test.myapplication.presentation.recentlyUrl.RecentlyUrlUiState
import com.test.myapplication.presentation.recentlyUrl.RecentlyUrlViewModel
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
class RecentlyUrlViewModelTest : UseCaseHelper() {

    @RelaxedMockK
    private lateinit var recentlyUrlUseCase: RecentlyUrlUseCase

    private lateinit var recentlyUrlViewModel: RecentlyUrlViewModel

    private lateinit var recentlyUrlState: StateFlow<RecentlyUrlUiState>

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        recentlyUrlViewModel = RecentlyUrlViewModel(recentlyUrlUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
        recentlyUrlState = recentlyUrlViewModel.uiState
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN app get shorten url list THEN the progress must be not visible`() =
        runTest {
            val list = listOf(getShortenDummiesUrl())
            coEvery { recentlyUrlUseCase.getShortenUrlFromDatabase() } returns list

            recentlyUrlViewModel.onCreate()

            assertFalse(recentlyUrlState.value.inProgress)
        }

    @Test
    fun `WHEN app get shorten url list THEN the info inside must be the same`() =
        runTest {
            val list = listOf(getShortenDummiesUrl())
            coEvery { recentlyUrlUseCase.getShortenUrlFromDatabase() } returns list

            recentlyUrlViewModel.onCreate()

            assertTrue(recentlyUrlState.value.data == list)
        }
}