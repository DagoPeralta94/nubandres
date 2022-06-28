package com.test.myapplication.presentation.recentlyUrl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.domain.useCase.RecentlyUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentlyUrlViewModel @Inject constructor(
    private val recentlyUrlUseCase: RecentlyUrlUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecentlyUrlUiState(inProgress = true))
    internal val uiState: StateFlow<RecentlyUrlUiState> = _uiState

    internal fun onCreate() {
        viewModelScope.launch {
            val result = recentlyUrlUseCase.getShortenUrlFromDatabase()
            _uiState.value = RecentlyUrlUiState(
                inProgress = false,
                data = result
            )
        }
    }
}