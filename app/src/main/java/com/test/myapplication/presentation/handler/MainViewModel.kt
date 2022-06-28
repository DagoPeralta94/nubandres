package com.test.myapplication.presentation.handler

import androidx.lifecycle.ViewModel
import com.test.myapplication.domain.model.ShortenUrlItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UrlState())
    internal val uiState: StateFlow<UrlState> = _uiState

    internal fun onUrlAdded(result: ShortenUrlItem?) {
        _uiState.value = UrlState(newUrlAdd = true, data = result)
    }
}

data class UrlState(
    val newUrlAdd: Boolean = false,
    val data: ShortenUrlItem? = null
)