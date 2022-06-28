package com.test.myapplication.presentation.searchUrl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.R
import com.test.myapplication.domain.useCase.ShortenLinkUseCase
import com.test.myapplication.domain.useCase.UrlValidatorUseCase
import com.test.myapplication.domain.useCase.success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUrlViewModel @Inject constructor(
    private val urlValidatorUseCase: UrlValidatorUseCase,
    private val urlShortenLinkUseCase: ShortenLinkUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUrlUiState())
    internal val uiState: StateFlow<SearchUrlUiState> = _uiState

    internal fun onUrlToSearch(url: String) {
        viewModelScope.launch {
            showProgress()
            val result = urlValidatorUseCase(url)
            _uiState.value = SearchUrlUiState(
                inProgress = false,
                isUrlCorrect = result.success,
                dotError = if (result.containDot.not()) R.string.error_on_dot else null,
                wwwError = if (result.containWww.not()) R.string.error_on_www else null
            )
        }
    }

    internal fun onUrlCorrect(url: String) {
        viewModelScope.launch {
            showProgress()
            val result = urlShortenLinkUseCase.getShortenUrlFromApi(url)
            _uiState.value = SearchUrlUiState(
                inProgress = false,
                addedNewUrl = result != null,
                result = result
            )
        }
    }

    private fun showProgress(show: Boolean = true) {
        _uiState.value = SearchUrlUiState(inProgress = show)
    }
}